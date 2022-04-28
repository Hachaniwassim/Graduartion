import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeftSlideBarComponent } from './left-slide-bar.component';

describe('LeftSlideBarComponent', () => {
  let component: LeftSlideBarComponent;
  let fixture: ComponentFixture<LeftSlideBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LeftSlideBarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LeftSlideBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
