import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntrepriseAddComponent } from './entreprise-add.component';

describe('EntrepriseAddComponent', () => {
  let component: EntrepriseAddComponent;
  let fixture: ComponentFixture<EntrepriseAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntrepriseAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EntrepriseAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
