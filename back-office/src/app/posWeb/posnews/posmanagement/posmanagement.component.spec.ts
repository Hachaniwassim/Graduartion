import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosmanagementComponent } from './posmanagement.component';

describe('PosmanagementComponent', () => {
  let component: PosmanagementComponent;
  let fixture: ComponentFixture<PosmanagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosmanagementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PosmanagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
