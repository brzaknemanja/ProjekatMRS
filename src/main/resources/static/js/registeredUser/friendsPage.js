/**
 * Created by Ivana Zeljkovic on 14-Apr-17.
 */
var currentUser;

function setCurrentUser(user){
    currentUser = user;
    showFriends();
}

function addFriend(friendUsername){
    $.ajax({
        url: "/registeredUser/addFriend",
        type: "PUT",
        contentType: "text/plain",
        data: friendUsername,
        dataType: "json",
        beforeSend: function(request){
          request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            currentUser = data;
            localStorage.setItem("currentUserToken", response.getResponseHeader("Authorization"));
            showFriends();
        },
        error: function(response){
            getToastr("Neuspešno dodavanje prijatelja!", "Status: " + response.status, 3);
        }
    });
}

function deleteFriend(friendUsername){
    $.ajax({
        url: "/registeredUser/deleteFriend",
        type: "PUT",
        contentType: "text/plain",
        dataType: "json",
        data: friendUsername,
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            currentUser = data;
            localStorage.setItem("currentUserToken", response.getResponseHeader("Authorization"));
            showFriends();
        },
        error: function(response){
            getToastr("Neuspešno brisanje prijatelja/Nepostojeći prijatelj", "Status: " + response.status, 3);
        }
    });
}

function searchUsers(parameter){
    $.ajax({
        url: "/registeredUser/searchUsers/" + parameter,
        type: "GET",
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            var foundUsers = data;
            localStorage.setItem("currentUserToken", response.getResponseHeader("Authorization"));
            if(foundUsers.length == 0)
                getToastr("Nema korisnika koji zadovoljavaju parametar pretrage: " + parameter, "", 2);
            else
                showUsers(foundUsers);
        },
        error: function(response){
            getToastr("Neuspešno pretraživanje korisnika!", "Status: " + response.status, 3);
        }
    });
}

function acceptRequest(username){
    $.ajax({
        url: "/registeredUser/acceptRequest",
        type: "PUT",
        contentType: "text/plain",
        data: username,
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            currentUser = data;
            localStorage.setItem("currentUserToken", response.getResponseHeader("Authorization"));
            showFriends();
        },
        error: function(response){
            getToastr("Neuspešno prihvatanje zahteva za prijateljstvo/Nepostojeći zahtev", "Status: " + response.status, 3);
        }
    });
}

function deleteRequest(username){
    $.ajax({
        url: "/registeredUser/deleteRequest",
        type: "PUT",
        contentType: "text/plain",
        data: username,
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            currentUser = data;
            localStorage.setItem("currentUserToken", response.getResponseHeader("Authorization"));
            showFriends();
        },
        error: function(response){
            getToastr("Neuspešno odbijanje zahteva za prijateljstvo/Nepostojeći zahtev", "Status: " + response.status, 3);
        }
    });
}

function showFriends(){
    var friendshipList = (currentUser.friendships == null) ? [] : (currentUser.friendships instanceof Array ? currentUser.friendships : [currentUser.friendships]);
    var friendTable = $("#friend-table");
    friendTable.find("tbody").empty();
    var requestTable = $("#friend-request-table");
    requestTable.find("tbody").empty();

    $.each(friendshipList, function(index, friend){
        var tableBody = friendTable.find("tbody");
        if(friend.status == "Accepted") {
            var trFriend = "<tr>" +
                "<td>" + friend.name + " " + friend.lastname + "</td>" +
                "<td>" +
                    "<form class='form-group'>" +
                        "<input type='hidden' value='" + friend.username + "'>" +
                        "<button class='btn btn-danger' id='delete-friend-button'>" +
                        "<span class='glyphicon glyphicon-remove'></span> Ukloni </button>" +
                    "</form>" +
                "</td>" +
            "</tr>";
            tableBody.append(trFriend);
        }
        else if(friend.status == "Pending"){
            var trWaiting = "<tr>" +
                    "<td>" + friend.name + " " + friend.lastname + "</td>" +
                    "<td> Ceka se odgovor </td>" +
                "</tr>";
            tableBody.append(trWaiting);
        }
        else {
            var trFriendRequest = "<tr>" +
                    "<td>" + friend.name + " " + friend.lastname + "</td>" +
                    "<td>" +
                        "<form class='form-group'>" +
                            "<input type='hidden' value='" + friend.username + "'>" +
                            "<button class='btn btn-warning' id='accept-request-button'>" +
                            "<span class='glyphicon glyphicon-check'></span> Prihvati zahtev</button>" +
                        "</form>" +
                    "</td>" +
                    "<td>" +
                        "<form class='form-group'>" +
                            "<input type='hidden' value='" + friend.username + "'>" +
                            "<button class='btn btn-danger' id='delete-request-button'>" +
                            "<span class='glyphicon glyphicon-remove'></span> Odbij zahtev</button>" +
                        "</form>" +
                    "</td>" +
                "</tr>";
            requestTable.find("tbody").append(trFriendRequest);
        }
    });
}

