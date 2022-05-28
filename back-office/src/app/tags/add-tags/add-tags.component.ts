import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import {Location}from '@angular/common';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { TagsSerice } from 'src/app/_services/tags.service';
import { TagsDTO } from 'src/app/models/dto/TagsDTO';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-add-tags',
  templateUrl: './add-tags.component.html',
  styleUrls: ['./add-tags.component.css']
})
export class AddTagsComponent implements OnInit {

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  tags:TagsDTO[]=[];
  datasource = new MatTableDataSource(this.tags)
  
  constructor(private dialog: MatDialog, private dialogService: DialogService, public tagsService:TagsSerice, public dialogRef: MatDialogRef<AddTagsComponent>,
    private notificationService: NotificationService, private router: Router, public _location: Location) {
    
  }
 //data sorting 
 ngAfterViewInit() {
  this.datasource.paginator = this.paginator;
  this.datasource.sort = this.sort;
}

 

  ngOnInit(): void {
  this.datasource.paginator = this.paginator;
  this.datasource.sort = this.sort;
  }

  //clear data
  onClear() {
    this.tagsService.form.reset();
    this.tagsService.initializeFormGroup();
  }

   // submit data with context EDITE : CREATE
   onSubmit() {
     
     
    if (this.tagsService.form.valid) {
      if (!this.tagsService.form.get('id')?.value)
        this.tagsService.createtags({
          id:this.tagsService.form.value.id,
          description:this.tagsService.form.value.description,
          createdDate:new Date(),
          lastModifiedDate:new Date(),
          entrepriseId:localStorage.getItem('idEntreprise'),

        }).subscribe((res) => {
          this.tags.push(res);
          console.log('the result of add tags ====>',res)
          
          this.notificationService.success(':: Submitted successfully');
        })

      else(
        this.tagsService.updateTags(this.tagsService.form.value).subscribe((res) => {
          this.tags.push(res);
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
    this.tagsService.form.reset();
    this.tagsService.initializeFormGroup();
    this.dialogRef.close();
  }
  

}
