import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupeListComponent } from './groupe-list.component';

describe('GroupeListComponent', () => {
  let component: GroupeListComponent;
  let fixture: ComponentFixture<GroupeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroupeListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
