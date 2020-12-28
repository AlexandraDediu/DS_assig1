import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCaregiverComponent } from './update-caregiver.component';

describe('UpdateCaregiverComponent', () => {
  let component: UpdateCaregiverComponent;
  let fixture: ComponentFixture<UpdateCaregiverComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateCaregiverComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCaregiverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
