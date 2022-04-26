import { ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CompanyBusinessDTO } from 'src/app/models/dto/companyBusinessDTO';

@Component({
  selector: 'app-company-view',
  templateUrl: './company-view.component.html',
  styleUrls: ['./company-view.component.css']
})
export class CompanyViewComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public company:CompanyBusinessDTO,public dialog: MatDialog,private ref: ChangeDetectorRef
  ) { }

  ngOnInit(): void {
  }

}
