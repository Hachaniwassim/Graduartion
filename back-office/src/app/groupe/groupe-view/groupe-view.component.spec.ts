import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupeViewComponent } from './groupe-view.component';

describe('GroupeViewComponent', () => {
  let component: GroupeViewComponent;
  let fixture: ComponentFixture<GroupeViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroupeViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupeViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
