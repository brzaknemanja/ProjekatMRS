/**
 * Created by Ivana Zeljkovic on 09-Apr-17.
 */

function getToastr(text1, text2, flag){
    toastr.options = {
        "closeButton": false,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": "toast-top-center",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
    if(flag == 1)
        toastr.success(text1, text2);
    else if(flag == 2)
        toastr.warning(text1, text2);
    else
        toastr.error(text1, text2);
}

function getFormData(dom_query){
    var formData = {};
    var s_data = $(dom_query).serializeArray();
    //transformacija u jednostavne kljuc/vrednost objekte
    for(var i = 0; i<s_data.length; i++){
        var record = s_data[i];
        formData[record.name] = record.value;
    }
    return formData;
}

function validateEmail(email) {
    var regex = new RegExp("^([_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}))$");
    if(regex.test(email))
        return true;
    else
        return false;
}

function validateRegistrationData(data){
    var name = data["name"];
    var lastname = data["lastname"];
    var username = data["username"];
    var password = data["password"];
    var password2 = data["password2"];
    if(localStorage.getItem("registrationType") == null)
        var email = data["email"];

    if(name.trim() == "") {
        getToastr("Niste uneli ime!", "Nepotpuna forma za registraciju", 2);
        return false;
    }

    if(name.length < 2 || name.length > 20) {
        getToastr("Ime mora da sadrži izmedju 2 i 20 karaktera!", "Nevalidni podaci u formi za registraciju", 2);
        return false;
    }

    if(lastname.trim() == "") {
        getToastr("Niste uneli prezime!", "Nepotpuna forma za registraciju", 2);
        return false;
    }

    if(lastname.trim().length < 2 || lastname.trim().length > 30) {
        getToastr("Ime mora da sadrži izmedju 2 i 30 karaktera!", "Nevalidni podaci u formi za registraciju", 2);
        return false;
    }

    if(localStorage.getItem("registrationType") == null){
        if(!validateEmail(email.trim())) {
            getToastr("Neispravan format e-mail adrese!", "Nevalidni podaci u formi za registraciju", 2);
            return false;
        }
    }

    if(username.trim() == "") {
        getToastr("Niste uneli korisničko ime!", "Nepotpuna forma za registraciju", 2);
        return false;
    }

    if(username.trim().length < 3) {
        getToastr("Korisničko ime mora da sadrži bar 3 karaktera!", "Nevalidni podaci u formi za registraciju", 2);
        return false;
    }

    if(password.trim() == "") {
        getToastr("Niste uneli lozinku!", "Nepotpuna forma za registraciju", 2);
        return false;
    }

    if(password.trim().length < 3) {
        getToastr("Lozinka mora da sadrži bar 3 karaktera!", "Nevalidni podaci u formi za registraciju", 2);
        return false;
    }

    if(password.trim() != password2.trim()) {
        getToastr("Unosi za lozinku moraju biti isti!", "Nevalidni podaci u formi za registraciju", 2);
        return false;
    }

    if(localStorage.getItem("registrationType") == null)
        localStorage.removeItem("registrationType");
    return true;
}