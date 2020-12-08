class AlertMessage {
    text: String;
    date: Date;

    constructor(text: String, date: Date) {
        this.text = text;
        this.date = date;
    }

    get() {
        return this.text + this.date.toDateString();
    }

    set(text: String, date: Date) {
        this.text = text
        this.date = date
    }
}

const catString = (text: String = "Empty", date: Date) => text + "\n" + date.toDateString()

function pop() {
    let message: AlertMessage = new AlertMessage("Hello Web Programming", new Date())
    message.set("Hello Practice M4", new Date())
    const {text, date} = message
    alert(catString(text, date))
}




