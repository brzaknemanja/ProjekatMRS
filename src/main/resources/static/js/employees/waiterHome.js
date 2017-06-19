/**
 * Created by brzak on 16.6.17..
 */

var tableOrder = {
    "orderItems" : []
};

$(document).ready(function(){


    $.get("/waiterTableOrders.html",function (data) {
        $("#orders").append(data);

        $.get("/waiterModals.html", function(data){
            $("#modals").append(data);

            $("#dishes").on("click",function () {
                $("#dishesModal").modal("show");
                populateDishes();
            });

            $("#drinks").click(function () {
                $("#drinksModal").modal("show");
                populateDrinks();
            });

            $("#finish-order").click(function () {
               finishOrder();
            });
        });
    });
});



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
                $("#" + element.segment).append("<tr><td>"+ table.name +"</td><td>" + table.chairNumber +"</td><td><button type='button' onclick=\"openOrderWindow('" + table.id + "')\"'>Order</button></td></tr>");
            }

        });

    });
}

function openOrderWindow(table) {

    $("#orderCollapse").collapse("show");







   /*var data = {
        "orderItems" : [
            {
                "name" : "some drink",
                "price" : 300,
                "type" : "Drink",
                "amount" : 2
            }
        ]
    };*/

    /*$.ajax({
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
    });*/

}

function populateDishes() {

    $.ajax({
        url: "/restaurant/getWaiterDishes",
        type: "PUT",
        contentType: "text/plain",
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            $("#dishesTable tbody tr").remove();
            for(var i = 0; i < data.length; i++){
                var red = '<tr><td>' + data[i].name + '</td><td>' +
                    data[i].description + '</td><td>' + data[i].price + '</td>'
                +  "<td><form><input type='text' id = '"+ i +"-amount-dish'></form></td>"
                 +   "<td><button id = '"+ i +"-btn-dish'>Add</button></td></tr>";
                $("#dishesTable").append(red);
                $("#" + i +"-btn-dish").click({param1: data[i], param2: i},addDish);

            }


        },
        error: function(response){
            getToastr("", "Dodavanje jela neuspesno! \nStatus: " + response.status, 3);
        }
    });
    
}


function populateDrinks() {
    $.ajax({
        url: "/restaurant/getWaiterDrinks",
        type: "PUT",
        contentType: "text/plain",
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            $("#drinksTable tbody tr").remove();
            for(var i = 0; i < data.length; i++){
                var red = '<tr><td>' + data[i].name + '</td><td>' +
                    data[i].description + '</td><td>' + data[i].price + '</td></tr>';
                $("#drinksTable").append(red);
            }
            localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
        },
        error: function(response, textStatus){
            getToastr("", "Dodavanje pica neuspešno! \nStatus: " + response.status, 3);
        }
    });

}


function addDish(event) {

    var dish = event.data.param1;
    var amount = parseInt($("#" + event.data.param2 + "-amount-dish").val());

    if(isNaN(amount))
    {
        getToastr("", "Amount must be number!");
        return;
    }

    var orderItem = {
        "name" : dish.name,
        "price" : dish.price,
        "type" : "Food",
        "amount" : amount
    }

    refreshOrder(orderItem);

}

function refreshOrder(orderItem) {

    var row = '<tr><td>' + orderItem.name + '</td><td>' +
        orderItem.price + '</td><td>' + orderItem.type + '</td><td>' + orderItem.amount +
        '</td></tr>';
    $("#orderTable tbody").append(row);

    tableOrder.orderItems.push(orderItem);
}

function finishOrder() {

    $.ajax({
        url: "/tableOrder/create",
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(tableOrder),
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            console.log(data);
            tableOrder.orderItems = [];
            $("#orderTable tbody tr").remove();;
            console.log("orders " + tableOrder);
        },
        error: function(response, textStatus){
            getToastr("", "Dodavanje porudzbine neuspešno! \nStatus: " + response.status, 3);
        }
    });

    $("#orderCollapse").collapse("hide");
}