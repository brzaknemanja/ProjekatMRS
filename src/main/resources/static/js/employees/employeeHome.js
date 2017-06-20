/**
 * Created by brzak on 16.6.17..
 */

var user;

function home(currentUser) {

    user = currentUser;

    $.get("/navbars/employeeNavbarPart.html", function(data){
        $("#div_navbar").append(data);
    });

    if(currentUser.type == "Waiter"){
        getTables();
        getTableOrders();
    }
}