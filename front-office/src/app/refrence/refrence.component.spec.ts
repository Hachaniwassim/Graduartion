import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RefrenceComponent } from './refrence.component';

describe('RefrenceComponent', () => {
  let component: RefrenceComponent;
  let fixture: ComponentFixture<RefrenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RefrenceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RefrenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
