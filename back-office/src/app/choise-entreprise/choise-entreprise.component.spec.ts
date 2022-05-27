import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoiseEntrepriseComponent } from './choise-entreprise.component';

describe('ChoiseEntrepriseComponent', () => {
  let component: ChoiseEntrepriseComponent;
  let fixture: ComponentFixture<ChoiseEntrepriseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChoiseEntrepriseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChoiseEntrepriseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
