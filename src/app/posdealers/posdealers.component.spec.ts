import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosdealersComponent } from './posdealers.component';

describe('PosdealersComponent', () => {
  let component: PosdealersComponent;
  let fixture: ComponentFixture<PosdealersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosdealersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PosdealersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
