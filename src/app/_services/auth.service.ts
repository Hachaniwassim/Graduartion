import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

const AUTH_API = 'http://localhost:8089/api/auth/';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

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



  //login 
  login(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username,
      password
    }, this.httpOptions);
    //this.router.navigate(['home']);
  }
  //register
  register(username: string, email: string, password: string, matchingPassword: string,
    fiscaleCode: string): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username,
      email,
      password,
      matchingPassword,
      fiscaleCode
    }, this.httpOptions);
  }
 

}
