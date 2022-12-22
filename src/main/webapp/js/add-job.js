$(document).ready(function () {
    $(".btn-add-job").click(function (){
        data = {
            name: $("#name").val(),
            startDate: $("#startDate").val(),
            endDate: $("#endDate").val()
        }
        console.log(data)
        $.ajax({
            method: "POST",
            url: "http://localhost:8081/crm/api/job/add",
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