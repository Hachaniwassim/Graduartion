import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomescustomerlogosComponent } from './homescustomerlogos.component';

describe('HomescustomerlogosComponent', () => {
  let component: HomescustomerlogosComponent;
  let fixture: ComponentFixture<HomescustomerlogosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomescustomerlogosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomescustomerlogosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
