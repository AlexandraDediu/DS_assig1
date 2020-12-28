import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMedicationPlanComponent } from './create-medication-plan.component';

describe('CreateMedicationPlanComponent', () => {
  let component: CreateMedicationPlanComponent;
  let fixture: ComponentFixture<CreateMedicationPlanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateMedicationPlanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateMedicationPlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
