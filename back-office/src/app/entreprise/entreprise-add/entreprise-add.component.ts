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
import { GroupeService } from 'src/app/_services/groupe.service';
import { GroupeDTO } from 'src/app/models/dto/groupeDTO';

@Component({
  selector: 'app-entreprise-add',
  templateUrl: './entreprise-add.component.html',
  styleUrls: ['./entreprise-add.component.css']
})
export class EntrepriseAddComponent implements OnInit {

  @ViewChild('entrepriseForm', { static: false })
  entrepriseForm !: FormGroup;
  entrepriseData !: EntrepriseDTO;
  entreprise: EntrepriseDTO[]=[];
  searchKey!: string;
  showspinner = false;
  datasource = new MatTableDataSource(this.entreprise)
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  groupeServices: GroupeDTO[] = [];



    constructor(private dialog: MatDialog, public entrepriseService: EntrepriseService, public dialogRef: MatDialogRef<EntrepriseAddComponent>,
    private notificationService: NotificationService, private groupeService : GroupeService ,private router: Router, public _location: Location) {
    this.entrepriseData = {} as EntrepriseDTO;
  }

  

  ngOnInit(): void {
    this.getAllEntreprise();
    this.groupeService.getallGroupe().subscribe(res => {
      console.log(res)
      this.groupeServices = res;
    })
   
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
        this.entrepriseService.createEntreprise(this.entrepriseService.form.value).subscribe((res) => {
          this.entreprise.push(res);
          this.notificationService.success(':: Submitted successfully');
        })

      else(
        this.entrepriseService.updateEntreprise(this.entrepriseService.form.value).subscribe((res) => {
          this.entreprise.push(res);
        })) 
        this.onClose();
        this.refresh();
      }
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
  getAllEntreprise(){
    this.entrepriseService.getAllEntreprise().subscribe((response: any) => {
    this.datasource.data = response;
    this.entreprise.push(response);
    
  });
}

}

