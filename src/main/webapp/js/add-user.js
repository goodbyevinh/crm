$(document).ready(function () {
    $(".btn-add-user").click(function (){
        data = {
            fullname: $("#fullname").val(),
            email: $("#email").val(),
            password: $("#password").val(),
            phone: $("#phone").val(),
            country: $("#country").val(),
            role: {
                id: $("#roles").val()
            }
        }
        $.ajax({
            method: "POST",
            url: "http://localhost:8081/crm/api/user/add",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function( result ) {
            console.log(result)
            if (result.success) {
                alert(result.description)
            } else {

            }
        });
    })
})