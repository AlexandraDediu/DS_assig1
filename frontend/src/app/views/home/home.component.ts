import {Component, HostBinding, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  title = 'angular-demo';

  constructor(private router: Router) {
  }

  ngOnInit() {
  }
}
