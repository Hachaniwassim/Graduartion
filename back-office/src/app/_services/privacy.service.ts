import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { privacyDTO } from '../models/dto/privacyDTO';

@Injectable({
  providedIn: 'root'
})
export class Privacyservice {

  //api backend
  private base_url = "http://localhost:8089/privacy";

  headers = new HttpHeaders().set('Content-Type', 'application/json');

  PrivacyDTO = {
    title: '',
    description: '',
    description2: '',
    description3: '',
  }
  constructor(private http: HttpClient, private datePipe: DatePipe) { }

  //http opttion
  httpOptions = {
    headers: new HttpHeaders({
      'content-type': 'application/json'

    })
  }
  //handel api  errors 
  handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      //a client-side or a neetwork error occurend .Handel it accordingly
      console.error('An Error occurend', error.error.message)

    }
    else {
      // the backend may returned an successfully response code 
      // the response body may contain clues as to what went wrong 
      console.error(`backend returned code ${error.status}, ` +
        `body was : ${error.error}`
      );
    }
    // return an observabel with a user-facing error message 
    return throwError('something bad happined , please try again later .');
  };


  // insert 
  createPrivacy(item: privacyDTO): Observable<privacyDTO> {
    return this.http.post<privacyDTO>(this.base_url, JSON.stringify(item), this.httpOptions).pipe(retry(2), catchError(this.handleError));
  }

  //get all account data 
  getallPrivacy(): Observable<privacyDTO[]> {
    return this.http.get<privacyDTO[]>(this.base_url).pipe(retry(2), catchError(this.handleError));
  }


  // get account by id
  getPrivacyByid(id: number): Observable<privacyDTO> {
    return this.http.get<privacyDTO>(this.base_url + '/' + id).pipe(retry(2), catchError(this.handleError));

  }

  // update account by Id the
  updatePrivacy(item: any) {
    return this.http.put(this.base_url, JSON.stringify(item), this.httpOptions).pipe(retry(2), catchError(this.handleError));
  }

  // delete accounts
  deletePrivacy(id: number) {
    return this.http.delete<privacyDTO>(this.base_url + '/' + id, this.httpOptions).pipe(retry(2), catchError(this.handleError));

  }



   
}
