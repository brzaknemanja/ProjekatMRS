/**
 * Created by brzak on 21.6.17..
 */

function getDishOrders(){


    $.ajax({
        url: "/tableOrder/getChefDishes",
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            showDishOrders(data);
        },
        error: function(response, textStatus){
            getToastr("", "Dobavljanje narudzbina neuspešno! \nStatus: " + response.status, 3);
        }
    });
}

function showDishOrders(orders)
{
    $("#waiting-orders").remove();
    $("#waiting-header").remove();

    $("#new-orders-div").append("<h2 id='waiting-header'>New orders: </h2>")
    var table = "<table id = 'waiting-orders' class='table table-bordered'>" +
        "<thead> <tr> <th>Name</th><th>Amount</th> <th>State</th> <th>Prepare</th> </tr> </thead><tbody></tbody> </table>";

    $("#new-orders-div").append(table);

    for(var i = 0; i < orders.length ; i++)
    {
        var row = '<tr><td>' + orders[i].name + '</td><td>' + orders[i].amount +
            "</td><td>" + orders[i].state +
            "</td><td><button type='button' id = '"+ i +"-btn-start'>Start</button> </td></tr>";
        $("#waiting-orders tbody").append(row);
        $("#" + i +"-btn-start").click({param1: orders[i], param2: "Preparing"},setState);
        $("#" + i +"-btn-start").bind('click',function () {
            var par = $(this).parent().parent(); //tr
            par.remove();

        });
    }

}


function getPreparingDishes() {
    $.ajax({
        url: "/tableOrder/getPreparingDishes",
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            showPreparingDishes(data);
        },
        error: function(response, textStatus){
            getToastr("", "Dobavljanje narudzbina neuspešno! \nStatus: " + response.status, 3);
        }
    });
}


function showPreparingDishes(orders) {

    $("#preparing-orders").remove();
    $("#preparing-header").remove();

    $("#preparing-orders-div").append("<h2 id = 'preparing-header'>Preparing orders: </h2>");
    var table = "<table id = 'preparing-orders' class='table table-bordered'>" +
        "<thead> <tr> <th>Name</th><th>Amount</th> <th>State</th> <th>Prepare</th> </tr> </thead><tbody></tbody> </table>";

    $("#preparing-orders-div").append(table);

    for(var i = 0; i < orders.length ; i++)
    {
        var row = '<tr><td>' + orders[i].name + '</td><td>' + orders[i].amount +
            "</td><td>" + orders[i].state +
            "</td><td><button type='button' id = '"+ i +"-btn-finished'>Finish</button> </td></tr>";
        $("#preparing-orders tbody").append(row);
        $("#" + i +"-btn-finished").click({param1: orders[i], param2: "Finished"},setState);
        $("#" + i +"-btn-finished").bind('click',function () {
            var par = $(this).parent().parent(); //tr
            par.remove();
        });
    }
}


function setState(event) {

    var item = event.data.param1;
    item.state = event.data.param2;

    $.ajax({
        url: "/tableOrder/setOrderState",
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(item),
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            if(data.state == 'Preparing'){
                getToastr(data.name, "Started preparing!",1);
                getPreparingDishes();
            }
            else if(data.state = 'Finished')
            {
                getToastr(data.name, "Finished preparing!",1);
            }

        },
        error: function(response, textStatus){
            getToastr("", "Promena stanja narudzbine neuspesna! \nStatus: " + response.status, 3);
        }
    });
}