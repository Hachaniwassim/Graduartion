import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosproductlistAddComponent } from './posproductlist-add.component';

describe('PosproductlistAddComponent', () => {
  let component: PosproductlistAddComponent;
  let fixture: ComponentFixture<PosproductlistAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosproductlistAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PosproductlistAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
