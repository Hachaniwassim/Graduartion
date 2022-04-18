import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosprivacyComponent } from './posprivacy.component';

describe('PosprivacyComponent', () => {
  let component: PosprivacyComponent;
  let fixture: ComponentFixture<PosprivacyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosprivacyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PosprivacyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
