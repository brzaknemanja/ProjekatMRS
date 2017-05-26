/**
 * Created by Katarina Cukurov on 09/04/2017.
 */

function regSystemManager(){
    formData = getFormData($("#regManagerRestForm"));
    var success = Boolean(validateRegistrationData(formData));
    if(success){
        var newManager = JSON.stringify(formData);
        console.log(newManager);
        $.ajax({
            url: "/managerRestaurant/registration",
            type: "POST",
            contentType: "application/json",
            data: newManager,
            dataType: "json",
            beforeSend: function(request){
                request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
            },
            success: function(response){
                localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
                window.location.replace("home.html");
            },
            error: function(response, textStatus){
            if(response.status != 201)
                getToastr("Neuspešna registracija menadzera restorana!", "Status: " + response.status, 3);
            else
                window.location.replace("home.html");
            }
        });
    }
    else
        return false;
}

$(document).on('click', '#regManagerRestForm :button', function(e){
    e.preventDefault();
    regSystemManager();
});