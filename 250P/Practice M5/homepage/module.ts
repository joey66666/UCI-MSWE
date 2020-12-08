class AlertMessage{
    text: String;
    date: Date;
    time: String;

    constructor(text: String, date: Date) {
        this.text = text;
        this.date = date;
        this.time = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds()
    }

    get() {
        return this.text + this.date.toDateString();
    }

    setText(text: String) {
        this.text = text
    }
}

export {AlertMessage as Message}

export const catString = (text: String = "Empty", date: Date, Time: String) => text + "\n" + date.toDateString() + "\n" + Time






