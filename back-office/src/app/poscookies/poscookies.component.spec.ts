import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoscookiesComponent } from './poscookies.component';

describe('PoscookiesComponent', () => {
  let component: PoscookiesComponent;
  let fixture: ComponentFixture<PoscookiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoscookiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoscookiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
