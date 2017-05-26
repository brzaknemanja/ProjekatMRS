/**
 * Created by Katarina Cukurov on 09/04/2017.
 */

function regSystemManager(){
    formData = getFormData($("#regForm"));
    var success = Boolean(validateRegistrationData(formData));
    var reg = localStorage.getItem("registrationType");
    if(success){
        if(reg == "waiter"){
            var newWaiter = JSON.stringify(formData);
            $.ajax({
                url: "/waiter/registration",
                type: "POST",
                contentType: "application/json",
                data: newWaiter,
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
                    getToastr("Neuspešna registracija konobara!", "Status: " + response.status, 3);
                else
                    window.location.replace("home.html");
                }
            });
        }else if(reg == "chef"){
            var newChef = JSON.stringify(formData);
            $.ajax({
                url: "/chef/registration",
                type: "POST",
                contentType: "application/json",
                data: newChef,
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
                    getToastr("Neuspešna registracija kuvara!", "Status: " + response.status, 3);
                else
                    window.location.replace("home.html");
                }
            });
        }else if(reg == "barman"){
            var newBarman = JSON.stringify(formData);
            $.ajax({
             url: "/barman/registration",
             type: "POST",
             contentType: "application/json",
             data: newBarman,
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
                 getToastr("Neuspešna registracija šankera!", "Status: " + response.status, 3);
             else
                 window.location.replace("home.html");
             }
            });
        }
    }
    else
        return false;
}

$(document).on('click', '#regForm :button', function(e){
    e.preventDefault();
    regSystemManager();
});