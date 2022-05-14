import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WebPositioningComponent } from './web-positioning.component';

describe('WebPositioningComponent', () => {
  let component: WebPositioningComponent;
  let fixture: ComponentFixture<WebPositioningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WebPositioningComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WebPositioningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
