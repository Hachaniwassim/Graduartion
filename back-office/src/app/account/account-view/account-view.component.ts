import { ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AccountDTO } from 'src/app/models/dto/accountDTO';
@Component({
  selector: 'app-account-view',
  templateUrl: './account-view.component.html',
  styleUrls: ['./account-view.component.css']
})
export class AccountViewComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public account:AccountDTO,public dialog: MatDialog,private ref: ChangeDetectorRef
  ) { }
  ngOnInit(): void {
  }

}
