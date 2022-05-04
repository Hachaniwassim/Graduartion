import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import {Location}from '@angular/common';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { TagsSerice } from 'src/app/_services/tags.service';

@Component({
  selector: 'app-add-tags',
  templateUrl: './add-tags.component.html',
  styleUrls: ['./add-tags.component.css']
})
export class AddTagsComponent implements OnInit {

 
  constructor(private dialog: MatDialog, private dialogService: DialogService, public tagsService:TagsSerice, public dialogRef: MatDialogRef<AddTagsComponent>,
    private notificationService: NotificationService, private router: Router, public _location: Location) {
    
  }

  

  ngOnInit(): void {
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
        this.tagsService.createtags(this.tagsService.form.value).subscribe(() => {
          
          this.notificationService.success(':: Submitted successfully');
        })

      else(
        this.tagsService.updateTags(this.tagsService.form.value).subscribe(() => {
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
  
  reloadPage() {
    window.location.reload();
  }
}
