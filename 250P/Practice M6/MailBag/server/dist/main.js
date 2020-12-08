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
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
var path_1 = __importDefault(require("path"));
var express_1 = __importDefault(require("express"));
var ServerInfo_1 = require("./ServerInfo");
var IMAP = __importStar(require("./IMAP"));
var SMTP = __importStar(require("./SMTP"));
var Contacts = __importStar(require("./Contacts"));
var app = express_1.default();
app.use(express_1.default.json());
app.use(express_1.default.static(path_1.default.join(__dirname, "../../client/dist")));
app.use(function (inRequest, inResponse, inNext) {
    inResponse.header("Access-Control-Allow-Origin", "*");
    inResponse.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
    inResponse.header("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept");
    inNext();
});
//List Mailboxes
app.get("/mailboxes", function (inRequest, inResponse) { return __awaiter(void 0, void 0, void 0, function () {
    var imapWorker, mailboxes, inError_1;
    return __generator(this, function (_a) {
        switch (_a.label) {
            case 0:
                console.log(inRequest.method, inRequest.originalUrl);
                _a.label = 1;
            case 1:
                _a.trys.push([1, 3, , 4]);
                imapWorker = new IMAP.Worker(ServerInfo_1.serverInfo);
                return [4 /*yield*/, imapWorker.listMailboxes()];
            case 2:
                mailboxes = _a.sent();
                inResponse.json(mailboxes);
                return [3 /*break*/, 4];
            case 3:
                inError_1 = _a.sent();
                inResponse.status(500);
                inResponse.send("error");
                return [3 /*break*/, 4];
            case 4: return [2 /*return*/];
        }
    });
}); });
//List Messages
app.get("/mailboxes/:mailbox", function (inRequest, inResponse) { return __awaiter(void 0, void 0, void 0, function () {
    var imapWorker, messages, inError_2;
    return __generator(this, function (_a) {
        switch (_a.label) {
            case 0:
                console.log(inRequest.method, inRequest.originalUrl);
                _a.label = 1;
            case 1:
                _a.trys.push([1, 3, , 4]);
                imapWorker = new IMAP.Worker(ServerInfo_1.serverInfo);
                return [4 /*yield*/, imapWorker.listMessages({
                        mailbox: inRequest.params.mailbox
                    })];
            case 2:
                messages = _a.sent();
                inResponse.json(messages);
                return [3 /*break*/, 4];
            case 3:
                inError_2 = _a.sent();
                inResponse.status(500);
                inResponse.send("error");
                return [3 /*break*/, 4];
            case 4: return [2 /*return*/];
        }
    });
}); });
//Get a Message
app.get("/messages/:mailbox/:id", function (inRequest, inResponse) { return __awaiter(void 0, void 0, void 0, function () {
    var imapWorker, messageBody, inError_3;
    return __generator(this, function (_a) {
        switch (_a.label) {
            case 0:
                console.log(inRequest.method, inRequest.originalUrl);
                _a.label = 1;
            case 1:
                _a.trys.push([1, 3, , 4]);
                imapWorker = new IMAP.Worker(ServerInfo_1.serverInfo);
                return [4 /*yield*/, imapWorker.getMessageBody({
                        mailbox: inRequest.params.mailbox,
                        id: parseInt(inRequest.params.id, 10)
                    })];
            case 2:
                messageBody = _a.sent();
                inResponse.send(messageBody);
                return [3 /*break*/, 4];
            case 3:
                inError_3 = _a.sent();
                inResponse.status(500);
                inResponse.send("Error");
                return [3 /*break*/, 4];
            case 4: return [2 /*return*/];
        }
    });
}); });
//Delete a Message
app.delete("/messages/:mailbox/:id", function (inRequest, inResponse) { return __awaiter(void 0, void 0, void 0, function () {
    var imapWorker, inError_4;
    return __generator(this, function (_a) {
        switch (_a.label) {
            case 0:
                console.log(inRequest.method, inRequest.originalUrl);
                _a.label = 1;
            case 1:
                _a.trys.push([1, 3, , 4]);
                imapWorker = new IMAP.Worker(ServerInfo_1.serverInfo);
                return [4 /*yield*/, imapWorker.deleteMessage({
                        mailbox: inRequest.params.mailbox,
                        id: parseInt(inRequest.params.id, 10)
                    })];
            case 2:
                _a.sent();
                inResponse.send("OK");
                return [3 /*break*/, 4];
            case 3:
                inError_4 = _a.sent();
                inResponse.status(500);
                inResponse.send("Error");
                return [3 /*break*/, 4];
            case 4: return [2 /*return*/];
        }
    });
}); });
//Send a Message
app.post("/messages", function (inRequest, inResponse) { return __awaiter(void 0, void 0, void 0, function () {
    var smtpWorker, inError_5;
    return __generator(this, function (_a) {
        switch (_a.label) {
            case 0:
                console.log(inRequest.method, inRequest.originalUrl);
                _a.label = 1;
            case 1:
                _a.trys.push([1, 3, , 4]);
                smtpWorker = new SMTP.Worker(ServerInfo_1.serverInfo);
                console.log(inRequest.body);
                return [4 /*yield*/, smtpWorker.sendMessage(inRequest.body)];
            case 2:
                _a.sent();
                inResponse.status(201);
                inResponse.send("OK");
                return [3 /*break*/, 4];
            case 3:
                inError_5 = _a.sent();
                inResponse.status(500);
                inResponse.send("Error");
                return [3 /*break*/, 4];
            case 4: return [2 /*return*/];
        }
    });
}); });
//List Contacts
app.get("/contacts", function (inRequest, inResponse) { return __awaiter(void 0, void 0, void 0, function () {
    var contactsWorker, contacts, inError_6;
    return __generator(this, function (_a) {
        switch (_a.label) {
            case 0:
                console.log(inRequest.method, inRequest.originalUrl);
                _a.label = 1;
            case 1:
                _a.trys.push([1, 3, , 4]);
                contactsWorker = new Contacts.Worker();
                return [4 /*yield*/, contactsWorker.listContacts()];
            case 2:
                contacts = _a.sent();
                inResponse.json(contacts);
                return [3 /*break*/, 4];
            case 3:
                inError_6 = _a.sent();
                inResponse.status(500);
                inResponse.send("Error");
                return [3 /*break*/, 4];
            case 4: return [2 /*return*/];
        }
    });
}); });
//Add a Contact
app.post("/contacts", function (inRequest, inResponse) { return __awaiter(void 0, void 0, void 0, function () {
    var contactsWorker, contact, inError_7;
    return __generator(this, function (_a) {
        switch (_a.label) {
            case 0:
                console.log(inRequest.method, inRequest.originalUrl);
                _a.label = 1;
            case 1:
                _a.trys.push([1, 3, , 4]);
                contactsWorker = new Contacts.Worker();
                console.log(inRequest.body);
                return [4 /*yield*/, contactsWorker.addContact(inRequest.body)];
            case 2:
                contact = _a.sent();
                inResponse.status(201);
                inResponse.json(contact);
                return [3 /*break*/, 4];
            case 3:
                inError_7 = _a.sent();
                inResponse.status(500);
                inResponse.send("Error");
                return [3 /*break*/, 4];
            case 4: return [2 /*return*/];
        }
    });
}); });
//Delete a Contact
app.delete("/contacts/:id", function (inRequest, inResponse) { return __awaiter(void 0, void 0, void 0, function () {
    var contactsWorker, inError_8;
    return __generator(this, function (_a) {
        switch (_a.label) {
            case 0:
                console.log(inRequest.method, inRequest.originalUrl);
                _a.label = 1;
            case 1:
                _a.trys.push([1, 3, , 4]);
                contactsWorker = new Contacts.Worker();
                return [4 /*yield*/, contactsWorker.deleteContact(inRequest.params.id)];
            case 2:
                _a.sent();
                inResponse.send("OK");
                return [3 /*break*/, 4];
            case 3:
                inError_8 = _a.sent();
                inResponse.status(500);
                inResponse.send("Error");
                return [3 /*break*/, 4];
            case 4: return [2 /*return*/];
        }
    });
}); });
//Update a Contact
app.put("/contacts/:id", function (inRequest, inResponse) { return __awaiter(void 0, void 0, void 0, function () {
    var contactsWorker, inError_9;
    return __generator(this, function (_a) {
        switch (_a.label) {
            case 0:
                console.log(inRequest.method, inRequest.originalUrl);
                _a.label = 1;
            case 1:
                _a.trys.push([1, 3, , 4]);
                contactsWorker = new Contacts.Worker();
                return [4 /*yield*/, contactsWorker.updateContact(inRequest.params.id, inRequest.body)];
            case 2:
                _a.sent();
                inResponse.send("OK");
                return [3 /*break*/, 4];
            case 3:
                inError_9 = _a.sent();
                inResponse.status(500);
                inResponse.send("Error");
                return [3 /*break*/, 4];
            case 4: return [2 /*return*/];
        }
    });
}); });
app.listen(80, function () {
    console.log("Server listen on http://localhost/");
});
