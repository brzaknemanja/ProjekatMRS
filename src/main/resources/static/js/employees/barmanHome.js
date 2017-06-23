/**
 * Created by brzak on 23.6.17..
 */


function getDrinkOrders() {
    $.ajax({
        url: "/tableOrder/getBarmanDrinks",
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
            showDrinkOrders(data)
        },
        error: function(response, textStatus){
            getToastr("", "Dobavljanje narudzbina neuspešno! \nStatus: " + response.status, 3);
        }
    });
}


function showDrinkOrders(orders)
{

    console.log(orders);
    $("#waiting-orders").remove();
    $("#waiting-header").remove();

    $("#orders").append("<h2 id='waiting-header'>New orders: </h2>")
    var table = "<table id = 'waiting-orders' class='table table-bordered'>" +
        "<thead> <tr> <th>Name</th><th>Description</th><th>Amount</th> <th>State</th> <th>Prepare</th> </tr> </thead><tbody></tbody> </table>";

    $("#orders").append(table);

    for(var i = 0; i < orders.length ; i++)
    {
        var row = '<tr><td>' + orders[i].name + '</td><td>' + orders[i].description + '</td><td>' + orders[i].amount +
            "</td><td>" + orders[i].state +
            "</td><td><button type='button' id = '"+ i +"-btn-start'>Finish</button> </td></tr>";
        $("#waiting-orders tbody").append(row);
        $("#" + i +"-btn-start").click({param1: orders[i], param2: "Finished"},setState);
        $("#" + i +"-btn-start").bind('click',function () {
            var par = $(this).parent().parent(); //tr
            par.remove();

        });
    }

}