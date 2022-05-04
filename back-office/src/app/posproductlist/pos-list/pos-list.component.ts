import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Location } from '@angular/common';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { productsDTO } from 'src/app/models/dto/productsDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { ProductService } from 'src/app/_services/products.service';
import Swal from 'sweetalert2';
import { PosproductlistAddComponent } from '../posproductlist-add/posproductlist-add.component';
import { PosproductlistViewComponent } from '../posproductlist-view/posproductlist-view.component';


@Component({
  selector: 'app-pos-list',
  templateUrl: './pos-list.component.html',
  styleUrls: ['./pos-list.component.css']
})
export class PosListComponent implements OnInit {

  @ViewChild('companyForm', { static: false })
  productForm!: FormGroup;
  productData !: productsDTO;
  productbusiness!: productsDTO[];
  searchKey!: string;
  showspinner = false;
  data : any ;
  
  company={
    title:"", 
    detailimage: "",
    note: "",
    name: "",
    image: "",
    consultationNumber: "",
  }

  datasource = new MatTableDataSource(this.productbusiness)
  displayedColumns: string[] = ['title', 'detailimage','note','name','image','consultationNumber','actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
 id=this.route.snapshot.params['id'];
  constructor(private dialog: MatDialog, private dialogService: DialogService, private productservice: ProductService,
    private notificationService: NotificationService,private route: ActivatedRoute, public router: Router, public _location: Location) {
    this.productData = {} as productsDTO;
  }
  //data sorting 
  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }


  ngOnInit(): void {
    //data sorting and pagination from angular materila 
    this.productservice.getAllproducts().subscribe((response: any) => {
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
  onDeleteproduct(id: number) {

    this.dialogService.openConfirmDialog('Are you sure to delete this record ?')
      .afterClosed().subscribe((res: any) => {
        if (res) {
          this.productservice.deleteproduct(id).subscribe(() => {
            //filter sur l objet  optionnel

           /*this.datasource.data = this.datasource.data.filter((o: any) => {
              return o.id !== id ? o : false;
            })
            console.log(this.datasource.data);*/
          })
          this.notificationService.success(' :: Deleted Successfully')
          this.refresh();
        }
      });
  }

        getone(){
        this.productservice.getByidproduct(this.id).subscribe((response)=>
        { this.data=response;
         this.company=this.data;
         console.log(this.company);
       })
      }


   Viewproduct(row : any) { 
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "100%"; 
     this.dialog.open(PosproductlistViewComponent, {
          data: {
            title : row.title,
            detailimage:row.detailimage,
            note:row.note,
            name:row.name,
            image:row.image,
            consultationNumber:row.consultationNumber,
          },
        }
        ),dialogConfig

      }

  // create dialog config
  onCreateproduct() {
    //this.companyService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(PosproductlistAddComponent, dialogConfig);

  }

  // edite dialogConfig
  onEditproduct(row: any) {
    this.productservice.populateForm(row);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(PosproductlistAddComponent, dialogConfig);


  }

  // clear data 
  onClear() {
    this.productservice.form.reset();
    this.productservice.initializeFormGroup();
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
