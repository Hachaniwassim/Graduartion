import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { configgeneraleDTO } from '../models/dto/configgeneraleDTO';

@Injectable({
  providedIn: 'root'
})
export class ConfigGeneraleService {

  //api backend
  private base_url="http://localhost:8089/";
  
  headers = new HttpHeaders().set('Content-Type', 'application/json');

 CompanyBusinessDTO ={
  facebook:'', 
  twitter: '',
  youtube: '',
  image: '',
  adresse: '',
  email: '',
  phone: '',
  fax: '',
  title: '',
  newslettertitle: '',
  newslettersubtitle: '',
  tagline: '',
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
create(item : configgeneraleDTO):Observable<configgeneraleDTO>{
  return this.http.post<configgeneraleDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
}

//get all account data 
all():Observable<configgeneraleDTO>{
   return this.http.get<configgeneraleDTO>(this.base_url).pipe(retry(2),catchError(this.handleError));
 }


  // get account by id
  getByid(id:number):Observable<configgeneraleDTO>{
    return this.http.get<configgeneraleDTO>(this.base_url + '/' +id).pipe(retry(2),catchError(this.handleError));

  }

   // update account by Id the
   update(item : configgeneraleDTO){
    return this.http.put<configgeneraleDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
   }

    // delete accounts
    delete(id:number){
      return this.http.delete<configgeneraleDTO>(this.base_url + '/' +id,this.httpOptions).pipe(retry(2),catchError(this.handleError));

}

//validation formulaire
  form : FormGroup= new FormGroup({
    id: new FormControl(null),
    facebook: new FormControl('',Validators.required),
    twitter : new FormControl('',[ Validators.required]),
    youtube : new FormControl('',[ Validators.required]),
    image : new FormControl('',[ Validators.required]),
    adresse : new FormControl('',[ Validators.required]),
    email : new FormControl('',[ Validators.required]),
    phone : new FormControl('',[ Validators.required]),
    fax : new FormControl('',[ Validators.required]),
    title : new FormControl('',[ Validators.required]),
    newslettertitle : new FormControl('',[ Validators.required]),
    newslettersubtitle : new FormControl('',[ Validators.required]),
    tagline : new FormControl('',[ Validators.required]),
});

// inialisation formulaire 
initializeFormGroup() {
  this.form.setValue({
    id :null,
    facebook: null,
    twitter: null,
    youtube: null,
    image: null,
    adresse: null,
    email: null,
    phone: null,
    fax: null,
    title: null,
    newslettertitle: null,
    newslettersubtitle: null,
    tagline: null,
  });
}
populateForm(company: any) {
  this.form.patchValue(_.omit(company));
}
}
