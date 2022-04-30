import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { productsDTO } from '../models/dto/productsDTO';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  //api backend
  private base_url="http://localhost:8089/product";
  
  headers = new HttpHeaders().set('Content-Type', 'application/json');

 ProductDTO ={
  title:'', 
  detailimage: '',
  note: '',
  name: '',
  image: '',
  consultationNumber: '',


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
createCompayBusiness(item : productsDTO):Observable<productsDTO>{
return this.http.post<productsDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
}

//get all team data 
getAllCompanyBussiness():Observable<productsDTO>{
 return this.http.get<productsDTO>(this.base_url).pipe(retry(2),catchError(this.handleError));
}


// get team by id
getByidCompany(id:number):Observable<productsDTO>{
  return this.http.get<productsDTO>(this.base_url + '/' +id).pipe(retry(2),catchError(this.handleError));

}

 // update team by Id the
 updateCompanyBusiness(item : productsDTO){
  return this.http.put<productsDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
 }

  // delete cars
  deleteCompanyBusiness(id:number){
    return this.http.delete<productsDTO>(this.base_url + '/' +id,this.httpOptions).pipe(retry(2),catchError(this.handleError));

}

//validation formulaire
  form : FormGroup= new FormGroup({
    id: new FormControl(null),
    title: new FormControl('',Validators.required),
    detailimage : new FormControl('',[ Validators.required]),
    note : new FormControl('',[ Validators.required]),
    name : new FormControl('',[ Validators.required]),
    image : new FormControl(''),
    consultationNumber : new FormControl(''),

 
});

// inialisation formulaire 
initializeFormGroup() {
  this.form.setValue({
    id :null,
    title: null,
    detailimage: null,
    note: null,
    name: null,
    image: null,
    consultationNumber: null,

  });
}
populateForm(product: any) {
  this.form.patchValue(_.omit(product));
}
}
