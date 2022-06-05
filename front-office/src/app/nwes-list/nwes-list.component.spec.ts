import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NwesListComponent } from './nwes-list.component';

describe('NwesListComponent', () => {
  let component: NwesListComponent;
  let fixture: ComponentFixture<NwesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NwesListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NwesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
