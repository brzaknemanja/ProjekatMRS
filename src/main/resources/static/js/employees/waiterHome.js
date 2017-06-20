/**
 * Created by brzak on 16.6.17..
 */

var tableOrder = {
    "orderItems" : [],
    "tableId" : null
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

            $("#close-order").click(function () {
                closeOrder();
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
                $("#" + element.segment).append("<tr><td>"+ table.name +"</td><td>" + table.chairNumber +"</td><td><button type='button' onclick=\"openOrderWindow('" + table.id + "','"+ table.name +"')\">Order</button></td></tr>");
            }

        });

    });
}

function openOrderWindow(tableId, tableName) {

    $("#orderCollapse").collapse("show");

    $("#table-order-id").text(tableName);

    tableOrder.tableId = tableId;

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
                    data[i].description + '</td><td>' + data[i].price + '</td>'
                    +  "<td><form><input type='text' id = '"+ i +"-amount-drink'></form></td>"
                    +   "<td><button id = '"+ i +"-btn-drink'>Add</button></td></tr>";
                $("#drinksTable").append(red);
                $("#" + i +"-btn-drink").click({param1: data[i], param2: i},addDrink);
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
        "type" : "Dish",
        "amount" : amount
    }

    refreshOrder(orderItem);

}

function addDrink(event){

    var drink = event.data.param1;
    var amount = parseInt($("#" + event.data.param2 + "-amount-drink").val());

    if(isNaN(amount))
    {
        getToastr("", "Amount must be number!");
        return;
    }

    var orderItem = {
        "name" : drink.name,
        "price" : drink.price,
        "type" : "Drink",
        "amount" : amount
    }

    refreshOrder(orderItem);
}

function refreshOrder(orderItem) {

    var row = '<tr><td>' + orderItem.name + '</td><td>' +
        orderItem.price + '</td><td>' + orderItem.type + '</td><td>' + orderItem.amount +
        "</td><td><button type='button' class='remove-order-item'>Remove</button> </td></tr>";
    $("#orderTable tbody").append(row);
    $(".remove-order-item").bind('click',deleteRow);

}

function finishOrder() {

    collectTableData();

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
            tableOrder.tableId = null;
            $("#orderTable tbody tr").remove();;
            console.log("orders " + tableOrder);
            getTableOrders();
        },
        error: function(response, textStatus){
            getToastr("", "Dodavanje porudzbine neuspešno! \nStatus: " + response.status, 3);
        }
    });

    $("#orderCollapse").collapse("hide");


}


function collectTableData() {
    $("#orderTable > tbody > tr").each(function()
    {
        var orderItem = new Object();
        orderItem.name = $(this).children("td:nth-child(1)").text();
        orderItem.price = $(this).children("td:nth-child(2)").text();
        orderItem.type = $(this).children("td:nth-child(3)").text();
        orderItem.amount = $(this).children("td:nth-child(4)").text();
        tableOrder.orderItems.push(orderItem);
        console.log(orderItem);

    });



}

function closeOrder() {
    tableOrder.orderItems = [];
    tableOrder.tableId = null;
    $("#orderTable tbody tr").remove();
    $("#orderCollapse").collapse("hide");
}

function deleteRow() {
    var par = $(this).parent().parent(); //tr
    par.remove();
    console.log("sagasasg");
}


function getTableOrders() {

    $.ajax({
        url: "/tableOrder/getWaiterOrders",
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            showTableOrders(data);
        },
        error: function(response, textStatus){
            getToastr("", "Dobavljanje narudzbina neuspešno! \nStatus: " + response.status, 3);
        }
    });
}

function showTableOrders(tableOrders) {

    $("#tableOrders").empty();

    $("#tableOrders").append("<h1>Table orders:</h1>");

    for(var i = 0; i < tableOrders.length; i++){

        $("#tableOrders").append("<h2>Table: " + tableOrders[i].tableName + "</h2>")
        var table = "<table id='orders-table-" + i + "' class='table table-bordered'>" +
            "<thead> <tr> <th>Name</th> <th>Price</th> <th>Type</th> <th>Amount</th> <th>Remove</th> </tr> </thead><tbody></tbody> </table>";

        $("#tableOrders").append(table);

        var orderItems = tableOrders[i].orderItems;

        for(var j = 0; j < orderItems.length; j++){
            var row = '<tr><td>' + orderItems[j].name + '</td><td>' +
                orderItems[j].price + '</td><td>' + orderItems[j].type + '</td><td>' + orderItems[j].amount +
                "</td><td><button type='button' class='remove-order-item'>Remove</button> </td></tr>";
            $("#orders-table-" + i + " tbody").append(row);
        }
    }

}