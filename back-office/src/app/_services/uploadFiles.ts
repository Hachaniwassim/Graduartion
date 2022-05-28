import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { CompanyBusinessDTO } from '../models/dto/companyBusinessDTO';

@Injectable({
  providedIn: 'root'
})
export class UploadFiles {

  //api backend
  private base_url=environment.privateApi + '/upload';

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


uploadFile(file: File):any {
  const formData: FormData = new FormData();

  formData.append('file', file);

  return this.http.post( "http://localhost:8089/api/private/upload/PRODUCT/"+localStorage.getItem('idEntreprise'), formData,);
}


getFile(){
   return this.http.get(this.base_url + localStorage.getItem('idEntreprise') +'/PRODUCT' );
}




}
