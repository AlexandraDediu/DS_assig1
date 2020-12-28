import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatDialog} from '@angular/material';
import {Observable, throwError} from 'rxjs';
import {REST_API} from '../common/API';
import {ErrorService} from '../utils/error-service';
import {Caregiver} from '../model/Caregiver';
import {Patient} from '../model/Patient';

@Injectable({
  providedIn: 'root'
})

export class CaregiverService {

  private baseURL = 'http://localhost:8080/api/caregivers';
  private complexUrl = 'http://localhost:8080/api/caregivers/patients';

  constructor(private http: HttpClient, private dialog: MatDialog) {
  }

  getCaregivers(): Observable<Caregiver[]> {
    return this.http.get<Caregiver[]>(REST_API + 'caregivers')
      .catch((e: any) => throwError(ErrorService.handleError(e, this.dialog)));
  }

  addCaregiver(caregiver: Caregiver): Observable<Caregiver> {
    return this.http.post<any>(REST_API + 'caregivers', caregiver)
      .catch((e: any) => throwError(ErrorService.handleError(e, this.dialog)));
  }

  getCaregiverById(id: number): Observable<Caregiver> {
    return this.http.get<Caregiver>(`${this.baseURL}/${id}`)
      .catch((e: any) => throwError(ErrorService.handleError(e, this.dialog)));
  }

  updateCaregiver(id: number, caregiver: Caregiver): Observable<Response> {
    return this.http.put<Response>(`${this.baseURL}/${id}`, caregiver)
      .catch((e: any) => throwError(ErrorService.handleError(e, this.dialog)));
  }

  deleteCaregiver(id: number): Observable<Object> {
    return this.http.delete(`${this.baseURL}/${id}`);
  }

  getPatientsByCaregiverId(id: number): Observable<Patient[]> {
    return this.http.get<Patient[]>(`${this.complexUrl}/${id}`)
      .catch((e: any) => throwError(ErrorService.handleError(e, this.dialog)));
  }

}
