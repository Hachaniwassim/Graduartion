import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeprimaryslideComponent } from './homeprimaryslide.component';

describe('HomeprimaryslideComponent', () => {
  let component: HomeprimaryslideComponent;
  let fixture: ComponentFixture<HomeprimaryslideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeprimaryslideComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeprimaryslideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
