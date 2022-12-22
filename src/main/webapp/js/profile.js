$(document).ready(function () {
    $(".btn-edit").click(function (){
        var This = $(this)
        var id = This.attr("taskId")
        This.attr("href", This.attr("href") + "?id=" + id)
    });
})