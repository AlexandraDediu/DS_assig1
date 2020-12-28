export class ErrorHandler {
  timestamp: number;
  entity: string;
  requestedUri: string;
  details: Array<string>;
  statusCode: number;
  statusMessage: string;
  constructor(details: Array<string>, entity: string, requestedUri: string, statusMessage: string, statusCode: number, timestamp: number) {
    this.details = details;
    this.entity = entity;
    this.requestedUri = requestedUri;
    this.statusMessage = statusMessage;
    this.statusCode = statusCode;
    this.timestamp = timestamp;
  }
}
