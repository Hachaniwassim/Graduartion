import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CookiesV2Component } from './cookies-v2.component';

describe('CookiesV2Component', () => {
  let component: CookiesV2Component;
  let fixture: ComponentFixture<CookiesV2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CookiesV2Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CookiesV2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
