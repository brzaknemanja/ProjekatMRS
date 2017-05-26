var vrstaHrane = '';

$(document).ready(function(){
    getRestaurant();
});

function getRestaurant(){
    $.ajax({
        url: "/restaurant/getRestaurant",
        type: "GET",
        dataType: "json",
        beforeSend: function(request){
          request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            var restaurant = data;
            localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));

            $("#restaurant-name").text(restaurant.name);
            $("#restaurant-description").text(restaurant.description);
            $("#restaurant-street").text(restaurant.street);
            $("#restaurant-city").text(restaurant.city);
            getDrinks();
            getDishes();
        },
        error: function (response, textStatus) {
            if(response.status != 200)
                getToastr("Nemate pravo pristupa!");
            else{
                window.location.replace("home.html");
            }
        }
    });
}

function getDrinks(){
    $.ajax({
        url: "/restaurant/getDrinks",
        type: "PUT",
        contentType: "text/plain",
        data: $("#restaurant-name").text(),
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            $("#drinks-table tbody tr").remove();
            for(var i = 0; i < data.length; i++){
                var red = '<tr><td>' + data[i].name + '</td><td>' +
                                data[i].description + '</td><td>' + data[i].price + '</td></tr>';
                $("#drinks-table").append(red);
            }
            localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
        },
        error: function(response, textStatus){
            getToastr("", "Dodavanje pica neuspešno! \nStatus: " + response.status, 3);
        }
    });

}

function getDishes(){
    $.ajax({
        url: "/restaurant/getDishes",
        type: "PUT",
        contentType: "text/plain",
        data: $("#restaurant-name").text(),
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            $("#dish-table tbody tr").remove();
            for(var i = 0; i < data.length; i++){
                var red = '<tr><td>' + data[i].name + '</td><td>' +
                data[i].description + '</td><td>' + data[i].price + '</td></tr>';
                $("#dish-table").append(red);
            }
            localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
        },
        error: function(response){
            getToastr("", "Dodavanje jela neuspesno! \nStatus: " + response.status, 3);
        }
    });

}

function changeName(){
    var name = $("#new-name").val();
    console.log(name);
    $.ajax({
        url: "/restaurant/updateName",
        type: "PUT",
        contentType: "text/plain",
        data: name,
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            var currentUser = data;
            localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
            $("#restaurant-name").text($("#new-name").val());
            $("#restaurant-name-button").css('visibility', 'visible');
            $("#form-name").remove();
        },
        error: function(response){
            getToastr("", "Ažuriranje imena neuspešno! \nStatus: " + response.status, 3);
        }
    });
}

function changeStreet(){
    var name = $("#new-street").val();

    $.ajax({
        url: "/restaurant/updateStreet",
        type: "PUT",
        contentType: "text/plain",
        data: name,
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            var currentUser = data;
            localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
            $("#restaurant-street").text($("#new-street").val());
            $("#restaurant-street-button").css('visibility', 'visible');
            $("#form-street").remove();
        },
        error: function(response){
            getToastr("", "Ažuriranje ulice neuspešno! \nStatus: " + response.status, 3);
        }
    });
}

function changeCity(){
    var name = $("#new-city").val();

    $.ajax({
        url: "/restaurant/updateCity",
        type: "PUT",
        contentType: "text/plain",
        data: name,
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            var currentUser = data;
            localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
            $("#restaurant-city").text($("#new-city").val());
            $("#restaurant-city-button").css('visibility', 'visible');
            $("#form-city").remove();
        },
        error: function(response){
            getToastr("", "Ažuriranje grada neuspešno! \nStatus: " + response.status, 3);
        }
    });
}

function changeDescription(){
    var name = $("#new-description").val();

    $.ajax({
        url: "/restaurant/updateDescription",
        type: "PUT",
        contentType: "text/plain",
        data: name,
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            var currentUser = data;
            localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
            $("#restaurant-description").text($("#new-description").val());
            $("#restaurant-description-button").css('visibility', 'visible');
            $("#form-description").remove();
        },
        error: function(response){
            getToastr("", "Ažuriranje opisa neuspešno! \nStatus: " + response.status, 3);
        }
    });
}

