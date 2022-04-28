import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosproductsComponent } from './posproducts.component';

describe('PosproductsComponent', () => {
  let component: PosproductsComponent;
  let fixture: ComponentFixture<PosproductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosproductsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PosproductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
