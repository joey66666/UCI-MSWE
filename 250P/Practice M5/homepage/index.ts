import {Message, catString} from "./module.js";


let message = new Message("Hello Web Programming", new Date())
message.setText("Hello Practice M5")
let {text, date, time} = message
let messageString = catString(text, date, time)
alert(messageString)
