import { Component, OnInit } from '@angular/core';
import {Caregiver} from '../../../model/Caregiver';
import {CaregiverService} from '../../../services/caregiver.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-update-caregiver',
  templateUrl: './update-caregiver.component.html',
  styleUrls: ['./update-caregiver.component.css']
})
export class UpdateCaregiverComponent implements OnInit {
  id: number;
  caregiver: Caregiver = new Caregiver();

  constructor(private caregiverService: CaregiverService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.caregiverService.getCaregiverById(this.id).subscribe(data => {
      this.caregiver = data;
    }, error => console.log(error));
  }

  updateCaregiver() {
    this.caregiverService.updateCaregiver(this.id, this.caregiver).subscribe(data => {
        console.log(data);
        this.goToCaregiversList();
      },
      error => console.log(error));
  }

  goToCaregiversList() {
    this.router.navigate(['/caregivers-list']);
  }

  onSubmit() {
    console.log(this.caregiver);
    this.updateCaregiver();
  }

}
