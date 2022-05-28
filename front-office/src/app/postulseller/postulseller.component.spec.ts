import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostulsellerComponent } from './postulseller.component';

describe('PostulsellerComponent', () => {
  let component: PostulsellerComponent;
  let fixture: ComponentFixture<PostulsellerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostulsellerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PostulsellerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
