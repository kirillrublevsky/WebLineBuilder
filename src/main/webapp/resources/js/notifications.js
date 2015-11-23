/* Notifications */

function alertPoints() {
    $('.bottom-right').notify({
        message: {text: "Less than 2 points added, we cannot build the line. Add more points"},
        type: 'danger'
    }).show();
}

function alertRange() {
    $('.bottom-right').notify({
        message: {text: "Coordinates exceed range"},
        type: 'danger'
    }).show();
}

function alertFormat() {
    $('.bottom-right').notify({
        message: {text: "Only integers are allowed"},
        type: 'danger'
    }).show();
}

function alertEmpty() {
    $('.bottom-right').notify({
        message: {text: "Please enter X and Y coordinates"},
        type: 'danger'
    }).show();
}

function successDraw() {
    $('.bottom-right').notify({
        message: {text: "Line is successfully drawn"},
        type: 'success'
    }).show();
}

function successAdd() {
    $('.bottom-right').notify({
        message: {text: "Point is successfully added"},
        type: 'success'
    }).show();
}

function successClear() {
    $('.bottom-right').notify({
        message: {text: "All points are deleted. You can start from scratch"},
        type: 'success'
    }).show();
}

