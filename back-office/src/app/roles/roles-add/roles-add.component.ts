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
import { RoleService } from 'src/app/_services/role.service';
import { RoleDTO } from 'src/app/models/dto/roleDTO';

@Component({
  selector: 'app-roles-add',
  templateUrl: './roles-add.component.html',
  styleUrls: ['./roles-add.component.css']
})
export class RolesAddComponent implements OnInit {

  @ViewChild('groupeForm', { static: false })
  roleForm !: FormGroup;
  roleData !: RoleDTO;
  role!: RoleDTO[];
  searchKey!: string;
  showspinner = false;
  datasource = new MatTableDataSource(this.role)
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;



  constructor(private dialog: MatDialog, private dialogService: DialogService, public roleService: RoleService, public dialogRef: MatDialogRef<RolesAddComponent>,
    private notificationService: NotificationService, private router: Router, public _location: Location) {
    this.roleData = {} as RoleDTO;
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
    this.getAllGroupe();  
  }

   getAllGroupe() {
    this.roleService.getallrole().subscribe((response: any) => {
      this.datasource.data = response;
    })
   }


  //clear data
  onClear() {
    this.roleService.form.reset();
    this.roleService.initializeFormGroup();
  }

   // submit data with context EDITE : CREATE
   onSubmit() {
    if (this.roleService.form.valid) {

      if (!this.roleService.form.get('id')?.value)
       { console.log(this.roleService.form.value);
        this.roleService.createrole(this.roleService.form.value).subscribe((res) => {
          console.log(res);
      
          this.notificationService.success(':: Submitted successfully');
          this.onClose();
        });
      }

      else(
        this.roleService.updaterole(this.roleService.form.value).subscribe(() => {
        })) 
        this.onClose();
      }
      
    this.roleService.form.reset();
    this.roleService.initializeFormGroup();
   // this.refresh();
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
    this.roleService.form.reset();
    this.roleService.initializeFormGroup();
    this.dialogRef.close();
  }
  reloadPage() {
    window.location.reload();
  }
 
}
