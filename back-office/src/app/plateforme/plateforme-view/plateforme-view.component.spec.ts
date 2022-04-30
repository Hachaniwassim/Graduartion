import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlateformeViewComponent } from './plateforme-view.component';

describe('PlateformeViewComponent', () => {
  let component: PlateformeViewComponent;
  let fixture: ComponentFixture<PlateformeViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlateformeViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlateformeViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
