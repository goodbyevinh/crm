//$ => đại diện thư viên jquery
$(document).ready(function (){

    $(".btn-delete").click(function (){
        var This = $(this)
        var id = This.attr("roleId")
        $.ajax({
            method: "post",
            url: "http://localhost:8081/crm/api/role/delete?id=" + id,
        }).done(function( data ) {
            if (data.success = true) {
                This.closest("tr").remove()
            } else {

            }
        });
    });
    $(".btn-update").click(function (){
        var This = $(this)
        var id = This.attr("roleId")
        This.attr("href", This.attr("href") + "?id=" + id)
    });
})