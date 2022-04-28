import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupeAddComponent } from './groupe-add.component';

describe('GroupeAddComponent', () => {
  let component: GroupeAddComponent;
  let fixture: ComponentFixture<GroupeAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroupeAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupeAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
