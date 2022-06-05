import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Page2DTO } from '../models/dto/page2DTO';
import { Page3DTO } from '../models/dto/page3DTO';

@Injectable({
  providedIn: 'root'
})
/***********
 * 
 * @author Tarchoun Abir
 * 
 */
export class Assistanceservice {

  //api backend
  private base_url= environment.publicApi +"/page-assistance-public";
  
  headers = new HttpHeaders().set('Content-Type', 'application/json');


  constructor(private http :HttpClient) { }

  
//get all by entreprise
getassistanceByEntreprise():any{
 return this.http.get<Page2DTO[]>(this.base_url + '/list-assistance/' + environment.enterpriseId);
}


// get by id
getByidassistance(id:number):Observable<Page2DTO>{
  return this.http.get<Page2DTO>(this.base_url + '/' +id);

}


 
}
