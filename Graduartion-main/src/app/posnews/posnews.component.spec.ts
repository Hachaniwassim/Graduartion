import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosnewsComponent } from './posnews.component';

describe('PosnewsComponent', () => {
  let component: PosnewsComponent;
  let fixture: ComponentFixture<PosnewsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosnewsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PosnewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
