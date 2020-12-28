import {Component, OnInit} from '@angular/core';
import {MedicationService} from '../../../services/medication.service';
import {Router} from '@angular/router';
import {Medication} from '../../../model/Medication';

@Component({
  selector: 'app-medication-list',
  templateUrl: './medication-list.component.html',
  styleUrls: ['./medication-list.component.css']
})
export class MedicationListComponent implements OnInit {

  medications: Medication[];

  constructor(private medicationService: MedicationService,
              private router: Router) {
  }

  ngOnInit() {
    this.getMedications();
  }

  private getMedications() {
    this.medicationService.getMedications().subscribe(data => {
      this.medications = data;
    });
  }

  updateMedication(id: number) {
    this.router.navigate(['update-medication', id]);
  }

  createMedication() {
    this.router.navigate(['create-medication']);
  }

  deleteMedication(id: number) {
    this.medicationService.deleteMedication(id).subscribe(data => {
      console.log(data);
      // @ts-ignore
      const deletedMedication = this.medications.find(u => u.id !== id);
      this.medications.splice(this.medications.indexOf(deletedMedication, 1));
    });
    // this.getMedications();
    // this.router.navigateByUrl('medications-list', {skipLocationChange: true }).then(() => {
    // this.router.navigate(['medications-list']);
    // });
  }

}
