import {MatDialog} from '@angular/material';
import {ErrorHandler} from '../model/ErrorHandler';
import {ErrorHandlingComponent} from '../error-handling/error-handling.component';

export class ErrorService{
  private static errorHandler: ErrorHandler;
  constructor() {
  }

  public static handleError(error: any, dialog: MatDialog): void {
      console.log(error.error);
      this.errorHandler = new ErrorHandler(error.error.details, error.error.entity, error.error.requestedUri,
      error.error.statusMessage, error.error.statusCode, error.error.timestamp);
      if (JSON.stringify(this.errorHandler) === '{}') {
        this.showErrorsDetails(dialog, null, null, error.status, 'An error occurred when making the request', null, null);
      } else {
        this.showErrorsDetails(dialog, this.convertToDate(this.errorHandler.timestamp), this.errorHandler.entity,
          this.errorHandler.statusCode, this.errorHandler.statusMessage, this.errorHandler.requestedUri, this.errorHandler.details[0]);
      }
  }

  private static showErrorsDetails(dialog: MatDialog, timestamp: string, entity: string, statusCode: number,
                    statusMessage: string, requestedUri: string, details: string): void {
    dialog.open(ErrorHandlingComponent, {
      data: {timestamp: timestamp, resource: entity, status: statusCode, message: statusMessage, path: requestedUri, details: details},
      width: '650px'
    });
  }
  private static convertToDate(date: number): string {
    const newDate = new Date(date);
    return  newDate.toISOString().split('T')[0];
  }
}
