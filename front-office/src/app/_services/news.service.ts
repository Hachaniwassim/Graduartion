import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { newsDTO } from '../models/dto/newsDTO';

@Injectable({
  providedIn: 'root'
})
export class Newsservice {

  //api backend
  private base_url= environment.publicApi +"/nwes";
  
  headers = new HttpHeaders().set('Content-Type', 'application/json');

 LinkDTO ={
    title:'', 
    htmlContent: '',
  }
  constructor(private http :HttpClient) { }

  
//get all by entreprise
getAllnewsByEntreprise():Observable<newsDTO>{
 return this.http.get<newsDTO>(this.base_url);
}


// get by id
getByidnew(id:number):Observable<newsDTO>{
  return this.http.get<newsDTO>(this.base_url + '/' +id);

}


 
}
