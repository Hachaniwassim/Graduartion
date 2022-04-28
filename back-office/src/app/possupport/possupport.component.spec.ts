import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PossupportComponent } from './possupport.component';

describe('PossupportComponent', () => {
  let component: PossupportComponent;
  let fixture: ComponentFixture<PossupportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PossupportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PossupportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
