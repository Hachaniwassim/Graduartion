import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoshomeComponent } from './poshome.component';

describe('PoshomeComponent', () => {
  let component: PoshomeComponent;
  let fixture: ComponentFixture<PoshomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoshomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoshomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
