import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import {Location}from '@angular/common';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { TagsDTO } from 'src/app/models/dto/TagsDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { TagsSerice } from 'src/app/_services/tags.service';
import { AddTagsComponent } from '../add-tags/add-tags.component';

@Component({
  selector: 'app-list-tags',
  templateUrl: './list-tags.component.html',
  styleUrls: ['./list-tags.component.css']
})
export class ListTagsComponent implements OnInit {

  @ViewChild('tagsForm', { static: false })
  tagsForm !:FormGroup;
  tagsData !:TagsDTO;
  tag!:TagsDTO[];
  searchKey!: string;
  showspinner = false;
  tags : any ;
  datasource = new MatTableDataSource(this.tag)
  message!: string;
  displayedColumns: string[] = ['id','description','createdDate', 'lastModifiedDate', 'actions'];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  constructor(private dialog: MatDialog,private tagsService : TagsSerice,private dialogService: DialogService,
    private notificationService: NotificationService,private route: ActivatedRoute, public router: Router, public _location: Location) {
    this.tagsData = {} as TagsDTO;
  }

  
  //data sorting 
  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }


  ngOnInit(): void {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
    this.getAllTags();
  
  }    


  getAllTags(){

      this.tagsService.getAlltags().subscribe((response: any) => {
      this.datasource.data = response;
      
    });
  }

  //search for data 

  onSearchClear() {
    this.searchKey = "";
    this.applyFilter();
  }

  // filter data 
  applyFilter() {
    this.datasource.filter = this.searchKey.trim().toLowerCase();
   
  }


  // delete data 
  onDeleteTags(id: number) {

    this.dialogService.openConfirmDialog('Are you sure to delete this record ?')
      .afterClosed().subscribe((res: any) => {
        if (res) {
          this.tagsService.deleteTags(id).subscribe(() => {
          })
          this.notificationService.success(' :: Deleted Successfully')
          this.refresh();
        }
      });
  }



  // create dialog config
  onCreateTags() { 
    //this.companyService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(AddTagsComponent, dialogConfig);

  }

  // edite dialogConfig
  onEditTags(row: any) {
    this.tagsService.populateForm(row);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(AddTagsComponent, dialogConfig);


  }

  // clear data 
  onClear() {
    this.tagsService.form.reset();
    this.tagsService.initializeFormGroup();
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


}
