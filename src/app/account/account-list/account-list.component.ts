import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountDTO } from 'src/app/models/dto/accountDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { Accountservice } from 'src/app/_services/account.service';
import {Location}from '@angular/common';
import { AccountEditComponent } from '../account-edit/account-edit.component';

@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css']
})
export class AccountListComponent implements OnInit {


  @ViewChild('companyForm', { static: false })
  accountForm!: FormGroup;
  accountData !: AccountDTO;
  account!:AccountDTO[];
  searchKey!: string;
  showspinner=false;
  private roles: string[] = [];
  showAdminBoard = false;
  showModeratorBoard = false;

  datasource = new MatTableDataSource(this.account)
  displayedColumns: string[] = ['username', 'email','fiscaleCode','role','actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort,{}) sort!: MatSort;
  mybreakpoint!: number;

  constructor( private dialog: MatDialog, private dialogService: DialogService, private Accountservice:Accountservice,
    private notificationService: NotificationService,private route: ActivatedRoute, public router: Router, public _location: Location) {
    this.accountData = {} as AccountDTO;
  }

  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }
  
  ngOnInit(): void {
    
   this.mybreakpoint = (window.innerWidth <= 600) ? 1 : 6;
    this.datasource.sort = this.sort;
    this.datasource.paginator = this.paginator;
    this.getAll();
  }

 

  getAll() {
    this.Accountservice.all().subscribe((response: any) => {
      this.datasource.data = response;
    });

  }
  onSearchClear() {
    this.searchKey = "";
    this.applyFilter();
  }

  applyFilter() {
    this.datasource.filter = this.searchKey.trim().toLowerCase();
  }

  onDelete(id: number) {

    this.dialogService.openConfirmDialog('Are you sure to delete this record ?')
      .afterClosed().subscribe((res: any) => {
        if (res) {
          this.Accountservice.delete(id).subscribe(() => {
  
          })
          this.getAll();
          this.refresh();
        }
      });
  }

    //refrech 
    refresh(): void {
      this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
        console.log(decodeURI(this._location.path()));
        this.router.navigate([decodeURI(this._location.path())]);
      });
    }
  
  
  onEdit(row: any){
    this.Accountservice.populateForm(row);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(AccountEditComponent,dialogConfig);

  }


  onClear() {
    this.Accountservice.form.reset();
    this.Accountservice.initializeFormGroup();
  }


  
}
