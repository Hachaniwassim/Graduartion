import { Component, OnInit, ViewChild } from '@angular/core';
import {Location}from '@angular/common';
import { FormGroup } from '@angular/forms';
import { EntrepriseDTO } from 'src/app/models/dto/entreprisDTO';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { NotificationService } from 'src/app/shared/notification.service';
import { DialogService } from 'src/app/shared/dialog.service';
import { Router } from '@angular/router';
import { EntrepriseService } from 'src/app/_services/entreprise.service';

@Component({
  selector: 'app-entreprise-add',
  templateUrl: './entreprise-add.component.html',
  styleUrls: ['./entreprise-add.component.css']
})
export class EntrepriseAddComponent implements OnInit {

  @ViewChild('entrepriseForm', { static: false })
  entrepriseForm !: FormGroup;
  entrepriseData !: EntrepriseDTO;
  entreprise!: EntrepriseDTO[];
  searchKey!: string;
  showspinner = false;
  datasource = new MatTableDataSource(this.entreprise)
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;



    constructor(private dialog: MatDialog, private dialogService: DialogService, public entrepriseService: EntrepriseService, public dialogRef: MatDialogRef<EntrepriseAddComponent>,
    private notificationService: NotificationService, private router: Router, public _location: Location) {
    this.entrepriseData = {} as EntrepriseDTO;
  }

  

  ngOnInit(): void {
   
  }


  //clear data
  onClear() {
    this.entrepriseService.form.reset();
    this.entrepriseService.initializeFormGroup();
  }

   // submit data with context EDITE : CREATE
   onSubmit() {
    if (this.entrepriseService.form.valid) {
      if (!this.entrepriseService.form.get('id')?.value)
        this.entrepriseService.createEntreprise(this.entrepriseService.form.value).subscribe(() => {
          
          this.notificationService.success(':: Submitted successfully');
        })

      else(
        this.entrepriseService.updateEntreprise(this.entrepriseService.form.value).subscribe(() => {
        })) 
        this.onClose();
      }
    this.refresh();
  }


  //refrech 
  refresh(): void {
    this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
      console.log(decodeURI(this._location.path()));
      this.router.navigate([decodeURI(this._location.path())]);
    });
  }

  // dialogue close 
  onClose() {
    this.entrepriseService.form.reset();
    this.entrepriseService.initializeFormGroup();
    this.dialogRef.close();
  }
  
  reloadPage() {
    window.location.reload();
  }

}

