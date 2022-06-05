import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { categoryDTO } from '../models/dto/categoryDTO';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  //api backend
  private base_url= environment.publicApi+ '/category-public';
  

  constructor(private http :HttpClient) { }



//get all
getallCategorieByEntreprise():Observable<categoryDTO[]>{
   return this.http.get<categoryDTO[]>(this.base_url + '/list-category/' + environment.enterpriseId);
 }


  // get  by id
  getByid(id:number):Observable<categoryDTO>{
    return this.http.get<categoryDTO>(this.base_url + '/' +id);
}

}