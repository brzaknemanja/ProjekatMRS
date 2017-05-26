/**
 * Created by Ivana Zeljkovic on 09-Apr-17.
 */
function registration(){
    formData = getFormData($("#registrationForm"));
    var success = Boolean(validateRegistrationData(formData));

    if(success){
        var newUser = JSON.stringify(formData);
        $.ajax({
            url: "registeredUser/registration",
            type: "POST",
            contentType: "application/json",
            data: newUser,
            success: function(){
                window.location.replace("home.html");
            },
            error: function (response, textStatus) {
                if(response.status != 201)
                    getToastr("Neuspešna registracija novog korisnika!", "Status: " + response.status, 3);
                else
                    window.location.replace("home.html");
            }
        });
    }
    else
        return false;

}

$(document).on('click', '#logo', function(e){
    e.preventDefault();
    if(localStorage.getItem("currentUserToken") == null)
        window.location.replace("index.html");
    else
        window.location.replace("home.html");
});

$(document).on('click', '#registrationForm :button', function(e){
    e.preventDefault();
    registration();
});