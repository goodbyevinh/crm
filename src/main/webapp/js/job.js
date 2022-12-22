$(document).ready(function () {
    $(".btn-delete").click(function (){
        var This = $(this)
        var id = This.attr("jobId")
        $.ajax({
            method: "post",
            url: "http://localhost:8081/crm/api/job/delete?id=" + id,
        }).done(function( data ) {
            console.log(data)
            if (data.success = true) {
                This.closest("tr").remove()
            } else {

            }
        });
    });

    $(".btn-update").click(function (){
        var This = $(this)
        var id = This.attr("jobId")
        This.attr("href", This.attr("href") + "?id=" + id)
    });

    $(".btn-view").click(function (){
        var This = $(this)
        var id = This.attr("jobId")
        This.attr("href", This.attr("href") + "?id=" + id)
    });
})