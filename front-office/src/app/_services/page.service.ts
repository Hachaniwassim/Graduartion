import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { pagesDTO } from '../models/dto/pageDTO';

@Injectable({
  providedIn: 'root'
})
export class PageService {

  //api backend
  private base_url= environment.publicApi +"/pages";
  


  constructor(private http :HttpClient) { }

//get all page 
 getAllPagesByEntreprise():Observable<pagesDTO[]>{
   return this.http.get<pagesDTO[]>(this.base_url);
 }


  // get page by id
  getByidPages(id:number):Observable<pagesDTO>{
    return this.http.get<pagesDTO>(this.base_url + '/' +id);

  }
}
