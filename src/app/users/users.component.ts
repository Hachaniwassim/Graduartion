import { Component, OnInit , ViewChild  } from '@angular/core';
import { users } from './user';
import { user } from './user.model';
import { MaterialModule } from '../material/material.module';
import { NgForm } from '@angular/forms';
import {HttpDataService} from './user.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  //public usersList: user[] = users;
  @ViewChild('carsForm', {  static : false}) carsForm!:NgForm;
  carsData !: user ;
  voiture !: user[] ;

  datasource = new MatTableDataSource(this.voiture)
  displayedColumns : string []= [ 'first_name', 'last_name', 'email', 'phone', 'actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;

  isEditeMode = false ;
  searchKey!: string ;

  
  constructor( private httpDataService : HttpDataService) { 
    this.carsData = {} as user;
 }

 ngAfterViewInit() {
   this.datasource.paginator = this.paginator;
   this.datasource.sort = this.sort;
 }
 ngOnInit(): void {

   this. datasource.paginator = this.paginator;

 this.getAllCars();
}


 getAllCars(){
   this.httpDataService.getcars().subscribe((response:any) =>{
   this.datasource.data=response;
   });

   }
   onSearchClear() {
     this.searchKey = "";
     this.applyFilter();
   }

   applyFilter() {
     this.datasource.filter = this.searchKey.trim().toLowerCase();
   }

}
