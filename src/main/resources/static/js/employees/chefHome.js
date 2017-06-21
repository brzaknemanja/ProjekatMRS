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
    $("#orders").append("<h2>New orders: </h2>")
    var table = "<table id = 'waiting-orders' class='table table-bordered'>" +
        "<thead> <tr> <th>Name</th><th>Amount</th> <th>State</th> <th>Prepare</th> </tr> </thead><tbody></tbody> </table>";

    $("#orders").append(table);

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
            console.log(data);
        },
        error: function(response, textStatus){
            getToastr("", "Dobavljanje narudzbina neuspešno! \nStatus: " + response.status, 3);
        }
    });
}