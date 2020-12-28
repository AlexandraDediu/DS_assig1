import { Component, OnInit } from '@angular/core';
import {CaregiverService} from '../../../services/caregiver.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Patient} from '../../../model/Patient';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {

  patients: Patient[];
  id: number;

  constructor(private caregiverService: CaregiverService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.getPatientsByCaregiverId(this.id);
  }

  private getPatientsByCaregiverId(id: number) {
    this.caregiverService.getPatientsByCaregiverId(id).subscribe(data => {
      this.patients = data;
    }, error => console.log(error));
  }

}
