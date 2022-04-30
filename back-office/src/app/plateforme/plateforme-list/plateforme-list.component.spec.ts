import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlateformeListComponent } from './plateforme-list.component';

describe('PlateformeListComponent', () => {
  let component: PlateformeListComponent;
  let fixture: ComponentFixture<PlateformeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlateformeListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlateformeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
