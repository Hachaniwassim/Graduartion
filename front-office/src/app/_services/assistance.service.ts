import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { assistanceDTO } from '../models/dto/assistanceDTO';

@Injectable({
  providedIn: 'root'
})
export class Assistanceservice {

  //api backend
  private base_url= environment.publicApi +"/links";
  
  headers = new HttpHeaders().set('Content-Type', 'application/json');

 LinkDTO ={
    title:'', 
    htmlContent: '',
  }
  constructor(private http :HttpClient) { }

  
//get all by entreprise
getassistanceByEntreprise():Observable<assistanceDTO>{
 return this.http.get<assistanceDTO>(this.base_url);
}


// get by id
getByidassistance(id:number):Observable<assistanceDTO>{
  return this.http.get<assistanceDTO>(this.base_url + '/' +id);

}


 
}
