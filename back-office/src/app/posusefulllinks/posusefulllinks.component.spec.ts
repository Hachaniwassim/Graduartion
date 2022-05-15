import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosusefulllinksComponent } from './posusefulllinks.component';

describe('PosusefulllinksComponent', () => {
  let component: PosusefulllinksComponent;
  let fixture: ComponentFixture<PosusefulllinksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosusefulllinksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PosusefulllinksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
