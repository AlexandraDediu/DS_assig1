import { Component, OnInit } from '@angular/core';
import {Caregiver} from '../../../model/Caregiver';
import {CaregiverService} from '../../../services/caregiver.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-caregiver-list',
  templateUrl: './caregiver-list.component.html',
  styleUrls: ['./caregiver-list.component.css']
})
export class CaregiverListComponent implements OnInit {

  caregivers: Caregiver[];
  constructor(private caregiverService: CaregiverService,
              private router: Router) { }

  ngOnInit() {
    this.getCaregivers();
  }

  private getCaregivers() {
    this.caregiverService.getCaregivers().subscribe(data => {
      this.caregivers = data;
    });
  }

  updateCaregiver(id: number) {
    this.router.navigate(['update-caregiver', id]);
  }

  createCaregiver() {
    this.router.navigate(['create-caregiver']);
  }

  deleteCaregiver(id: number) {
    this.caregiverService.deleteCaregiver(id).subscribe(data => {
      console.log(data);
      const deletedCaregiver = this.caregivers.find(u => u.id !== id);
      this.caregivers.splice(this.caregivers.indexOf(deletedCaregiver, 1));
    });
  }


}
