import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import {CustomHttpRespone} from "../models/custom-http-response";

const AUTH_API= environment.publicApi+'/auth/';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

 


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

  public resetPasswordtoken(email: string): Observable<CustomHttpRespone> {
    return this.http.get<CustomHttpRespone>(`${AUTH_API}resetpasswordtoken/${email}`);
  }

}
