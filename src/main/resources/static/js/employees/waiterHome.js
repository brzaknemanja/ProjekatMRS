/**
 * Created by brzak on 16.6.17..
 */

function getTables() {

        $.ajax({
            url: "/restaurant/getTables",
            type: "GET",
            contentType: "text/plain",
            dataType: "json",
            beforeSend: function(request){
                request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
            },
            success: function(data, textStatus, response){
                console.log(data);
                showTables(data)
                localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
            },
            error: function(response, textStatus){
                getToastr("", "Dodavanje pica neuspešno! \nStatus: " + response.status, 3);
            }
        });
}

function showTables(tables) {

    user.tableSegments.forEach(function (element) {
        $("#restaurant-tables").append(
            "<table class='table table-condensed' id='"+ element.segment + "'>" + element.segment + " <tr> <th>Table name</th> <th>Seat num.</th> <th>Reserve</th> </tr> </table>"
        );

        tables.forEach(function (table) {

            if(table.segment == element.segment){
                $("#" + element.segment).append("<tr><td>"+ table.name +"</td><td>" + table.chairNumber +"</td><td><button type='button' onclick=\"reserve('" + table.id + "')\"'>Rezervisi</button></td></tr>");
            }

        });

    });
}

function reserve(table) {

    var data = {
        "orderItems" : [
            {
                "name" : "some drink",
                "price" : 300,
                "type" : "Drink",
                "amount" : 2
            }
        ]
    };

    $.ajax({
        url: "/tableOrder/create",
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(data),
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            console.log(data);
        },
        error: function(response, textStatus){
            getToastr("", "Dodavanje porudzbine neuspešno! \nStatus: " + response.status, 3);
        }
    });
}