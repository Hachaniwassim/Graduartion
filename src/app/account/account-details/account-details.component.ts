import { ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Account } from 'src/app/models/account';

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.css']
})
export class AccountDetailsComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public account:Account,public dialog: MatDialog,private ref: ChangeDetectorRef
  ) { }

  ngOnInit(): void {
  }


}
