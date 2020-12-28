import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicationPlanListComponent } from './medication-plan-list.component';

describe('MedicationPlanListComponent', () => {
  let component: MedicationPlanListComponent;
  let fixture: ComponentFixture<MedicationPlanListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedicationPlanListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicationPlanListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
