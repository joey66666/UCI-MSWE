import * as path from "path";
// import Nedb from "nedb";

const Datastore = require("nedb");

export interface IContact {
    _id?: number,
    name: string,
    email: string
}

export class Worker {
    private db: Nedb;

    constructor() {
        this.db = new Datastore({
            filename: path.join(__dirname, "contacts.db"),
            autoload: true
        });
    }

    public listContacts(): Promise<IContact[]> {
        return new Promise((inResolve, inReject) => {
            this.db.find({}, (inError: Error, inDocs: IContact[]) => {
                if (inError) {
                    console.log("Contacts.Worker.listContacts() Error:", inError)
                    inReject(inError);
                } else {
                    console.log("Contacts.Worker.listContacts():", inDocs)
                    inResolve(inDocs);
                }
            });
        });

    }

    public addContact(inContact: IContact): Promise<IContact> {
        return new Promise((inResolve, inReject) => {
            this.db.insert(inContact, (inError: Error | null, inNewDoc: IContact) => {
                if (inError) {
                    console.log("Contacts.Worker.addContact() Error:", inError)
                    inReject(inError);
                } else {
                    console.log("Contacts.Worker.addContact(), Added: ", inNewDoc)
                    inResolve(inNewDoc);
                }
            });
        });

    }

    public deleteContact(inID: string): Promise<string> {
        return new Promise((inResolve, inReject) => {
            this.db.remove(
                {_id: inID},
                {},
                (inError: Error | null, inNumRemoved: number) => {
                    if (inError) {
                        console.log("Contacts.Worker.deleteContact() Error:", inError)
                        inReject(inError);
                    } else {
                        console.log("Contacts.Worker.deleteContact(), Deleted:", inNumRemoved.toString())
                        inResolve(inNumRemoved.toString());
                    }
                }
            );
        });
    }

    public updateContact(inId: string, inContact: IContact): Promise<string> {
        return new Promise((inResolve, inReject) => {
            console.log(inId, inContact)
            this.db.update(
                {_id: inId}, {$set: {name: inContact.name, email: inContact.email}}, {},
                function (inError: Error | null, numPlaced: number) {
                    if (inError) {
                        console.log("Contacts.Worker.updateContact() Error:", inError)
                        inReject(inError);
                    } else {
                        console.log("Contacts.Worker.updateContact(), Updated: ", numPlaced.toString());
                        inResolve(numPlaced.toString());
                    }
                }
            );
        });
    }
}