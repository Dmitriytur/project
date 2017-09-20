function validateEmail(email) {
  var re = /^[^@\s]+@[^@\s]+\.[^@\s]+$/;
  return re.test(email);
}

function validateUsername(username) {
  var re = /^[A-Za-z][A-Za-z0-9]+$/;
  return re.test(username);
}


function validatePassword(password) {
  var re = (/^(?=.*\d)(?=.*[a-z])[0-9a-zA-Z]{6,}$/);
  return re.test(password);
}


function setError(selector, message){
    $(selector).addClass('has-error');
    $(selector + " .help-block").text(message);
}

function resetError(selector){
    $(selector).removeClass('has-error');
    $(selector + " .help-block").text("");
}


$(".thumb").click(function(event) {
    var target = $(event.target);
    var src = target.attr("src");
    $("#primary").attr("src",src);
    $(".thumb.active").removeClass('active');
    $(event.target).addClass("active");
});

$("#btnRegister").click(function(event) {
    var formIsValid = false;
    var username = $("#username input").val();

    if (!validateUsername(username)){
        formIsValid = false;
        setError("#username", "Username must starts with letter and contain only letters and numbers");
    } else if (username.length < 5 || username.length > 20){
        formIsValid = false;
        setError("#username", "Username must be from 5 to 20 characters");
    } else {
        formIsValid = true;
        resetError("#username");
    }


    var email = $("#email input").val();
    if (!validateEmail(email)){
        formIsValid = false;
        setError("#email", "Enter correct email");
    } else {
        formIsValid = true;
        resetError("#email");
    }


    var password = $("#password input").val();
    var confirmPassword = $("#confirmPassword input").val();

    if (!validatePassword(password)){
        formIsValid = false;
        setError("#password", "Password must contain at least 6 characters, at least 1 number at least 1 uppercase character and only lettern and digits");
    } else if (password != confirmPassword){
        formIsValid = false;
        setError("#password", "");
        setError("#confirmPassword", "Enter the same password in both password fields");
    } else {
        formIsValid = true;
        resetError("#password");
        resetError("#confirmPassword");
    }
    if (formIsValid){
        var params = {username: username, password: password, email: email};
        $.ajax({
            url: '/page/register',
            type: 'POST',
            data: jQuery.param( params ),
            error: function(response){
                $("#errorMessage p").text(response.responseText);
                $("#errorMessage").css('display','block');

            },
            success: function() {
                window.location = "/page/home";
            }
        });

    }
});
