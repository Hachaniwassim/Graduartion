import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosproductlistComponent } from './posproductlist.component';

describe('PosproductlistComponent', () => {
  let component: PosproductlistComponent;
  let fixture: ComponentFixture<PosproductlistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosproductlistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PosproductlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
