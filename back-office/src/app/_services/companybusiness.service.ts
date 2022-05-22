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
export class CompanybusinessService {

  //api backend
  private base_url=environment.privateApi + '/company';

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


// insert 
createCompayBusiness(item : CompanyBusinessDTO):Observable<CompanyBusinessDTO>{
  return this.http.post<CompanyBusinessDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
}

//get all team data 
getAllCompanyBussiness():Observable<CompanyBusinessDTO[]>{
   return this.http.get<CompanyBusinessDTO[]>(`${this.base_url}`).pipe(retry(2),catchError(this.handleError));
 }


  // get team by id
  getByidCompany(id:number):Observable<CompanyBusinessDTO>{
    return this.http.get<CompanyBusinessDTO>(`${this.base_url}`+ '/' +id).pipe(retry(2),catchError(this.handleError));

  }

   // update team by Id the
   updateCompanyBusiness(item : CompanyBusinessDTO){
    return this.http.put<CompanyBusinessDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
   }

    // delete cars
    deleteCompanyBusiness(id:number){
      return this.http.delete<CompanyBusinessDTO>(`${this.base_url}`+ '/' +id,this.httpOptions).pipe(retry(2),catchError(this.handleError));

}

//validation formulaire
  form : FormGroup= new FormGroup({
    id: new FormControl(null),
    description: new FormControl('',Validators.required),
    domainename : new FormControl('',[ Validators.required]),
 
});

// inialisation formulaire 
initializeFormGroup() {
  this.form.setValue({
    id :null,
    description: '',
    domainename: '',

  });
}
populateForm(company: any) {
  this.form.patchValue(_.omit(company));
}
}
