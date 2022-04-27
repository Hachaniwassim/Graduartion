import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Account } from 'src/app/models/account';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { accountservice } from 'src/app/_services/account.service';
import Swal from 'sweetalert2';
import { AccountEditComponent } from '../account-edit/account-edit.component';
import { AccountDetailsComponent } from '../account-details/account-details.component';

import { Location } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css']
})
export class AccountListComponent implements OnInit {


  @ViewChild('companyForm', { static: false })
  accountForm!: FormGroup;
  accountData !: Account;
  account!:Account[];
  searchKey!: string;
  showspinner=false;
  data : any ;

  accountt={
    username :"",
    email: "",
    password: "",
    matchingPassword: "",
    fiscaleCode: "",
  }

  datasource = new MatTableDataSource(this.account)
  displayedColumns: string[] = ['username', 'email','password','matchingPassword','fiscaleCode','actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort,{}) sort!: MatSort;
  id=this.route.snapshot.params['id'];

  constructor( private dialog: MatDialog, private dialogService: DialogService, private Accountservice:accountservice,
    private notificationService: NotificationService,private route: ActivatedRoute, public router: Router, public _location: Location) {
    this.accountData = {} as Account;
  }

  //data sorting 
  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }


  ngOnInit(): void {
    //data sorting and pagination from angular materila 
    this.Accountservice.all().subscribe((response: any) => {
      this.datasource.data = response;
      this.datasource.paginator = this.paginator;
      this.datasource.sort = this.sort;
    });

  }
  //search for data 

  onSearchClear() {
    this.searchKey = "";
    this.applyFilter();
  }

  applyFilter() {
    this.datasource.filter = this.searchKey.trim().toLowerCase();
  }


  // delete data 
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
          this.refresh();
        }
      });
  }

        getone(){
        this.Accountservice.getByid(this.id).subscribe((response)=>
        { this.data=response;
         this.accountt=this.data;
         console.log(this.accountt);
       })
      }


   View(row : any) { 
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "100%"; 
     this.dialog.open(AccountDetailsComponent, {
          data: {
            domainename : row.domainename,
            description:row.description
          },
        }
        ),dialogConfig

      }

  // create dialog config
  onCreate() {
    //this.companyService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(AccountEditComponent, dialogConfig);

  }

  // edite dialogConfig
  onEdit(row: any) {
    this.Accountservice.populateForm(row);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(AccountEditComponent, dialogConfig);


  }

  // clear data 
  onClear() {
    this.Accountservice.form.reset();
    this.Accountservice.initializeFormGroup();
  }

  //refrech 
  refresh(): void {
    this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
      console.log(decodeURI(this._location.path()));
      this.router.navigate([decodeURI(this._location.path())]);
    });
  }


  // spinner from angular material
  spinner() {
    this.showspinner = true;
    setTimeout(() => { this.showspinner = false; }, 2000)
  }

  //alerte de confirmation 
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
