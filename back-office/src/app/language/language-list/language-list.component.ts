import { Component, OnInit, Output, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Location } from '@angular/common';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { Languageservice } from 'src/app/_services/language.service';
import Swal from 'sweetalert2';
import { AddLanguageComponent } from '../add-language/add-language.component';
import { LanguageViewComponent } from '../language-view/language-view.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { languageDTO } from 'src/app/models/dto/languageDTO';

@Component({
  selector: 'app-language-list',
  templateUrl: './language-list.component.html',
  styleUrls: ['./language-list.component.css']
})
export class LanguageListComponent implements OnInit {

  @Output()
  groupeDTO !: languageDTO;

  @ViewChild('groupeForm', { static: false })

  languageData !: languageDTO;
  language!: languageDTO[];
  searchKey!: string;
  showspinner = false;
  currentGroupe: any;
  languages: any;

  datasource = new MatTableDataSource(this.language)
  displayedColumns: string[] = [ 'name', 'groupStatus', 'createdDate', 'lastModifiedDate', 'actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  id = this.route.snapshot.params['id'];
  message!: string;
  constructor(private _snackBar: MatSnackBar, private dialog: MatDialog, private languageservice: Languageservice, private dialogService: DialogService,private notificationService: NotificationService, private route: ActivatedRoute, public router: Router, public _location: Location) {
    this.languageData = {} as languageDTO;
  }


  //data sorting 
  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }


  ngOnInit(): void {

      this.languageservice.getalllanguage().subscribe((response: any) => {
      this.datasource.data = response;});

    //this.getBy(this.route.snapshot.paramMap.get('id'));

  }

  getBylanguage(id: any) {
    this.languageservice.getByidlanguage(id)
      .subscribe(
        data => {
          this.currentGroupe = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  //search for data 

  onSearchClear() {
    this.searchKey = "";
    this.applyFilter();
  }

  // fiter data 
  applyFilter() {
    this.datasource.filter = this.searchKey.trim().toLowerCase();

  }


  // delete data 
  onDeletelanguage(id: number) {

    this.dialogService.openConfirmDialog('Are you sure to delete this record ?')
      .afterClosed().subscribe((res: any) => {
        if (res) {
          this.languageservice.deletelanguage(id).subscribe(() => {
          })
          
          this.ngOnInit();
          this.refresh();
          this._snackBar.open(" :: Groupe have been deleted Successfully ", "", {
            duration: 3000,
            horizontalPosition: "right",
            verticalPosition: "top",
            panelClass: ["mat-toolbar", "mat-succes"],
          });

        }},
        error => {
          this._snackBar.open("Erroor occurend !!" + error?.message, "", {
            duration: 3000,
            horizontalPosition: "right",
            verticalPosition: "top",
            panelClass: ["mat-toolbar", "mat-warn"],
          });
        });

  }



  //view details groupe
  Viewlanguage(row: any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "100%";
    this.dialog.open(LanguageViewComponent, {
      data: {
        lang: row.lang,
        name: row.name,
        image: row.image,
        active: row.active,
      },
    }
    ), dialogConfig

  }

  // create dialog config
  onCreatelanguage() {
    this.languageservice.form.reset();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(AddLanguageComponent, dialogConfig);
//            this.datasource.data.push(result)

  }

  // edite dialogConfig
  onEditlanguage(row: any) {
    this.languageservice.populateForm(row);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(AddLanguageComponent, dialogConfig);
  }

  // clear data 
  onClear() {
    this.languageservice.form.reset();
    this.languageservice.initializeFormGroup();
  }

  //refrech 
  refresh(): void {
    this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
      console.log(decodeURI(this._location.path()));
      this.router.navigate([decodeURI(this._location.path())]);
    });
  }

  //update status groupe
  /*updateactiveGroupe(element: languageDTO) {

    this.languageservice.updatelanguageByStatus(element.id, element.groupStatus).subscribe( res => {
        
         console.log(res);
          
          const index = this.datasource.data.indexOf(element);
          if(index > -1) {
            this.datasource.data[index].groupStatus = res.groupStatus;
          }
        },
      // snackBar error 
      );

  }*/


  // delete all groupe
  removeAlllanguage() {
    Swal.fire({
      title: 'Are you sure to delete all Groupes  !?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, go ahead.',
      cancelButtonText: 'No, let me think',
    }).then((result) => {
      if (result.value) {
        this.languageservice.deleteAlllanguage()
          .subscribe(
            response => {
              console.log(response);
               // snackBar success 
              this._snackBar.open("Groupes Deleted Successfully !!", "", {
                duration: 5000,
                horizontalPosition: "right",
                verticalPosition: "top",
                panelClass: ["mat-toolbar", "mat-succes"],
              });

              this.ngOnInit();
              this.refresh();
            },

            error => {
               // snackBar error
              this._snackBar.open("Erroor occurend !!" + error?.message, "", {
                duration: 3000,
                horizontalPosition: "right",
                verticalPosition: "top",
                panelClass: ["mat-toolbar", "mat-warn"],
              });
            });
        Swal.fire('Deleted!', ' All Groupe  was Deleted successfully.', 'success');
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('TRY LATER...');
      }
    });

  }
  //filtring by active groupe 
  active = "";
  searchByActivelanguage() {
    this.languageservice.findByActivelanguage(this.active)
      .subscribe(
        data => {

          this.datasource.filter = this.active.trim().toLowerCase();
          this.active.trim().toLowerCase() == data;
        },
        error => {
          console.log(error);
        });
  }

}
