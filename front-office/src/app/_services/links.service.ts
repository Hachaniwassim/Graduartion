import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { linksDTO } from '../models/dto/linksDTO';

@Injectable({
  providedIn: 'root'
})
export class Linksservice {

  //api backend
  private base_url= environment.publicApi +"/product";
  
  headers = new HttpHeaders().set('Content-Type', 'application/json');

 LinkDTO ={
    title:'', 
    htmlContent: '',
  }
  constructor(private http :HttpClient) { }

  
//get all by entreprise
getAlllinksByEntreprise():Observable<linksDTO>{
 return this.http.get<linksDTO>(this.base_url);
}


// get by id
getByidlink(id:number):Observable<linksDTO>{
  return this.http.get<linksDTO>(this.base_url + '/' +id);

}


 
}
