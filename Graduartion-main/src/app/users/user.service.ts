import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHandler, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators'; 
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { user } from './user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  //private apiUrl='https://my-json-server.typicode.com/Hachaniwassim/userapi/users';



    // API BACKEND
 private base_url='http://localhost:3000/users';

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


// insert a car
create(car : user):Observable<any>{
  return this.http.post<user>(this.base_url,JSON.stringify(car),this.httpOptions).pipe(retry(2),catchError(this.handleError));
}

//get all cars data 
 getcars():Observable<user>{
   return this.http.get<user>(this.base_url).pipe(retry(2),catchError(this.handleError));
 }


  // get carq by id
  getByid(id:number):Observable<user>{
    return this.http.get<user>(this.base_url + '/' +id).pipe(retry(2),catchError(this.handleError));

  }

   // update cars by Id the
   update(item :any){
     return this.http.put<user>(this.base_url + '/' +item.id,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
   }

    // delete cars
    delete(id:number){
      return this.http.delete<user>(this.base_url + '/' +id,this.httpOptions).pipe(retry(2),catchError(this.handleError));

}

//validation formulaire
form : FormGroup= new FormGroup({
  id: new FormControl(null),
  first_name: new FormControl('',Validators.required),
  last_name : new FormControl('',[ Validators.required,Validators.minLength(3)]),
  email: new FormControl('',[Validators.required,Validators.minLength(3)]),
  phone : new FormControl('',[Validators.required,Validators.maxLength(9)]),
});

// inialisation formulaire 
initializeFormGroup() {
this.form.setValue({
  id :'',
  first_name: '',
  last_name: '',
  email: '',
  phone: '',
});
}



populateForm(user: any) {
  this.form.patchValue(_.omit(user));
}

}