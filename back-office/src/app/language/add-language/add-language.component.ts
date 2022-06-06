import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import {Location}from '@angular/common';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { Languageservice } from 'src/app/_services/language.service';
import { languageDTO } from 'src/app/models/dto/languageDTO';
@Component({
  selector: 'app-add-language',
  templateUrl: './add-language.component.html',
  styleUrls: ['./add-language.component.css']
})
export class AddLanguageComponent implements OnInit {

  @ViewChild('groupeForm', { static: false })
  languageForm !: FormGroup;
  languageData !: languageDTO;
  language: languageDTO[]= [];
  searchKey!: string;
  showspinner = false;
  datasource = new MatTableDataSource(this.language)
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;



  constructor(private dialog: MatDialog, private dialogService: DialogService, public languageservice: Languageservice, public dialogRef: MatDialogRef<AddLanguageComponent>,
    private notificationService: NotificationService, private router: Router, public _location: Location) {
    this.languageData = {} as languageDTO;
  }

  // sorting + pagination data 
  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }

  ngOnInit(): void {
    // sorting sorting and pagination data 
    this.datasource.sort = this.sort;
    this.datasource.paginator = this.paginator;
    this.getAll();
   
  }

   getAll() {
    this.languageservice.getalllanguage().subscribe((response: any) => {
      this.datasource.data = response;
    })
   }


  //clear data
  onClear() {
    this.languageservice.form.reset();
    this.languageservice.initializeFormGroup();
  }

   // submit data with context EDITE : CREATE
   onSubmit() {
    if (this.languageservice.form.valid) {

      if (!this.languageservice.form.get('id')?.value)
        
        this.languageservice.createlanguage({   
          id:this.languageservice.form.value.id,
          name:this.languageservice.form.value.name,
          code:this.languageservice.form.value.code,
          createdDate:new Date(),
          lastModifiedDate:new Date(),
          entrepriseId:localStorage.getItem('idEntreprise'),
          }).subscribe((res) => {
           this.datasource.data.push(res)
          this.notificationService.success(':: Submitted successfully');
         
        })
    else(
        this.languageservice.updatelanguage(this.languageservice.form.value).subscribe((res) => {
          console.log('the result of update  language ==============>',res) 
          
          this.datasource.data.push(res) 
        })) 
        this.onClose();
        this.refresh();
      
      }
  }


  //refrech 

  refresh(): void {
    this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {

      this.router.navigate(['/language']);
    });
  }


  // dialogue close 
  onClose() {
    this.languageservice.form.reset();
    this.languageservice.initializeFormGroup();
    this.dialogRef.close();
  }
  reloadPage() {
    window.location.reload();
  }
 
}
