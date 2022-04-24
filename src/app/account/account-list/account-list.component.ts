import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { account } from 'src/app/models/account';
import { AccountDTO } from 'src/app/models/dto/accountDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { Accountservice } from 'src/app/_services/account.service';
import Swal from 'sweetalert2';
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


  datasource = new MatTableDataSource(this.account)
  displayedColumns: string[] = ['username', 'email','password','matchingPassword','fiscaleCode','actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort,{}) sort!: MatSort;

  constructor( private dialog: MatDialog, private dialogService: DialogService, private Accountservice:Accountservice,
    private notificationService: NotificationService) {
    this.accountData = {} as AccountDTO;
  }

  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }
  ngOnInit(): void {

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
            this.datasource.data = this.datasource.data.filter((o: any) => {
              return o.id !== id ? o : false;
            })
            console.log(this.datasource.data);
          })
          this.getAll();
        }
      });
  }
  onCreate() {
    this.Accountservice.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(AccountEditComponent,dialogConfig);
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


  reloadPage() {
    setTimeout(()=>{
        window.location.reload();
      }, 1000);  
  }

  spinner(){ 
    this.showspinner=true;
    setTimeout(() => {this.showspinner=false;},2000)
  }
  alertConfirmation() {
    Swal.fire({
      title: 'Are you sure?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, go ahead.',
      cancelButtonText: 'No, let me think',
    }).then((result) => {
      if (result.value) {
        Swal.fire('Updated!', 'Company updated successfully.', 'success');
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelled');
      }
    });
  }
}
