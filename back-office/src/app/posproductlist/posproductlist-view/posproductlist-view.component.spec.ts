import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosproductlistViewComponent } from './posproductlist-view.component';

describe('PosproductlistViewComponent', () => {
  let component: PosproductlistViewComponent;
  let fixture: ComponentFixture<PosproductlistViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosproductlistViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PosproductlistViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
