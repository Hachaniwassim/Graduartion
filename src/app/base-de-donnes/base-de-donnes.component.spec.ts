import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BaseDeDonnesComponent } from './base-de-donnes.component';

describe('BaseDeDonnesComponent', () => {
  let component: BaseDeDonnesComponent;
  let fixture: ComponentFixture<BaseDeDonnesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BaseDeDonnesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BaseDeDonnesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
