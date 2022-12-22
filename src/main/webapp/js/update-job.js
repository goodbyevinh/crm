$(document).ready(function () {
    $(".btn-update-job").click(function (){
        var This = $(this)
        var id = This.attr("jobId")
        data = {
            id: id,
            name: $("#name").val(),
            startDate: $("#startDate").val(),
            endDate: $("#endDate").val()
        }
        console.log(data)
        $.ajax({
            method: "POST",
            url: "http://localhost:8081/crm/api/job/update",
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