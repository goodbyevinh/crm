$(document).ready(function () {
    $("#select-status option").each( function (index, value) {
        if (value.innerHTML === $("#select-status").attr("statusName")) {
            $("#select-status option").attr("selected", true)
        } else {
            $("#select-status option").attr("selected", false)
        }
    })
    $(".btn-back").click( function () {
        history.back();
    })
    $(".btn-update-profile").click(function (){
        var This = $(this)
        var id = This.attr("taskId")
        data = {
            id: id,
            status: {
                id: $("#select-status").val()
            }
        }
        $.ajax({
            method: "POST",
            url: "http://localhost:8081/crm/api/task/update",
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