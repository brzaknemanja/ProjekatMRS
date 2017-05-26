$(document).on('click', '#link-registration-waiter', function(e){
    e.preventDefault();
    localStorage.setItem("registrationType", "waiter");
    window.location.replace("employeeRegistration.html");
});

$(document).on('click', '#link-registration-chef', function(e){
    e.preventDefault();
    localStorage.setItem("registrationType", "chef");
    window.location.replace("employeeRegistration.html");
});

$(document).on('click', '#link-registration-barman', function(e){
    e.preventDefault();
    localStorage.setItem("registrationType", "barman");
    window.location.replace("employeeRegistration.html");
});

$(document).on('click', '#restaurant-profile-button', function(e){
    e.preventDefault();
    window.location.replace("restaurantProfile.html");
});