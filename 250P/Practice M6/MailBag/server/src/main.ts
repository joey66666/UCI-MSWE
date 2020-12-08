import path from "path";
import express, {Express, NextFunction, Request, Response} from "express";
import {serverInfo} from "./ServerInfo";
import * as IMAP from "./IMAP";
import * as SMTP from "./SMTP";
import * as Contacts from "./Contacts";
import {IContact} from "./Contacts";
import {fdatasyncSync} from "fs";
import {runInContext} from "vm";

const app: Express = express();

app.use(express.json());

app.use(
    express.static(path.join(__dirname, "../../client/dist"))
);

app.use(function (inRequest: Request, inResponse: Response, inNext: NextFunction) {
    inResponse.header("Access-Control-Allow-Origin", "*");
    inResponse.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
    inResponse.header("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept");
    inNext();
});

//List Mailboxes
app.get("/mailboxes",
    async (inRequest: Request, inResponse: Response) => {
        console.log(inRequest.method, inRequest.originalUrl)
        try {
            const imapWorker: IMAP.Worker = new IMAP.Worker(serverInfo);
            const mailboxes: IMAP.IMailbox[] = await imapWorker.listMailboxes();
            inResponse.json(mailboxes);
        } catch (inError) {
            inResponse.status(500)
            inResponse.send("error");
        }
    }
);

//List Messages
app.get("/mailboxes/:mailbox",
    async (inRequest: Request, inResponse: Response) => {
        console.log(inRequest.method, inRequest.originalUrl)
        try {
            const imapWorker: IMAP.Worker = new IMAP.Worker(serverInfo);
            const messages: IMAP.IMessage[] = await imapWorker.listMessages({
                mailbox: inRequest.params.mailbox
            });
            inResponse.json(messages);
        } catch (inError) {
            inResponse.status(500)
            inResponse.send("error");
        }
    }
);

//Get a Message
app.get("/messages/:mailbox/:id",
    async (inRequest: Request, inResponse: Response) => {
        console.log(inRequest.method, inRequest.originalUrl)
        try {
            const imapWorker: IMAP.Worker = new IMAP.Worker(serverInfo);
            const messageBody: string | undefined = await imapWorker.getMessageBody({
                mailbox: inRequest.params.mailbox,
                id: parseInt(inRequest.params.id, 10)
            });
            inResponse.send(messageBody);
        } catch (inError) {
            inResponse.status(500)
            inResponse.send("Error");
        }
    }
);

//Delete a Message
app.delete("/messages/:mailbox/:id",
    async (inRequest: Request, inResponse: Response) => {
        console.log(inRequest.method, inRequest.originalUrl)
        try {
            const imapWorker: IMAP.Worker = new IMAP.Worker(serverInfo);
            await imapWorker.deleteMessage({
                mailbox: inRequest.params.mailbox,
                id: parseInt(inRequest.params.id, 10)
            });
            inResponse.send("OK");
        } catch (inError) {
            inResponse.status(500)
            inResponse.send("Error");
        }
    }
);

//Send a Message
app.post("/messages",
    async (inRequest: Request, inResponse: Response) => {
        console.log(inRequest.method, inRequest.originalUrl)
        try {
            const smtpWorker: SMTP.Worker = new SMTP.Worker(serverInfo);
            console.log(inRequest.body)
            await smtpWorker.sendMessage(inRequest.body);
            inResponse.status(201);
            inResponse.send("OK");
        } catch (inError) {
            inResponse.status(500)
            inResponse.send("Error");
        }
    }
);

//List Contacts
app.get("/contacts",
    async (inRequest: Request, inResponse: Response) => {
        console.log(inRequest.method, inRequest.originalUrl)
        try {
            const contactsWorker: Contacts.Worker = new Contacts.Worker();
            const contacts: IContact[] = await contactsWorker.listContacts();
            inResponse.json(contacts);
        } catch (inError) {
            inResponse.status(500)
            inResponse.send("Error");
        }
    }
);

//Add a Contact
app.post("/contacts",
    async (inRequest: Request, inResponse: Response) => {
        console.log(inRequest.method, inRequest.originalUrl)
        try {
            const contactsWorker: Contacts.Worker = new Contacts.Worker();
            console.log(inRequest.body)
            const contact: IContact = await contactsWorker.addContact(inRequest.body);
            inResponse.status(201);
            inResponse.json(contact);
        } catch (inError) {
            inResponse.status(500)
            inResponse.send("Error");
        }
    }
);

//Delete a Contact
app.delete("/contacts/:id",
    async (inRequest: Request, inResponse: Response) => {
        console.log(inRequest.method, inRequest.originalUrl)
        try {
            const contactsWorker: Contacts.Worker = new Contacts.Worker();
            await contactsWorker.deleteContact(inRequest.params.id);
            inResponse.send("OK");
        } catch (inError) {
            inResponse.status(500)
            inResponse.send("Error");
        }
    }
);

//Update a Contact
app.put("/contacts/:id",
    async (inRequest: Request, inResponse: Response) => {
        console.log(inRequest.method, inRequest.originalUrl)
        try {
            const contactsWorker: Contacts.Worker = new Contacts.Worker();
            await contactsWorker.updateContact(inRequest.params.id, inRequest.body);
            inResponse.send("OK");
        } catch (inError) {
            inResponse.status(500)
            inResponse.send("Error");
        }
    }
);

app.listen(80, () => {
        console.log("Server listen on http://localhost/");
    }
);