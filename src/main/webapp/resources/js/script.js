/* jQuery and AJAX */

$(document).ready(function () {

    grid();

    $("#draw").click(function (e) {
        e.preventDefault();
        clear();
        grid();

        var points = [];
        $.ajax({
            type: 'GET',
            url: "JsonServlet",

            success: function (data) {
                $.each(data, function (index, object) {
                    var point = {'x': object.x, 'y': object.y};
                    points.push(point);
                });
                draw(points);
            },
            error: function (textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
            }
        });
    });

    $("#add").click(function (e) {
        e.preventDefault();

        var x = $("#x").val();
        var y = $("#y").val();
        var pattern = /^[0-9]+$/;

        if (x == "" || y == "") {
            alertEmpty();
        } else if (!pattern.test(Math.abs(x)) || !pattern.test(Math.abs(y))) {
            alertFormat();
        } else if (x > 400 || x < -400 || y > 250 || y < -250) {
            alertRange();
        } else {
            var point = {'x': x, 'y': y};
            $.ajax({
                type: 'POST',
                url: "JsonServlet",
                data: point,

                success: function (data) {
                    $("#x").val("");
                    $("#y").val("");
                    successAdd();
                },
                error: function (textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        }
    });

    $("#clear").click(function (e) {
        e.preventDefault();
        $.ajax({
            type: 'DELETE',
            url: "JsonServlet",

            success: function (data) {
                successClear();
            },
            error: function (textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
            }
        });
    });
});
