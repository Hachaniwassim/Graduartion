import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { plateforomeDTO } from '../models/dto/plateformeDTO';

@Injectable({
  providedIn: 'root'
})
export class Plateformeservice {

  //api backend
  private base_url="http://localhost:8089/";
  
  headers = new HttpHeaders().set('Content-Type', 'application/json');

 CompanyBusinessDTO ={
  image:'', 
  email: '',
  phone: '',
  adresse: '',
  published: '',

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
create(item : plateforomeDTO):Observable<plateforomeDTO>{
  return this.http.post<plateforomeDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
}

//get all plateforme data 
all():Observable<plateforomeDTO>{
   return this.http.get<plateforomeDTO>(this.base_url).pipe(retry(2),catchError(this.handleError));
 }


  // get plateforme by id
  getByid(id:number):Observable<plateforomeDTO>{
    return this.http.get<plateforomeDTO>(this.base_url + '/' +id).pipe(retry(2),catchError(this.handleError));

  }

   // update plateforme by Id the
   update(item : plateforomeDTO){
    return this.http.put<plateforomeDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
   }

    // delete plateformes
    delete(id:number){
      return this.http.delete<plateforomeDTO>(this.base_url + '/' +id,this.httpOptions).pipe(retry(2),catchError(this.handleError));

}

//validation formulaire
  form : FormGroup= new FormGroup({
    id: new FormControl(null),
    image: new FormControl('',Validators.required),
    email : new FormControl('',[ Validators.required]),
    phone : new FormControl('',[ Validators.required]),
    adresse : new FormControl('',[ Validators.required]),
    published : new FormControl('',[ Validators.required]),

 
});

// inialisation formulaire 
initializeFormGroup() {
  this.form.setValue({
    id :null,
    image: null,
    email: null,
    phone: null,
    adresse: null,
    published: null,
  });
}
populateForm(company: any) {
  this.form.patchValue(_.omit(company));
}
}
