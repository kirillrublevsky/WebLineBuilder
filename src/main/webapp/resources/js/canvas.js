/* Canvas properties */

var canvas = document.getElementById("canvas"),
    context = canvas.getContext("2d");

var width = canvas.width;
var height = canvas.height;
var halfWidth = width / 2;
var halfHeight = height / 2;

function clear() {
    context.fillStyle = "#fff";
    context.translate((-1) * halfWidth, (-1) * halfHeight);
    context.clearRect(0, 0, width, height);
    context.fillRect(0, 0, width, height);
}

function grid() {
    context.fillStyle = "#fff";
    context.fillRect(0, 0, width, height);

    for (var x = 0.5; x < width; x += 10) {
        context.moveTo(x, 0);
        context.lineTo(x, height);
    }

    for (var y = 0.5; y < height; y += 10) {
        context.moveTo(0, y);
        context.lineTo(width, y);
    }

    context.strokeStyle = "#eee";
    context.stroke();

    context.beginPath();
    context.moveTo(0, halfHeight);
    context.lineTo(width, halfHeight);
    context.moveTo(halfWidth, 0);
    context.lineTo(halfWidth, height);
    context.strokeStyle = "#000";
    context.stroke();

    context.font = "bold 10px sans-serif";
    context.fillStyle = "#000";
    context.translate(halfWidth, halfHeight);
    context.fillText("-" + halfWidth, (-1) * halfWidth + 10, 10);
    context.fillText("" + halfWidth, halfWidth - 25, 10);
    context.fillText("-" + halfHeight, 5, halfHeight - 10);
    context.fillText("" + halfHeight, 5, (-1) * halfHeight + 10);
    context.fillText("0", 5, 10);
}

function draw(points) {
    if (points.length < 2) {
        alertPoints();
    } else {
        context.beginPath();
        for (var i = 0; i < points.length - 1; i++) {
            context.moveTo(points[i].x, (-1) * points[i].y);
            context.lineTo(points[i + 1].x, (-1) * points[i + 1].y);
        }
        context.strokeStyle = "#000";
        context.stroke();
        successDraw();
    }
}
