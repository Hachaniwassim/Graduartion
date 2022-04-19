import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

const API_URL = 'http://localhost:8077/api/test/';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }
    //http opttion
    httpOptions={ 
      headers:new HttpHeaders({
        'content-type':'application/json'
  
      })
    }
    //handel api  errors 
    handleError(error: HttpErrorResponse){
      if( error.error instanceof ErrorEvent){
      //a client-side or a neetwork error occurend .Handel it accordingly
      console.error('An Error occurend' , error.error.message)
  
    }
    else{
      // the backend may returned an successfully response code 
      // the response body may contain clues as to what went wrong 
      console.error(`backend returned code ${error.status}, ` +
      `body was : ${ error.error}`
      );}
     // return an observabel with a user-facing error message 
    return throwError( 'something bad happined , please try again later .');
  };

  // http get public content
  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  // http get board user
  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user' +this.httpOptions ,{ responseType: 'text' }).pipe(retry(2),catchError(this.handleError));
  
  }

  
  // http get board moderateur
  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'mod', { responseType: 'text' });
  }

  
  // http get board admin
  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }
}
