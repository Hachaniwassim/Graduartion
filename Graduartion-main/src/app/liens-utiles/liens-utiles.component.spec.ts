import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LiensUtilesComponent } from './liens-utiles.component';

describe('LiensUtilesComponent', () => {
  let component: LiensUtilesComponent;
  let fixture: ComponentFixture<LiensUtilesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LiensUtilesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LiensUtilesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
