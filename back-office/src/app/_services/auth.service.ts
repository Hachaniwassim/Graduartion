import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { ChangePasswordRequest } from '../models/entity/change-password-request';
import { CustomHttpRespone } from '../models/entity/custom-http-response';

const AUTH_API= environment.publicApi + '/api/auth' ;
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})




/**
 * @author Tarchoun Abir
 */


export class AuthService {

  constructor(private http: HttpClient) { }

  
    login(username: string, password: string): Observable<any> {
     
       let logedUser= this.http.post(AUTH_API + '/signin', {
        username,
        password
      }, httpOptions);
    
      return logedUser
     
    }
  
    create(username: string, email: string, password: string, matchingPassword:string ,
      fiscaleCode:string): Observable<any> {
      return this.http.post(AUTH_API + '/signup', {
        username,
        email,
        password,
        matchingPassword ,
        fiscaleCode,
        role: ['admin']
      }, httpOptions);
    }
  
    changePassword(request: ChangePasswordRequest) {
      return this.http.post<boolean>(environment.privateApi + '/change-password', request)
        .pipe(map(response => response));
    }

  public forgetPassword(email: string): Observable<CustomHttpRespone> {
    return this.http.get<CustomHttpRespone>(`${AUTH_API}/resetpassword/${email}`);
  }
 
 

}
