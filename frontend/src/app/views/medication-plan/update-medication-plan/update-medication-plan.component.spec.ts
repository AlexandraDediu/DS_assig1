import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateMedicationPlanComponent } from './update-medication-plan.component';

describe('UpdateMedicationPlanComponent', () => {
  let component: UpdateMedicationPlanComponent;
  let fixture: ComponentFixture<UpdateMedicationPlanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateMedicationPlanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateMedicationPlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
