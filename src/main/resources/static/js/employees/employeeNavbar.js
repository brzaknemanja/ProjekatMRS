/**
 * Created by Ivana Zeljkovic on 26-Apr-17.
 */
$(document).on('click', '#employee-profile-button', function(){
    window.location.replace("profile.html");
});

$(document).on('click', '#restaurant-details-button', function(e){
    e.preventDefault();
    window.location.replace("restaurantDetails.html");
});