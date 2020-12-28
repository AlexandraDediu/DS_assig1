import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatDialog} from '@angular/material';
import {Observable, throwError} from 'rxjs';
import {REST_API} from '../common/API';
import {ErrorService} from '../utils/error-service';
import {Medication} from '../model/Medication';


@Injectable({
  providedIn: 'root'
})

export class MedicationService {

  private baseURL = 'http://localhost:8080/api/medications';

  constructor(private http: HttpClient, private dialog: MatDialog) {
  }

  getMedications(): Observable<Medication[]> {
    return this.http.get<Medication[]>(REST_API + 'medications')
      .catch((e: any) => throwError(ErrorService.handleError(e, this.dialog)));
  }

  addMedication(medication: Medication): Observable<Medication> {
    return this.http.post<any>(REST_API + 'medications', medication)
      .catch((e: any) => throwError(ErrorService.handleError(e, this.dialog)));
  }

  getMedicationById(id: number): Observable<Medication> {
    return this.http.get<Medication>(`${this.baseURL}/${id}`)
      .catch((e: any) => throwError(ErrorService.handleError(e, this.dialog)));
  }

  updateMedication(id: number, medication: Medication): Observable<Response> {
    return this.http.put<Response>(`${this.baseURL}/${id}`, medication)
      .catch((e: any) => throwError(ErrorService.handleError(e, this.dialog)));
  }

  deleteMedication(id: number): Observable<Object> {
    return this.http.delete(`${this.baseURL}/${id}`);
  }

}
