import { Component, OnInit } from '@angular/core';
import {CaregiverService} from '../../../services/caregiver.service';
import {Router} from '@angular/router';
import {Caregiver} from '../../../model/Caregiver';

@Component({
  selector: 'app-create-caregiver',
  templateUrl: './create-caregiver.component.html',
  styleUrls: ['./create-caregiver.component.css']
})
export class CreateCaregiverComponent implements OnInit {

  caregiver: Caregiver = new Caregiver();
  constructor(private caregiverService: CaregiverService,
              private router: Router) { }

  ngOnInit(): void {
  }

  saveCaregiver() {
    this.caregiverService.addCaregiver(this.caregiver).subscribe( data => {
        console.log(data);
        this.goToCaregiverList();
      },
      error => console.log(error));
  }

  goToCaregiverList() {
    this.router.navigate(['/caregivers-list']);
  }

  onSubmit() {
    console.log(this.caregiver);
    this.saveCaregiver();
  }

}
