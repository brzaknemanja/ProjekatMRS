/**
 * Created by Ivana Zeljkovic on 09-Apr-17.
 */
var filedChanged = "";

function showPersonalData(currentUser){
    $("#user-profile-name").text(currentUser.name);
    $("#user-profile-lastname").text(currentUser.lastname);
    $("#user-profile-username").text(currentUser.username);
    $("#user-profile-password").text("/");
    if(currentUser.type == "Registered")
    {
        var trEmail = "<tr>" +
                "<td>" +
                    "<strong><span class='glyphicon glyphicon-globe text-primary'></span> Email adresa </strong>" +
                "</td>" +
                "<td class='text-primary' id='user-profile-email'>" + currentUser.email + "</td>" +
                "<td></td>" +
            "</tr>";
        $("#profile-data").find("tbody").append(trEmail);
    }
    if(currentUser.type == "Waiter" || currentUser.type == "Chef" || currentUser.type == "Barman"){
        showCalendar(currentUser);
    }

}

function updateView(currentUser) {
    if(filedChanged == "name") {
        $("#user-profile-name").text($("#new-name").val());
        $("#profile-change-name-button").css('visibility', 'visible');
        $("#form-name").remove();
    }
    else if(filedChanged == "lastname") {
        $("#user-profile-lastname").text($("#new-lastname").val());
        $("#profile-change-lastname-button").css('visibility', 'visible');
        $("#form-lastname").remove();
    }
    else if(filedChanged == "password") {
        $("#user-profile-password").text("/");
        $("#profile-change-password-button").css('visibility', 'visible');
        $("#form-password").remove();
    }
    filedChanged = "";
}

function showCalendar(employee) {
    $.get("calendar.html", function(data){
        $("#work-calendar").replaceWith(data);
        var calendar = new CALENDAR();

        console.log(employee);
        calendar.init(null,employee.daySchedules);
    });

}

function changeName(){
    var name = $("#new-name").val();

    $.ajax({
        url: "/user/updateName",
        type: "PUT",
        contentType: "text/plain",
        data: name,
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            var currentUser = data;
            localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
            updateView(currentUser);
        },
        error: function(response){
            getToastr("", "Ažuriranje imena neuspešno! \nStatus: " + response.status, 3);
        }
    });
}

function changeLastname(){
    var lastname = $("#new-lastname").val();

    $.ajax({
        url: "/user/updateLastname",
        type: "PUT",
        contentType: "text/plain",
        data: lastname,
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            var currentUser = data;
            localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
            updateView(currentUser);
        },
        error: function(response){
            getToastr("", "Ažuriranje prezimena neuspešno! \nStatus: " + response.status, 3);
        }
    });
}

function changePassword(newPassword){
    var oldPassword = $("#old-password").val();
    var jsonPasswords = {
        "oldPassword":oldPassword,
        "newPassword":newPassword
    }

    $.ajax({
        url: "/user/updatePassword",
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(jsonPasswords),
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            var currentUser = data;
            localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
            updateView(currentUser);
        },
        error: function(response){
            getToastr("", "Ažuriranje lozinke neuspešno \nStatus: " + response.status, 3);
        }
    });
}

$(document).on('click', '#profile-change-name-button', function(){
    $("#profile-change-name-button").css('visibility', 'hidden');
    var trParent = $(this).parents("tr");
    var formName = "<td id='form-name' class='form-group'>" +
            "<input type='text' id='new-name' placeholder='novo ime'>" +
            "<button class='btn btn-warning' id='name-changed-button'>Potvrdi izmenu</button>" +
        "</td>";
    trParent.append(formName);
});

$(document).on('click', '#profile-change-lastname-button', function(){
    $("#profile-change-lastname-button").css('visibility', 'hidden');
    var trParent = $(this).parents("tr");
    var formLastname = "<td id='form-lastname' class='form-group'>" +
        "<input type='text' id='new-lastname' placeholder='novo prezime'>" +
        "<button class='btn btn-warning' id='lastname-changed-button'>Potvrdi izmenu</button>" +
        "</td>";
    trParent.append(formLastname);
});

$(document).on('click', '#profile-change-password-button', function(){
    $("#profile-change-password-button").css('visibility', 'hidden');
    var trParent = $(this).parents("tr");
    var formPassword = "<tr id='form-password'><td class='form-group'>" +
        "<input type='password' id='old-password' placeholder='stara lozinka'>" +
        "<input type='password' id='new-password' placeholder='nova lozinka'>" +
        "<input type='password' id='new-password-2' placeholder='nova lozinka'>" +
        "<button class='btn btn-warning' id='password-changed-button'>Potvrdi izmenu</button>" +
        "</td><td></td><td></td></tr>";
    $(formPassword).insertAfter(trParent);
});

$(document).on('click', '#password-changed-button', function(){
    var password1 = $("#new-password").val();
    var password2 = $("#new-password-2").val();
    if(password1.trim() != password2.trim()) {
        getToastr("Unosi za novu lozinku se ne poklapaju!", "Neispravni podaci u formi za ažuriranje lozinke", 2);
        return false;
    }
    changePassword(password1);
    filedChanged = "password";
});

$(document).on('click', '#lastname-changed-button', function(){
    changeLastname();
    filedChanged = "lastname";
});

$(document).on('click', '#name-changed-button', function(){
    changeName();
    filedChanged = "name";
});
