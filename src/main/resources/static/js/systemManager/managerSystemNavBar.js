$(document).on('click', '#link-registration-manager-system', function(e){
    e.preventDefault();
    window.location.replace("managerSystemRegistration.html");
});

$(document).on('click', '#link-registration-manager-restaurant', function(e){
    e.preventDefault();
    window.location.replace("managerRestaurantRegistration.html");
});

$(document).on('click', '#reg-restaurant-button', function(){
    window.location.replace("restaurantRegistration.html");
});

$(document).on('click', '#user-restaurant-list-button', function(){
    window.location.replace("restaurantsPage.html");
});