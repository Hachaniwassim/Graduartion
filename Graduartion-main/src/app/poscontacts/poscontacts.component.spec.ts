import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoscontactsComponent } from './poscontacts.component';

describe('PoscontactsComponent', () => {
  let component: PoscontactsComponent;
  let fixture: ComponentFixture<PoscontactsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoscontactsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoscontactsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
