window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

window.ajax = function (date, funcSuccess) {
    $.ajax({
        type: "POST",
        dataType: "json",
        data: date,
        success: funcSuccess
    });

}