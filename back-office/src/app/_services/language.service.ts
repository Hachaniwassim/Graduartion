import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { languageDTO } from '../models/dto/languageDTO';

@Injectable({
  providedIn: 'root'
})
export class Accountservice {

  //api backend
  private base_url="http://localhost:8089/";
  
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  Language={
    lang:'', 
    name: '',
    image: '',
    active: true,
  }
  constructor(private http :HttpClient, private datePipe: DatePipe) { }

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


// insert 
createlanguage(item : languageDTO):Observable<languageDTO>{
  return this.http.post<languageDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
}

//get all account data 
alllanguages():Observable<languageDTO>{
   return this.http.get<languageDTO>(this.base_url).pipe(retry(2),catchError(this.handleError));
 }


  // get account by id
  getByidlanguage(id:number):Observable<languageDTO>{
    return this.http.get<languageDTO>(this.base_url + '/' +id).pipe(retry(2),catchError(this.handleError));

  }

   // update language by Id the
   updatelanguage(item : languageDTO){
    return this.http.put<languageDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
   }

    // delete languages
    deletelanguage(id:number){
      return this.http.delete<languageDTO>(this.base_url + '/' +id,this.httpOptions).pipe(retry(2),catchError(this.handleError));

}

//validation formulaire language
  form : FormGroup= new FormGroup({
    id: new FormControl(null),
    lang: new FormControl('',Validators.required),
    name : new FormControl('',[ Validators.required]),
    image : new FormControl('',[ Validators.required]),
    active: new FormControl('', [Validators.required]),
});

// inialisation formulaire  language
initializeFormGroup() {
  this.form.setValue({
    id :null,
    lang: null,
    name: null,
    image: null,
    active: true,
  });
}
populateForm(language: any) {
  this.form.patchValue(_.omit(language));
}
}
