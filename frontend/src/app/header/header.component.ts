import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  getUsers() {
    this.router.navigate(['persons']);
  }

  getMedications() {
    this.router.navigate(['medications-list']);
  }

  getCaregivers() {
    this.router.navigate(['caregivers-list']);
  }

  getPatients() {
    this.router.navigate(['patients-list']);
  }

  goHome() {
    this.router.navigate(['']);
  }

}
