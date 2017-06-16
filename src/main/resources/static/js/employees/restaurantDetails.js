/**
 * Created by brzak on 15.6.17..
 */
$(document).ready(function(){
    getRestaurant();
});


function getRestaurant() {
    $.ajax({
        url: "/restaurant/getEmployeeRestaurant",
        type: "GET",
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function (data, textStatus, response) {
            var restaurant = data;
            localStorage.setItem("currentUserToken", response.getResponseHeader("Authorization"));

            $("#restaurant-name").text(restaurant.name);
            $("#restaurant-description").text(restaurant.description);
            $("#restaurant-street").text(restaurant.street);
            $("#restaurant-city").text(restaurant.city);
            getDrinks();
            getDishes();
        },
        error: function (response, textStatus) {
            if (response.status != 200)
                getToastr("Nemate pravo pristupa!");
            else {
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