import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateMedicationComponent } from './update-medication.component';

describe('UpdateMedicationComponent', () => {
  let component: UpdateMedicationComponent;
  let fixture: ComponentFixture<UpdateMedicationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateMedicationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateMedicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
