import { Component, OnInit , ViewChild  } from '@angular/core';
import { users } from './user';
import { user } from './user.model';
import {UserService} from './user.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import * as _ from 'lodash'
import { FormGroup, NgForm } from '@angular/forms';
import { first } from 'rxjs/operators';
import { NotificationService } from '../shared/notification.service';
import { DialogService } from '../shared/dialog.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  //public usersList: user[] = users;
  @ViewChild('carsForm', { static: false })
  carsForm!: FormGroup;
  carsData !: user;
  voiture !: user[];
  isEditeMode = false;
  searchKey!: string;
  submitted = false ;
  
   showspinner=false;

  base_url = "http://localhost:3000/rents";

  datasource = new MatTableDataSource(this.voiture)
  displayedColumns: string[] = ['first_name', 'last_name', 'email', 'phone', 'actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;

  constructor(public httpDataService:UserService , private dialog: MatDialog, private dialogService: DialogService,
    private notificationService: NotificationService) {
    this.carsData = {} as user;
  }

  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }
  ngOnInit(): void {

    this.datasource.sort = this.sort;
    this.datasource.paginator = this.paginator;
    this.getAllCars();
  }


  getAllCars() {
    this.spinner()
    this.httpDataService.getcars().subscribe((response: any) => {
      this.datasource.data = response;
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
          this.httpDataService.delete(id).subscribe((response: any) => {
            this.datasource.data = this.datasource.data.filter((o: any) => {
              return o.id !== id ? o : false;
            })
            console.log(this.datasource.data);
          })
          this.notificationService.warn('! Deleted successfully');
          this.getAllCars();
        }
      });
  }

  updatee() {
    this.isEditeMode != this.isEditeMode
    this.httpDataService.update(this.httpDataService.form.value).subscribe(() => {
      this.getAllCars();
    })
    this.httpDataService.form.reset();
    this.httpDataService.initializeFormGroup();
    this.notificationService.success(':: Submitted successfully');
    this.reloadPage();
  }

  // cancelEdit() {
  //  this.isEditeMode = false;
  //  this.carsForm.resetForm();
  //}


  // status 400 , backend yraja object object  , !! fel json-server ca fonctionne correctememnt 
  onSubmit() {
  
    if (this.httpDataService.form.valid) {
       this.httpDataService.create(this.httpDataService.form.value).subscribe(()=>{

        this.httpDataService.form.reset();
        this.httpDataService.initializeFormGroup();
        this.notificationService.success(' :: Add successfully ')
        this.getAllCars();
        this.reloadPage();
       })
      
  
    }
   
  }
  




  onEdit(row: any) {
    this.isEditeMode = true;
    this.httpDataService.populateForm(row);

  }

  onClear() {
    this.httpDataService.form.reset();
    this.httpDataService.initializeFormGroup();
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
}
