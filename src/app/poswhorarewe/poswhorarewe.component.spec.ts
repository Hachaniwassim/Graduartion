import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoswhorareweComponent } from './poswhorarewe.component';

describe('PoswhorareweComponent', () => {
  let component: PoswhorareweComponent;
  let fixture: ComponentFixture<PoswhorareweComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoswhorareweComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoswhorareweComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
