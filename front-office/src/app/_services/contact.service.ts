import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import {FormEntityDTO } from '../models/dto/formEntityDTO';

@Injectable({
  providedIn: 'root'
})
export class Contactservice {

  //api backend
  private base_url=environment.publicApi + '/contact-public';
  
  constructor(private http :HttpClient) { }
  //get all by entreprise
getByEntreprise():any{
  return this.http.get<FormEntityDTO[]>(this.base_url + '/list-form/'+environment.enterpriseId);
 }
 
 
 // get by id
 getByid(id:number):Observable<FormEntityDTO>{
   return this.http.get<FormEntityDTO>(this.base_url + '/' +id);
 
 }
 create(item :any):any{
  return this.http.post<FormEntityDTO>(this.base_url +  '/post-form',item);
}



}
