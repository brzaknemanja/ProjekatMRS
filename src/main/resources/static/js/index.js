    /**
 * Created by Ivana Zeljkovic on 09-Apr-17.
 */
function getCurrentUser(){
    $.ajax({
        url: "/user/getCurrentUser",
        type: "GET",
        dataType: "json",
        beforeSend: function(request){
          request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            var currentUser = data;
            localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
            localStorage.setItem("currentUserRole", currentUser.type);

            $("#current-user-username").val(currentUser.username);

            if(currentUser.type == "Registered") {
                $.get("/navbars/regUserNavbarPart.html", function(data){
                    $("#div_navbar").append(data);
                });

                if (document.URL.indexOf("profile.html") != -1)
                    showPersonalData(currentUser);
                else if(document.URL.indexOf("friends.html") != -1)
                    setCurrentUser(currentUser);
            }

            else if(currentUser.type == "Waiter" || currentUser.type == "Chef" || currentUser.type == "Barman"){
                if(currentUser.firstLogin && document.URL.indexOf("profile") == -1)
                {
                    changePassword(currentUser);
                }
                else if(currentUser.firstLogin && document.URL.indexOf("profile") != -1)
                {
                    window.location.replace("home.html");
                }
                else
                {
                    $.get("/navbars/employeeNavbarPart.html", function(data){
                        $("#div_navbar").append(data);
                    });

                    if(document.URL.indexOf("profile.html") != -1)
                        showPersonalData(currentUser);
                }
            }
            else if(currentUser.type == "Manager")
            {
                $.get("/navbars/ManagerSystemNavBar.html", function(data){
                    $("#div_navbar").append(data);
                });
            }
            else if(currentUser.type == "RestaurantManager")
            {
                $.get("/navbars/ManagerRestaurantNavBar.html", function(data){
                    $("#div_navbar").append(data);
                });
            }
        },
        error: function (response, textStatus) {
            if(document.URL.indexOf("index.html") ==  -1)
                window.location.replace("index.html");
        }
    });
}

function logout()
{
    $.ajax({
        url: "/user/logout",
        type: "GET",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
            localStorage.removeItem("currentUserToken");
        },
        success: function() {
            window.location.replace("index.html");
        },
        error: function(response, textStatus){
            getToastr("Neuspešno odjavljivanje sa sistema!", "Status: " + response.status, 3);
        }
    });
}

function changePassword(user) {
    $("#div_navbar").html("");
    $("#changePassword").append("<h1>Change password</h1>" +
        "<input type='text' id='old-password' placeholder='Stara Lozinka'>" +
        "<input type='text' id='new-password' placeholder='Nova Lozinka'>" +
        "<input type='text' id='new-password-check' placeholder='Ponoviti Novu Lozinku'>" +
        "<button class='btn btn-warning' id='first-password-changed-button'>Nastavi</button>");


    $(document).on('click', '#first-password-changed-button', function(){
        if($("#new-password").val().trim() != $("#new-password-check").val().trim()) {
            getToastr("Unosi za novu lozinku se ne poklapaju!", "Neispravni podaci u formi za ažuriranje lozinke", 2);
            return false;
        }

        var data = {};
        data["oldPassword"] = $("#old-password").val();
        data["newPassword"] = $("#new-password").val();

        $.ajax({
            url: "/user/updatePassword",
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: "json",
            beforeSend: function(request){
                request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
            },
            success: function(data, textStatus, response){
                localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
                window.location.replace("home.html");
            },
            error: function(response){
                getToastr("", "Ažuriranje lozinke neuspešno \nStatus: " + response.status, 3);
            }
        });
    });
}

$(document).on('click', '#button-logout', function(){
   logout();
});

$(document).on('click', '#button-user-registration', function(){
   window.location.replace("userRegistration.html");
});

$(document).on('click', '#logo-img', function(e){
    e.preventDefault();
    if(document.URL.indexOf("home.html") == -1)
        window.location.replace("home.html");
});

$(document).ready(function(){
    getCurrentUser();
});
