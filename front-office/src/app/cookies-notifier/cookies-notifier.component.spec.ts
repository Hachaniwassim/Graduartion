import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CookiesNotifierComponent } from './cookies-notifier.component';

describe('CookiesNotifierComponent', () => {
  let component: CookiesNotifierComponent;
  let fixture: ComponentFixture<CookiesNotifierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CookiesNotifierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CookiesNotifierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
