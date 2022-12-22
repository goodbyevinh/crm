$(document).ready(function () {
    $(".btn-update-user").click(function (){
        var This = $(this)
        var id = This.attr("userId")
        data = {
            id: id,
            fullname: $("#fullname").val(),
            email: $("#email").val(),
            password: $("#password").val(),
            phoneNumber: $("#phone").val(),
            role: {
                id: $("#roles").val()
            }
        }
        console.log(data)
        $.ajax({
            method: "POST",
            url: "http://localhost:8081/crm/api/user/update",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function( result ) {
            if (result.success) {
                alert(result.description)
            } else {

            }
        });
    })
})