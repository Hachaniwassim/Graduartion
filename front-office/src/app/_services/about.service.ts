import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { aboutDTO } from '../models/dto/aboutDTO';

@Injectable({
  providedIn: 'root'
})
export class Aboutservice {

  //api backend
  private base_url= environment.publicApi +"/links";
  
  headers = new HttpHeaders().set('Content-Type', 'application/json');

 LinkDTO ={
    title:'', 
    htmlContent: '',
  }
  constructor(private http :HttpClient) { }

  
//get all by entreprise
getAllaboutByEntreprise():Observable<aboutDTO>{
 return this.http.get<aboutDTO>(this.base_url);
}


// get by id
getByidabout(id:number):Observable<aboutDTO>{
  return this.http.get<aboutDTO>(this.base_url + '/' +id);

}


 
}
