import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {UserView} from '../model/UserView';
import {User} from '../model/User';
import {REST_API} from '../common/API';
import {Observable, throwError} from 'rxjs';
import 'rxjs-compat/add/operator/catch';
import {MatDialog} from '@angular/material';
import {ErrorService} from '../utils/error-service';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private dialog: MatDialog) {
  }

  getUsers(): Observable<UserView[]> {
    return this.http.get<UserView[]>(REST_API + 'person')
      .catch((e: any) => throwError(ErrorService.handleError(e, this.dialog)));
  }

  insertUser(user: User): Observable<User> {
    return this.http.post<any>(REST_API + 'person', user)
      .catch((e: any) => throwError(ErrorService.handleError(e, this.dialog)));
  }

  getUserById(id: number): Observable<UserView> {
    return this.http.get<UserView>(REST_API + 'person' + id)
      .catch((e: any) => throwError(ErrorService.handleError(e, this.dialog)));
  }

}
