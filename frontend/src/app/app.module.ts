import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {
  MatIconModule,
  MatMenuModule,
  MatToolbarModule,
  MatButtonModule,
  MatTableModule,
  MatPaginatorModule,
  MatDialogModule
} from '@angular/material';
import {RouterModule} from '@angular/router';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {UsersComponent} from './users/users.component';
import {HomeComponent} from './views/home/home.component';
import {HttpClientModule} from '@angular/common/http';
import {HeaderComponent} from './header/header.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ErrorHandlingComponent } from './error-handling/error-handling.component';
import { MedicationListComponent } from './views/medication/medication-list/medication-list.component';
import { UpdateMedicationComponent } from './views/medication/update-medication/update-medication.component';
import {AppRoutingModule} from './app-routing.module';
import { CreateMedicationComponent } from './views/medication/create-medication/create-medication.component';
import { CaregiverListComponent } from './views/caregiver/caregiver-list/caregiver-list.component';
import { CreateCaregiverComponent } from './views/caregiver/create-caregiver/create-caregiver.component';
import { UpdateCaregiverComponent } from './views/caregiver/update-caregiver/update-caregiver.component';
import { PatientListComponent } from './views/patient/patient-list/patient-list.component';
import { CreatePatientComponent } from './views/patient/create-patient/create-patient.component';
import { UpdatePatientComponent } from './views/patient/update-patient/update-patient.component';
import { CreateMedicationPlanComponent } from './views/medication-plan/create-medication-plan/create-medication-plan.component';
import { UpdateMedicationPlanComponent } from './views/medication-plan/update-medication-plan/update-medication-plan.component';
import { MedicationPlanListComponent } from './views/medication-plan/medication-plan-list/medication-plan-list.component';
import { NotificationsComponent } from './views/notifications/notifications.component';

@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    HomeComponent,
    HeaderComponent,
    ErrorHandlingComponent,
    MedicationListComponent,
    UpdateMedicationComponent,
    CreateMedicationComponent,
    CaregiverListComponent,
    CreateCaregiverComponent,
    UpdateCaregiverComponent,
    PatientListComponent,
    CreatePatientComponent,
    UpdatePatientComponent,
    CreateMedicationPlanComponent,
    UpdateMedicationPlanComponent,
    MedicationPlanListComponent,
    NotificationsComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    HttpClientModule,
    MatPaginatorModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule
  ],
  entryComponents: [
    ErrorHandlingComponent
  ],
  exports: [
    MatPaginatorModule,
    MatTableModule,
    ErrorHandlingComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
