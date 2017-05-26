/**
 * Created by Ivana Zeljkovic on 08-Apr-17.
 */

function validateLoginData(formData){
    var username = formData["username"];
    var password = formData["password"];

    if(username.trim() == "") {
        getToastr("Niste uneli korisničko ime!", "Nepotpuna forma za logovanje", 2);
        return false;
    }

    if(password.trim() == "") {
        getToastr("Niste uneli lozinku!", "Nepotpuna forma za logovanje", 2);
        return false;
    }

    return true;
}

function login(){
    var formData = getFormData($("#loginForm"));
    var success = Boolean(validateLoginData(formData));

    if(success){
        var user = JSON.stringify(formData);

        $.ajax({
            url: "/user/login",
            type: "POST",
            contentType: "application/json",
            data: user,
            success: function(data, textStatus, response){
                localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
                window.location.replace("home.html");
            },
            error: function (response, textStatus) {
                    getToastr("Nepostojeće korisničko ime/lozinka!", "Status: " + response.status, 3);
            }
        });
    }
    else
        return false;
}

$(document).on('click', '#loginForm :button', function(e){
    e.preventDefault();
    login();
});

$(document).ready(function(){
   if(localStorage.getItem("currentUserToken") != null)
       window.location.replace("home.html");
});
