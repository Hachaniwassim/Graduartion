import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosdatabaseComponent } from './posdatabase.component';

describe('PosdatabaseComponent', () => {
  let component: PosdatabaseComponent;
  let fixture: ComponentFixture<PosdatabaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosdatabaseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PosdatabaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
