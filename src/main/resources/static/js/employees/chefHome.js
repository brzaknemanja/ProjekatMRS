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
            "</td><td><button type='button' class='start-order-item'>Start</button> </td></tr>";
        $("#waiting-orders tbody").append(row);
    }

}