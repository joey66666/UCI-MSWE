var AlertMessage = /** @class */ (function () {
    function AlertMessage(text, date) {
        this.text = text;
        this.date = date;
        this.time = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
    }
    AlertMessage.prototype.get = function () {
        return this.text + this.date.toDateString();
    };
    AlertMessage.prototype.setText = function (text) {
        this.text = text;
    };
    return AlertMessage;
}());
export { AlertMessage as Message };
export var catString = function (text, date, Time) {
    if (text === void 0) { text = "Empty"; }
    return text + "\n" + date.toDateString() + "\n" + Time;
};
