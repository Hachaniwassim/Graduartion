import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosactualityComponent } from './posactuality.component';

describe('PosactualityComponent', () => {
  let component: PosactualityComponent;
  let fixture: ComponentFixture<PosactualityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosactualityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PosactualityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
