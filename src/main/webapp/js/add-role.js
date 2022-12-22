$(document).ready(function () {
    $(".btn-add-role").click(function (){
        data = {
            name: $("#name").val(),
            description: $("#description").val()
        }
        $.ajax({
            method: "POST",
            url: "http://localhost:8081/crm/api/role/add",
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