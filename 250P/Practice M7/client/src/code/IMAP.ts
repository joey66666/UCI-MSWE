import axios, {AxiosResponse} from "axios";
import {config} from "./config";

export interface IMailbox {
    name: string,
    path: string
}

export interface IMessage {
    id: string,
    date: string,
    from: string,
    subject: string,
    body?: string
}

export class Worker {

    public async listMailboxes(): Promise<IMailbox[]> {
        console.log("listMailboxes()")
        const response: AxiosResponse = await axios.get(`${config.serverAddress}/mailboxes`);
        return response.data
    }

    public async listMessages(inMailbox: string): Promise<IMessage[]> {
        console.log("listMessages()");
        const response: AxiosResponse = await axios.get(`${config.serverAddress}/mailboxes/${inMailbox}`);
        return response.data;
    }

    public async getMessageBody(inID: string, inMailbox: string): Promise<string> {
        console.log("getMessageBody()", inID);
        const response: AxiosResponse = await axios.get(`${config.serverAddress}/messages/${inMailbox}/${inID}`);
        return response.data;
    }

    public async deleteMessage(inID: string, inMailbox: string): Promise<void> {
        console.log("deleteMessage()", inMailbox, inID);
        await axios.delete(`${config.serverAddress}/messages/${inMailbox}/${inID}`);
    }
}