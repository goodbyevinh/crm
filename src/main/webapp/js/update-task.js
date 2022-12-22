$(document).ready(function () {
    $(".btn-update-task").click(function (){
        var This = $(this)
        var id = This.attr("taskId")
        data = {
            id: id,
            job: {
                id: $("#select-job").val()
            },
            name: $("#name").val(),
            user: {
                id: $("#select-user").val()
            },
            startDate: $("#startDate").val(),
            endDate: $("#endDate").val()
        }

        $.ajax({
            method: "POST",
            url: "http://localhost:8081/crm/api/task/update",
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