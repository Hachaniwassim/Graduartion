import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { privacyDTO } from '../models/dto/privacyDTO';

@Injectable({
  providedIn: 'root'
})
/**
 * @author Tarchoun Abir
 * 
 */
export class Privacyservice {

  //api backend
  private base_url = environment.publicApi +"/privacy-public";

  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }


  //get all  by enterpriseId
  getallPrivacy():any {
    return this.http.get<privacyDTO[]>(this.base_url + '/list-privacy/'+ environment.enterpriseId);
  }


  // get account by id
  getPrivacyByid(id: number): Observable<privacyDTO> {
    return this.http.get<privacyDTO>(this.base_url + '/' + id);

  }


   
}
