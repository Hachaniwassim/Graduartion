import { HttpClient, HttpErrorResponse, HttpHandler, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { user } from './user.model';
import { retry, catchError } from 'rxjs/operators'; 



@Injectable({
  providedIn: 'root'
})
export class HttpDataService {
  base_url ="http://localhost:3000/voiture";

  constructor(private http :HttpClient) { }

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
    );


  }
   // return an observabel with a user-facing error message 
  return throwError( 'something bad happined , please try again later .');
};


// insert a user
create(item:any):Observable<user>{
  return this.http.post<user>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
}

//get all users data 
 getcars():Observable<user>{
   return this.http.get<user>(this.base_url).pipe(retry(2),catchError(this.handleError));
 }


  // get users by ID 
  getByid(matricule:string):Observable<user>{
    return this.http.get<user>(this.base_url + '/' +matricule).pipe(retry(2),catchError(this.handleError));

  }
    // update users by Id the
    update(matricule: string , item :any){
    return this.http.put<user>(this.base_url + '/' +matricule,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
    }
   
     // delete users
    delete(matricule: string){
     return this.http.delete<user>(this.base_url + '/' +matricule,this.httpOptions).pipe(retry(2),catchError(this.handleError));
   
   
       }

}

