import Mail from "nodemailer/lib/mailer";
import * as nodemailer from "nodemailer";
import {SendMailOptions, SentMessageInfo} from "nodemailer";

import {IServerInfo} from "./ServerInfo";

// const nodemailer = require("nodemailer");

export class Worker {
    private static serverInfo: IServerInfo;

    constructor(inServerInfo: IServerInfo) {
        console.log("SMTP.Worker.constructor", inServerInfo);
        Worker.serverInfo = inServerInfo;
    }

    public sendMessage(inOptions: SendMailOptions): Promise<string> {
        console.log("SMTP.Worker.sendMessage()", inOptions);
        return new Promise((inResolve, inReject) => {
            const transport: Mail = nodemailer.createTransport(Worker.serverInfo.smtp);
            transport.sendMail(
                inOptions,
                (inError: Error | null, inInfo: SentMessageInfo) => {
                    if (inError) {
                        console.log("SMTP.Worker.sendMessage(): Error", inError);
                        inReject(inError);
                    } else {
                        console.log("SMTP.Worker.sendMessage(): OK", inInfo);
                        inResolve(inInfo);
                    }
                }
            );
        });

    }
}