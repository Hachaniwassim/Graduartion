import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrandlogoComponent } from './brandlogo.component';

describe('BrandlogoComponent', () => {
  let component: BrandlogoComponent;
  let fixture: ComponentFixture<BrandlogoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BrandlogoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BrandlogoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
