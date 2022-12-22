$(document).ready(function () {
    $(".btn-add-task").click(function (){
        data = {
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
        console.log(data)
        $.ajax({
            method: "POST",
            url: "http://localhost:8081/crm/api/task/add",
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