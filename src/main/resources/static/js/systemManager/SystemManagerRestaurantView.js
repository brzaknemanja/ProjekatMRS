var id;
$(document).ready(function(){
    var index = document.URL.indexOf("?id=");
    	if(index != -1){
    		id = document.URL.substring(index+4)
    		getRestaurantById(id);
    	}
});

function getRestaurantById(){
    $.ajax({
        url: "/restaurant/getById",
        type: "PUT",
        contentType: "text/plain",
        data: id,
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            $("#restaurant-name").text(data.name);
            $("#restaurant-description").text(data.description);
            $("#restaurant-street").text(data.street);
            $("#restaurant-city").text(data.city);
            $("#managers-table tbody tr").remove();
            for(var i = 0; i < data.managers.length; i++){
                var red = '<tr><td>' + data.managers[i].name + '</td><td>' +
                                data.managers[i].lastname + '</td><td>' + data.managers[i].username + '</td></tr>';
                $("#managers-table").append(red);
            }
            getManagers();
        },
        error: function(response){
            getToastr("", "Ažuriranje imena neuspešno! \nStatus: " + response.status, 3);
        }
    });
}

function getManagers(){
    $('#managers').empty();
    $.ajax({
        url: "/managerRestaurant/getAll",
        type: "GET",
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function (data, textStatus, response) {
            allManagers = data;
            for(i = 0; i < data.length; i++)
            {
                $('#managers').append('<option>' + data[i].username + '</option>');
            }
            localStorage.setItem("currentUserToken", response.getResponseHeader("Authorization"));
        }
    });
}

function dodajMenadzera(){
    var restaurant = '{\"name\":\"' + $("#restaurant-name").text() + '\",\"description\":\"' + $("#restaurant-description").text() + '\",\"username\":\"'
    + $('#managers')[0].value + '\",\"street\":\"' + $("#restaurant-street").text() + '\",\"city\":\"' + $("#restaurant-city").text() + '\"}';
    $.ajax({
        url: "/restaurant/addMoreManagers",
        type: "POST",
        contentType: "application/json",
        data: restaurant,
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
            getRestaurantById();
        },
        error: function(response, textStatus){
        if(response.status != 201)
            getToastr("Neuspešno dodavanje novog pica!", "Status: " + response.status, 3);
        else{
            getRestaurantById();
            }
        }
    });

}

$(document).on('click', '#add-manager', function(){
    dodajMenadzera();
});