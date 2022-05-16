import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomewelcometextComponent } from './homewelcometext.component';

describe('HomewelcometextComponent', () => {
  let component: HomewelcometextComponent;
  let fixture: ComponentFixture<HomewelcometextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomewelcometextComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomewelcometextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