function showUsers(users){
    var userList = (users == null) ? [] : (users instanceof Array ? users : [users]);
    $.each(userList, function(index, user){
        var alreadyFriends = false;
        var status = null;
        $.each(currentUser.friendships, function(indexF, friend){
            if(friend.username == user.username){
                alreadyFriends = true;
                status = friend.status;
                return false;
            }
        });

        var trUser = "<tr>" +
                "<td>" + user.name + " " + user.lastname + "</td>" +
                "<td>";
                if(!alreadyFriends)
                   trUser += "<form class='form-group'>" +
                        "<input type='hidden' value='" + user.username + "'>" +
                        "<button class='btn btn-warning' id='add-friend-button'>" +
                        "<span class='glyphicon glyphicon-plus'></span> Dodaj </button>" +
                    "</form>";
                else {
                    if(status == null)
                        trUser += "Odgovorite na zahtev";
                    else if(status == "Pending")
                        trUser += "Ceka se odgovor prijatelja na zahtev";
                    else
                        trUser += "<form class='form-group'>" +
                            "<input type='hidden' value='" + user.username + "'>" +
                            "<button class='btn btn-danger' id='delete-friend-button'>" +
                            "<span class='glyphicon glyphicon-remove'></span> Ukloni </button>" +
                        "</form>";
                }
                trUser += "</td>" +
            "</tr>";
        $("#user-table").find("tbody").append(trUser);
    });
}

$(document).on('click', '#search-user-button', function(e){
    e.preventDefault();
    var form = $(this).parent("form");
    var parameter = $("#search-user-form :text").val();
    if(parameter.trim() == "") {
        getToastr("Niste uneli parametar pretrage!", "Nepotpuna forma za pretraživanje korisnika", 2);
        return false;
    }
    $("#user-table").find("tbody").empty();
    searchUsers(parameter);
});

$(document).on('click', '#add-friend-button', function(e){
    e.preventDefault();
    var form = $(this).parents("form");
    var friendUsername = form.find("input[type=hidden]").val();
    $("#user-table").find("tbody").empty();
    $("#search-user-form").find("input[type=text]").val("");
    addFriend(friendUsername);
});

$(document).on('click', '#delete-friend-button', function(e){
    e.preventDefault();
    var form = $(this).parents("form");
    var friendUsername = form.find("input[type=hidden]").val();
    $("#user-table").find("tbody").empty();
    $("#search-user-form").find("input[type=text]").val("");
    deleteFriend(friendUsername);
});

$(document).on('click', '#accept-request-button', function(e){
   e.preventDefault();
   var form = $(this).parents("form");
   var friendUsername = form.find("input[type=hidden]").val();
   acceptRequest(friendUsername);
});

$(document).on('click', '#delete-request-button', function(e){
    e.preventDefault();
    var form = $(this).parents("form");
    var friendUsername = form.find("input[type=hidden]").val();
    deleteRequest(friendUsername);
});