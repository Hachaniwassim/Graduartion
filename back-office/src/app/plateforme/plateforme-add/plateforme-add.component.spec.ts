import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlateformeAddComponent } from './plateforme-add.component';

describe('PlateformeAddComponent', () => {
  let component: PlateformeAddComponent;
  let fixture: ComponentFixture<PlateformeAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlateformeAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlateformeAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