function dodajPice(){
    formData = getFormData($("#new-food"));
    var newDrink = JSON.stringify(formData);
    $.ajax({
        url: "/restaurant/addDrink",
        type: "POST",
        contentType: "application/json",
        data: newDrink,
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(response){
            localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
            getDrinks();
        },
        error: function(response, textStatus){
        if(response.status != 201)
            getToastr("Neuspešno dodavanje novog pica!", "Status: " + response.status, 3);
        else
            getDrinks();
        }
    });
}

function dodajJelo(){
    formData = getFormData($("#new-food"));
    var newDish = JSON.stringify(formData);
    $.ajax({
        url: "/restaurant/addDish",
        type: "POST",
        contentType: "application/json",
        data: newDish,
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(response){
            localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
            getDishes();
        },
        error: function(response, textStatus){
        if(response.status != 201)
            getToastr("Neuspešno dodavanje novog pica!", "Status: " + response.status, 3);
        else
            getDishes();
        }
    });
}

$(document).on('click', '#restaurant-name-button', function(){
    $("#restaurant-name-button").css('visibility', 'hidden');
    var trParent = $(this).parents("tr");
    var formName = "<td id='form-name' class='form-group'>" +
            "<input type='text' id='new-name' placeholder='novo ime'>" +
            "<button class='btn btn-warning' id='name-changed-button'>Potvrdi izmenu</button>" +
        "</td>";
    trParent.append(formName);
});

$(document).on('click', '#restaurant-description-button', function(){
    $("#restaurant-description-button").css('visibility', 'hidden');
    var trParent = $(this).parents("tr");
    var formName = "<td id='form-description' class='form-group'>" +
            "<input type='text' id='new-description' placeholder='novi opis'>" +
            "<button class='btn btn-warning' id='description-changed-button'>Potvrdi izmenu</button>" +
        "</td>";
    trParent.append(formName);
});

$(document).on('click', '#restaurant-street-button', function(){
    $("#restaurant-street-button").css('visibility', 'hidden');
    var trParent = $(this).parents("tr");
    var formName = "<td id='form-street' class='form-group'>" +
            "<input type='text' id='new-street' placeholder='nova ulica'>" +
            "<button class='btn btn-warning' id='street-changed-button'>Potvrdi izmenu</button>" +
        "</td>";
    trParent.append(formName);
});

$(document).on('click', '#restaurant-city-button', function(){
    $("#restaurant-city-button").css('visibility', 'hidden');
    var trParent = $(this).parents("tr");
    var formName = "<td id='form-city' class='form-group'>" +
            "<input type='text' id='new-city' placeholder='novi grad'>" +
            "<button class='btn btn-warning' id='city-changed-button'>Potvrdi izmenu</button>" +
        "</td>";
    trParent.append(formName);
});


$(document).on('click', '#name-changed-button', function(){
    changeName();
});

$(document).on('click', '#description-changed-button', function(){
    changeDescription();
});

$(document).on('click', '#street-changed-button', function(){
    changeStreet();
});

$(document).on('click', '#city-changed-button', function(){
    changeCity();
});

$(document).on('click', '#restaurant-drink-button', function(){
    vrstaHrane = 'pice';
});

$(document).on('click', '#restaurant-dish-button', function(){
    vrstaHrane = 'jelo';
});

$(document).on('click', '#restaurant-food-button', function(){
    if(vrstaHrane == 'pice')
            dodajPice();
        if(vrstaHrane == 'jelo')
            dodajJelo();
    document.getElementById('new-food-desc').value = "";
    document.getElementById('new-food-name').value = "";
    document.getElementById('new-food-price').value = "";

    $("#foodModal").modal('hide');

});

$(document).on('click', '#food-quit-button', function(){
    document.getElementById('new-food-desc').value = "";
    document.getElementById('new-food-name').value = "";
    document.getElementById('new-food-price').value = "";

});
