import { Message, catString } from "./module.js";
var message = new Message("Hello Web Programming", new Date());
message.setText("Hello Practice M5");
var text = message.text, date = message.date, time = message.time;
var messageString = catString(text, date, time);
alert(messageString);
