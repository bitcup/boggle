// based on source: https://www.mkyong.com/spring-boot/spring-boot-ajax-example/

$(document).ready(function () {

    $("#boggle-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    var board = {};
    board["letters"] = $("#boggle-grid").val();
    board["minWordLength"] = $("#boggle-min-word-length").val();

    $("#btn-solve").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/v1/solve",
        data: JSON.stringify(board),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = "<h5>Found " + data["validWords"].length + " valid words in " + data["millis"] + " ms</h5>"
                + "<p>" + data["validWords"].join(", ") + "</p>";
            $('#results').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-solve").prop("disabled", false);
        },
        error: function (e) {
            var json = "<h4>Error Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#results').html(json);
            console.log("ERROR : ", e);
            $("#btn-solve").prop("disabled", false);
        }
    });

}
