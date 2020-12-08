"use strict";
var AlertMessage = /** @class */ (function () {
    function AlertMessage(text, date) {
        this.text = text;
        this.date = date;
    }
    AlertMessage.prototype.get = function () {
        return this.text + this.date.toDateString();
    };
    AlertMessage.prototype.set = function (text, date) {
        this.text = text;
        this.date = date;
    };
    return AlertMessage;
}());
var catString = function (text, date) {
    if (text === void 0) { text = "Empty"; }
    return text + "\n" + date.toDateString();
};
function pop() {
    var message = new AlertMessage("Hello Web Programming", new Date());
    message.set("Hello Practice M4", new Date());
    var text = message.text, date = message.date;
    alert(catString(text, date));
}
