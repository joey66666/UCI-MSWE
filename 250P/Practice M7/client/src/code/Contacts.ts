import axios, {AxiosResponse} from "axios";
import {config} from "./config";

export interface IContact {
    _id?: number,
    name: string,
    email: string
}

export class Worker {
    public async listContacts(): Promise<IContact[]> {
        console.log("listContacts()");
        const response: AxiosResponse = await axios.get(`${config.serverAddress}/contacts`);
        return response.data;
    }

    public async addContact(inContact: IContact): Promise<IContact> {
        console.log("addContact()", inContact);
        const response: AxiosResponse = await axios.post(`${config.serverAddress}/contacts`, inContact);
        return response.data;
    }

    public async deleteContact(inId): Promise<void> {
        console.log("deleteContact()", inId);
        await axios.delete(`${config.serverAddress}/contacts/${inId}`);
    }

    public async updateContact(inId, inContact: IContact): Promise<void> {
        console.log("updateContact()", inId, inContact);
        await axios.put(`${config.serverAddress}/contacts/${inId}`, inContact);
    }
}