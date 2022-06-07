import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Location } from '@angular/common';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { CompanyBusinessDTO } from 'src/app/models/dto/companyBusinessDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { CompanybusinessService } from 'src/app/_services/companybusiness.service';
import Swal from 'sweetalert2';
import { CompanyAddComponent } from '../company-add/company-add.component';
import { CompanyViewComponent } from '../company-view/company-view.component';


@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css']
})
export class CompanyListComponent implements OnInit {


  @ViewChild('companyForm', { static: false })
  companyForm!: FormGroup;
  companyData !: CompanyBusinessDTO;
  companybusiness : CompanyBusinessDTO[]=[];
  searchKey!: string;
  showspinner = false;
  data: any;

  company = {
    domainename: "",
    description: "",
  }

  datasource = new MatTableDataSource(this.companybusiness)
  displayedColumns: string[] = ['description', 'domainename', 'createdDate', 'lastModifiedDate', 'actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  id = this.route.snapshot.params['id'];
  constructor(private dialog: MatDialog, private dialogService: DialogService, private companyService: CompanybusinessService,
    private notificationService: NotificationService, private route: ActivatedRoute, public router: Router, public _location: Location) {
    this.companyData = {} as CompanyBusinessDTO;
  }
  //data sorting 
  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }


  ngOnInit(): void {
    //data sorting and pagination from angular materila 
    this.companyService.getAllCompanyBussiness().subscribe((response: any) => {
      this.datasource.data = response;
      this.datasource.paginator = this.paginator;
      this.datasource.sort = this.sort;
      console.log(response)
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
  onDeleteCompany(id: number) {

    this.dialogService.openConfirmDialog('Are you sure to delete this record ?')
      .afterClosed().subscribe((res: any) => {
        if (res) {
          this.companyService.deleteCompanyBusiness(id).subscribe(() => {
            this.datasource.data = this.datasource.data.filter((o: any) => {
               return o.id !== id ? o : false;
             })
             this.companybusiness.push(res);
          })
          this.notificationService.success(' :: Deleted Successfully')
        }
        this.refresh();
      });
   
  }

  getone() {
    this.companyService.getByidCompany(this.id).subscribe((response) => {
      this.data = response;
      this.company = this.data;
      console.log(this.company);
    })
  }


  ViewCompany(row: any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "100%";
    this.dialog.open(CompanyViewComponent, {
      data: {
        domainename: row.domainename,
        description: row.description
      },
    }
    ), dialogConfig

  }

  // create dialog config
  onCreateCompany() {
    this.companyService.form.reset();
    this.companyService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(CompanyAddComponent, dialogConfig);

  }

  // edite dialogConfig
  onEditCompany(row: any) {
    this.companyService.populateForm(row);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(CompanyAddComponent, dialogConfig);


  }

  // clear data 
  onClear() {
    this.companyService.form.reset();
    this.companyService.initializeFormGroup();
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



