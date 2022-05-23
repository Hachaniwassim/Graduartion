
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { postDTO } from '../models/dto/postDTO';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  //api backend
  private base_url= environment.publicApi + "/post";
  

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
    );}
   // return an observabel with a user-facing error message 
  return throwError( 'something bad happined , please try again later .');
};



//get all  
getAllByEntreprise():Observable<postDTO[]>{
   return this.http.get<postDTO[]>(this.base_url).pipe(retry(2),catchError(this.handleError));
 }


  // get post by id
  getByid(id:number):Observable<postDTO>{
    return this.http.get<postDTO>(this.base_url + '/' +id).pipe(retry(2),catchError(this.handleError));

  }

   
}
