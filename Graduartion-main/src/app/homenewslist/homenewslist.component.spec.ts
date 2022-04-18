import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomenewslistComponent } from './homenewslist.component';

describe('HomenewslistComponent', () => {
  let component: HomenewslistComponent;
  let fixture: ComponentFixture<HomenewslistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomenewslistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomenewslistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
