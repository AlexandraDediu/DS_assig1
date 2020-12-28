import { Component, OnInit } from '@angular/core';
import {Medication} from '../../../model/Medication';
import {MedicationService} from '../../../services/medication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-medication',
  templateUrl: './create-medication.component.html',
  styleUrls: ['./create-medication.component.css']
})
export class CreateMedicationComponent implements OnInit {

  medication: Medication = new Medication();
  constructor(private medicationService: MedicationService,
              private router: Router) { }

  ngOnInit(): void {
  }

  saveMedication() {
    this.medicationService.addMedication(this.medication).subscribe( data => {
        console.log(data);
        this.goToMedicationList();
      },
      error => console.log(error));
  }

  goToMedicationList() {
    this.router.navigate(['/medications-list']);
  }

  onSubmit() {
    console.log(this.medication);
    this.saveMedication();
  }

}
