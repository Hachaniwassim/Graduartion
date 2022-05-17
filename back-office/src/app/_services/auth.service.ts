import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import {CustomHttpRespone} from "../models/custom-http-response";

const AUTH_API= environment.api +'/api/auth/';


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
  login(username: string, password: string) {
    return this.http.post(AUTH_API + 'signin', {
      username,
      password
    })

  }
  //register
  register(username: string, email: string, password: string, matchingPassword: string,
    fiscaleCode: string) {
    return this.http.post(AUTH_API + 'signup', {
      username,
      email,
      password,
      matchingPassword,
      fiscaleCode
    });
  }

  public resetPassword(email: string): Observable<CustomHttpRespone> {
    return this.http.get<CustomHttpRespone>(`${AUTH_API}resetpassword/${email}`);
  }
  public resetPasswordtoken(email: string): Observable<CustomHttpRespone> {
    return this.http.get<CustomHttpRespone>(`${AUTH_API}resetpasswordtoken/${email}`);
  }

}
