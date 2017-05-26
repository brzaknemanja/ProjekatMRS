/**
 * Created by Ivana Zeljkovic on 30-Apr-17.
 */

var allRestaurants = {};

function showRestaurantList(){
    var restaurantList = (allRestaurants == null) ? [] : (allRestaurants instanceof Array ? allRestaurants : [allRestaurants]);

    if(restaurantList.length){
        var restaurantListTableBody = $("#restaurant-list-table").find("tbody");

        $.each(restaurantList, function(index, restaurant){
            console.log(restaurant.id);
            var restaurantRow = "<tr>" +
                    "<td>" + restaurant.name + "</td>" +
                    "<td>" + restaurant.description + "</td>" +
                    "<td>" + restaurant.street + "</td>" +
                    "<td>" + restaurant.city + "</td>" +
                    "<td>" + restaurant.kitchenType + "</td>" +
                    "<td>" +
                        "<form class='form-inline'>" +
                            "<input type='hidden' class='form-control' value='" + restaurant.id + "'>" +
                            "<button type='button' class='btn btn-primary' title='Detaljniji prikaz za rezervaciju' id='button-restaurant-details'>" +
                                "<span class='glyphicon glyphicon-info-sign'></span> Detaljnije" +
                            "</button>" +
                        "</form>" +
                    "</td>" +
                "</tr>";
            restaurantListTableBody.append(restaurantRow);
        });
    }
    else
        getToastr("Još uvek nije registrovan nijedan restoran!", "", 2);
}

function getAllRestaurants(){
    $.ajax({
        url: "/restaurant/getAllRestaurants",
        type: "GET",
        dataType: "json",
        beforeSend: function(request){
          request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            localStorage.setItem("currentUserToken", response.getResponseHeader("Authorization"));
            allRestaurants = data;
            showRestaurantList();
        },
        error: function(response){
            getToastr("Neuspešno prikazivanje restorana!", "Status: " + response.status, 3);
        }
    });
}

$(document).on('click', '#button-restaurant-details', function(){
   var form = $(this).parents("form");
   console.log(form);
   var idRestaurant = form.find("input[type=hidden]").val();
   console.log(idRestaurant);
   if(localStorage.getItem("currentUserRole") == "Registered")
       window.location.replace("restaurantReservationPage.html?id=" + idRestaurant);
   else if(localStorage.getItem("currentUserRole") == "Manager")
       window.location.replace("SystemManagerRestaurantView.html?id=" + idRestaurant);

});

$(document).ready(function(){
    getAllRestaurants();
});