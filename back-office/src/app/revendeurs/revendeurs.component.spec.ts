import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevendeursComponent } from './revendeurs.component';

describe('RevendeursComponent', () => {
  let component: RevendeursComponent;
  let fixture: ComponentFixture<RevendeursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RevendeursComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RevendeursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
