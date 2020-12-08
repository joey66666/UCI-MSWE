"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    Object.defineProperty(o, k2, { enumerable: true, get: function() { return m[k]; } });
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.Worker = void 0;
var path = __importStar(require("path"));
// import Nedb from "nedb";
var Datastore = require("nedb");
var Worker = /** @class */ (function () {
    function Worker() {
        this.db = new Datastore({
            filename: path.join(__dirname, "contacts.db"),
            autoload: true
        });
    }
    Worker.prototype.listContacts = function () {
        var _this = this;
        return new Promise(function (inResolve, inReject) {
            _this.db.find({}, function (inError, inDocs) {
                if (inError) {
                    console.log("Contacts.Worker.listContacts() Error:", inError);
                    inReject(inError);
                }
                else {
                    console.log("Contacts.Worker.listContacts():", inDocs);
                    inResolve(inDocs);
                }
            });
        });
    };
    Worker.prototype.addContact = function (inContact) {
        var _this = this;
        return new Promise(function (inResolve, inReject) {
            _this.db.insert(inContact, function (inError, inNewDoc) {
                if (inError) {
                    console.log("Contacts.Worker.addContact() Error:", inError);
                    inReject(inError);
                }
                else {
                    console.log("Contacts.Worker.addContact(), Added: ", inNewDoc);
                    inResolve(inNewDoc);
                }
            });
        });
    };
    Worker.prototype.deleteContact = function (inID) {
        var _this = this;
        return new Promise(function (inResolve, inReject) {
            _this.db.remove({ _id: inID }, {}, function (inError, inNumRemoved) {
                if (inError) {
                    console.log("Contacts.Worker.deleteContact() Error:", inError);
                    inReject(inError);
                }
                else {
                    console.log("Contacts.Worker.deleteContact(), Deleted:", inNumRemoved.toString());
                    inResolve(inNumRemoved.toString());
                }
            });
        });
    };
    Worker.prototype.updateContact = function (inId, inContact) {
        var _this = this;
        return new Promise(function (inResolve, inReject) {
            console.log(inId, inContact);
            _this.db.update({ _id: inId }, { $set: { name: inContact.name, email: inContact.email } }, {}, function (inError, numPlaced) {
                if (inError) {
                    console.log("Contacts.Worker.updateContact() Error:", inError);
                    inReject(inError);
                }
                else {
                    console.log("Contacts.Worker.updateContact(), Updated: ", numPlaced.toString());
                    inResolve(numPlaced.toString());
                }
            });
        });
    };
    return Worker;
}());
exports.Worker = Worker;
