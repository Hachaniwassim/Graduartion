import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CompanyBusinessDTO } from 'src/app/models/dto/companyBusinessDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { CompanybusinessService } from 'src/app/_services/companybusiness.service';
import Swal from 'sweetalert2';
import { CompanyAddComponent } from '../company-add/company-add.component';

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css']
})
export class CompanyListComponent implements OnInit {


  @ViewChild('companyForm', { static: false })
  companyForm!: FormGroup;
  companyData !: CompanyBusinessDTO;
  company!:CompanyBusinessDTO[];
  searchKey!: string;
  showspinner=false;


  datasource = new MatTableDataSource(this.company)
  displayedColumns: string[] = ['description', 'domainename','actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort,{}) sort!: MatSort;

  constructor( private dialog: MatDialog, private dialogService: DialogService, private companyService:CompanybusinessService,
    private notificationService: NotificationService) {
    this.companyData = {} as CompanyBusinessDTO;
  }

  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }
  ngOnInit(): void {
    
    this.companyService.all().subscribe((response: any) => {
    this.datasource.data = response;
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
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
          this.companyService.delete(id).subscribe(() => {
            this.datasource.data = this.datasource.data.filter((o: any) => {
              return o.id !== id ? o : false;
            })
            console.log(this.datasource.data);
          })
          this.ngOnInit();
        }
      });
  }
  onCreate() {
    //this.companyService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(CompanyAddComponent,dialogConfig);
   
  }

  onEdit(row: any){
    this.companyService.populateForm(row);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(CompanyAddComponent,dialogConfig);
   
   
  }


  onClear() {
    
    this.companyService.form.reset();
    this.companyService.initializeFormGroup();
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



