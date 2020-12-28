import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {MedicationListComponent} from './views/medication/medication-list/medication-list.component';
import {HomeComponent} from './views/home/home.component';
import {UpdateMedicationComponent} from './views/medication/update-medication/update-medication.component';
import {CreateMedicationComponent} from './views/medication/create-medication/create-medication.component';
import {CaregiverListComponent} from './views/caregiver/caregiver-list/caregiver-list.component';
import {CreateCaregiverComponent} from './views/caregiver/create-caregiver/create-caregiver.component';
import {UpdateCaregiverComponent} from './views/caregiver/update-caregiver/update-caregiver.component';
import {PatientListComponent} from './views/patient/patient-list/patient-list.component';
import {CreatePatientComponent} from './views/patient/create-patient/create-patient.component';
import {UpdatePatientComponent} from './views/patient/update-patient/update-patient.component';
import {MedicationPlanListComponent} from './views/medication-plan/medication-plan-list/medication-plan-list.component';
import {CreateMedicationPlanComponent} from './views/medication-plan/create-medication-plan/create-medication-plan.component';
import {UpdateMedicationPlanComponent} from './views/medication-plan/update-medication-plan/update-medication-plan.component';

const routes: Routes = [
  {path: '', component: HomeComponent},

  {path: 'medications-list', component: MedicationListComponent},
  {path: 'update-medication/:id', component: UpdateMedicationComponent},
  {path: 'create-medication', component: CreateMedicationComponent},

  {path: 'caregivers-list', component: CaregiverListComponent},
  {path: 'create-caregiver', component: CreateCaregiverComponent},
  {path: 'update-caregiver/:id', component: UpdateCaregiverComponent},

  {path: 'patients-list/:id', component: PatientListComponent},
  {path: 'create-patient', component: CreatePatientComponent},
  {path: 'update-patient/:id', component: UpdatePatientComponent},

  {path: 'medication-plans-list', component: MedicationPlanListComponent},
  {path: 'create-medication-plan', component: CreateMedicationPlanComponent},
  {path: 'update-medication-plan/:id', component: UpdateMedicationPlanComponent}


];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
