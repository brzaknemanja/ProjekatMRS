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
    else if(currentUser.type == "Chef"){

        $("#orders").append("<div id='new-orders-div'></div>");
        $("#orders").append("<div id='preparing-orders-div'></div>");

        getDishOrders();
        getPreparingDishes();
    }
    else if(currentUser.type == "Barman") {
        getDrinkOrders();
    }
}