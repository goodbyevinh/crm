$(document).ready(function () {
    $(".btn-update-role").click(function (){
        var This = $(this)
        var id = This.attr("roleId")
        data = {
            id: id,
            name: $("#id").val(),
            description: $("#description").val(),
        }
        $.ajax({
            method: "POST",
            url: "http://localhost:8081/crm/api/role/update",
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