import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanybusinessComponent } from './companybusiness.component';

describe('CompanybusinessComponent', () => {
  let component: CompanybusinessComponent;
  let fixture: ComponentFixture<CompanybusinessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompanybusinessComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CompanybusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
