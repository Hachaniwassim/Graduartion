import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { categoryDTO } from '../models/dto/categoryDTO';

@Injectable({
  providedIn: 'root'
})
export class Gategoryservice {

  //api backend
  private base_url= environment.privateApi + '/category';
  


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
createCategory(request:any){
  return this.http.post<categoryDTO>(`${this.base_url + '/post-category' }`, request);
}

//get all 
getallCategorie():Observable<categoryDTO[]>{
   return this.http.get<categoryDTO[]>(this.base_url + '/list-category' + localStorage.getItem('idEntreprise'));
 }


  updateCategory(request: any) {
  console.log('the request ====>',request)
  return this.http.post<categoryDTO>(`${this.base_url + '/update-category' }`, request);
  
}

  // get  by id
  getByid(id:number):Observable<categoryDTO>{
    return this.http.get<categoryDTO>(this.base_url + '/' +id).pipe(retry(2),catchError(this.handleError));

  }
    // delete
    delete(id:number){
      return this.http.delete<categoryDTO>(this.base_url + '/' +id,this.httpOptions).pipe(retry(2),catchError(this.handleError));

}

//validation formulaire
    form : FormGroup= new FormGroup({
    id: new FormControl(null),
    image: new FormControl('',Validators.required),
    title : new FormControl('',[ Validators.required]),
    description : new FormControl(''),
    menuimage : new FormControl(''),
    bannerimage : new FormControl(''),
    entrepriseId : new FormControl(''),
    subtitle : new FormControl(''),
    createdDate : new FormControl(''),
    lastModifiedDate : new FormControl(''),
    
});

// inialisation formulaire 
initializeFormGroup() {
  this.form.setValue({
    id :null,
    image: '',
    title: '',
    subtitle: '',
    description: '',
    menuimage: '',
    bannerimage: '',
    createdDate: '',
    lastModifiedDate: '',
   
  });
}
populateForm(category: any) {
  this.form.patchValue(_.omit(category));
}
}
