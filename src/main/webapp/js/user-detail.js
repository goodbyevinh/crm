$(document).ready(function () {
    var yetValue = $("#yet-rate-bar").html()
    var doingValue = $("#doing-rate-bar").html()
    var completedValue = $("#completed-rate-bar").html()

    $("#yet-bar").css("width", `${yetValue}`)
    $("#doing-bar").css("width", `${doingValue}`)
    $("#completed-bar").css("width", `${completedValue}`)

})