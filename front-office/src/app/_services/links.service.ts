import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { LinksDTO } from '../models/dto/linksDTO';

@Injectable({
  providedIn: 'root'
})
export class LinksService {

  //api backend
  private base_url= environment.publicApi +"/links-public";

  constructor(private http :HttpClient) { }

  
//get all by entreprise
getAlllinksByEntreprise():any{
 return this.http.get<LinksDTO[]>(this.base_url + '/list-liens/'+environment.enterpriseId);
}


// get by id
getByidlink(id:number):Observable<LinksDTO>{
  return this.http.get<LinksDTO>(this.base_url + '/' +id);

}


 
}
