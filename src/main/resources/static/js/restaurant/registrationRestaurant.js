var allManagers;
var canvas;
var tables;

$(document).ready(function(){
    $('#menagerss').empty();
    $.ajax({
        url: "/managerRestaurant/getAll",
        type: "GET",
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function (data, textStatus, response) {
            allManagers = data;
            for(i = 0; i < data.length; i++)
            {
                $('#managerss').append('<option>' + data[i].username + '</option>');
            }
            localStorage.setItem("currentUserToken", response.getResponseHeader("Authorization"));
        }
    });


    $.ajax({
        url: "/restaurant/getKitchenTypes",
        type: "GET",
        dataType: "json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function (data, textStatus, response) {
            for(i = 0; i < data.length; i++)
            {
                $('#kitchen').append('<option>' + data[i] + '</option>');
            }
            localStorage.setItem("currentUserToken", response.getResponseHeader("Authorization"));
        }
    });

    canvas = new fabric.Canvas('canvas');

    canvas.add(new fabric.Line([250, 0, 250, 500], {
        stroke: 'black'
    }));
    canvas.add(new fabric.Line([500, 0, 500, 500], {
        stroke: 'black'
    }));
    canvas.add(new fabric.Line([750, 0, 750, 500], {
        stroke: 'black'
    }));
    canvas.add(new fabric.Line([1000, 0, 1000, 500], {
        stroke: 'black'
    }));
    canvas.add(new fabric.Line([0, 250, 1000, 250], {
        stroke: 'black'
    }));

    canvas.add(new fabric.Text('unutra - pusacki', {
        fontSize: 18,
        left: 70,
        top: 5
    }));
    canvas.add(new fabric.Text('unutra - nepusacki', {
        fontSize: 18,
        left: 70,
        top: 255
    }));
    canvas.add(new fabric.Text('sprat - pusacki', {
        fontSize: 18,
        left: 320,
        top: 5
    }));
    canvas.add(new fabric.Text('sprat - nepusacki', {
        fontSize: 18,
        left: 320,
        top: 255
    }));
    canvas.add(new fabric.Text('terasa - pusacki', {
        fontSize: 18,
        left: 570,
        top: 5
    }));
    canvas.add(new fabric.Text('terasa - nepusacki', {
        fontSize: 18,
        left: 570,
        top: 255
    }));
    canvas.add(new fabric.Text('basta - pusacki', {
        fontSize: 18,
        left: 820,
        top: 5
    }));
    canvas.add(new fabric.Text('basta - nepusacki', {
        fontSize: 18,
        left: 820,
        top: 255
    }));

})

function regRestaurant(){
    formData = getFormData($("#regRest"));
    var restaurant = '{\"name\":\"' + formData.name + '\",\"description\":\"' + formData.description + '\",\"username\":\"'
    + $('#managerss')[0].value + '\",\"street\":\"' + formData.street + '\",\"city\":\"' + formData.city + '\",\"kitchenType\":\"'
    + $('#kitchen')[0].value + '\"}';
    var objects = canvas.getObjects();
    tables = '{\"restaurant\":' + restaurant + ',\"tables\":[';
    for (var i = 0, len = canvas.size(); i < len; i++) {
        if(objects[i].fill == "#DF1F1F")
            tables += '{\"chairNumber\":2,';
        if(objects[i].fill == "#FF9A04")
            tables += '{\"chairNumber\":4,';
        if(objects[i].fill == "#FFE709")
            tables += '{\"chairNumber\":6,';
        if((objects[i].fill == "#DF1F1F") || (objects[i].fill == "#FF9A04") || (objects[i].fill == "#FFE709")){
            tables += '\"top\":' + objects[i].top + ',\"left\":' + objects[i].left + ',\"rotation\":' +
                        objects[i].angle;
            if(i + 1 == len)
                tables += '}';
            else
                tables +='},';
        }

    }
    tables += ']}';
    $.ajax({
        url: "/restaurant/registration",
        type: "POST",
        contentType: "application/json",
        data: restaurant,
        beforeSend: function(request){
            request.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success: function(data, textStatus, response){
                dodajStolove();
        },
        error: function(response, textStatus){
            if(response.status != 201)
                getToastr("Neuspešna registracija restorana!", "Status: " + textStatus + ", greška:" + response.status, 3);
            else{
                window.location.replace("home.html");
            }
        }
    });
}

function dodajStolove(){
    $.ajax({
        url: "/restaurant/addTables",
        type: "POST",
        contentType: "application/json",
        data: tables,
        beforeSend: function(req){
            req.setRequestHeader("Authorization", localStorage.getItem("currentUserToken"));
        },
        success:function(data, textStatus, response){
            localStorage.setItem("currentUserToken",response.getResponseHeader("Authorization"));
            window.location.replace("home.html");
        },
        error: function(response, text){
            if(response.status != 201)
                getToastr("Neuspešna konfiguracija stolova pri registraciji restorana!", "Status: " + response.status, 3);
            else
                window.location.replace("home.html");
        }
    });
}

$(document).on('click', '#button-registration-restaurant', function(e){
    e.preventDefault();
    regRestaurant();
});

function dva(){
    var kocka = new fabric.Rect({
        left: 1100,
        top: 50,
        fill: '#DF1F1F',
        width: 30,
        height: 30
    });
    canvas.add(kocka);
};

function cetiri(){
    var kocka = new fabric.Rect({
        left: 1100,
        top: 190,
        fill: '#FF9A04',
        width: 35,
        height: 35
    });
    canvas.add(kocka);
};

function sest(){
    var kocka = new fabric.Rect({
        left: 1100,
        top: 360,
        fill: '#FFE709',
        width: 40,
        height: 40
    });
    canvas.add(kocka);
};

$(document).on('click', '#dve-stolice', function(){
   dva();
});

$(document).on('click', '#cetiri-stolice', function(){
   cetiri();
});
$(document).on('click', '#sest-stolica', function(){
   sest();
});