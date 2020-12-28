import {Component, OnInit} from '@angular/core';
import {Medication} from '../../../model/Medication';
import {MedicationService} from '../../../services/medication.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-update-medication',
  templateUrl: './update-medication.component.html',
  styleUrls: ['./update-medication.component.css']
})
export class UpdateMedicationComponent implements OnInit {
  id: number;
  medication: Medication = new Medication();

  constructor(private medicationService: MedicationService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.medicationService.getMedicationById(this.id).subscribe(data => {
      this.medication = data;
    }, error => console.log(error));
  }

  updateMedication() {
    this.medicationService.updateMedication(this.id, this.medication).subscribe(data => {
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
    this.updateMedication();
  }

}
