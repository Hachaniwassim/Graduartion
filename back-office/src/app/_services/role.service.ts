import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { RoleDTO } from '../models/dto/roleDTO';
import { ERole } from '../models/enum/erole';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  //api backend
  private base_url = "http://localhost:8089/role";


  roleDTO = {
    name: '',
  }

  constructor(private http: HttpClient, private datePipe: DatePipe) { }

  //http opttion
  httpOptions = {
    headers: new HttpHeaders({
      'content-type': 'application/json'

    })
  }
  //handel api  errors 
  handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      //a client-side or a neetwork error occurend .Handel it accordingly
      console.error('An Error occurend', error.error.message)

    }
    else {
      // the backend may returned an successfully response code 
      // the response body may contain clues as to what went wrong 
      console.error(`backend returned code ${error.status}, ` +
        `body was : ${error.error}`
      );
    }
    // return an observabel with a user-facing error message 
    return throwError('something bad happined , please try again later .');
  };


  // insert 
  createrole(item: any): Observable<RoleDTO> {
  
    return this.http.post<RoleDTO>(this.base_url, JSON.stringify(item), this.httpOptions).pipe(retry(2), catchError(this.handleError));
  }

  //get all team data 
  getallrole(): Observable<RoleDTO[]> {
    return this.http.get<RoleDTO[]>(this.base_url).pipe(retry(2), catchError(this.handleError));
  }


  // get team by id
  getByidrole(id: number): Observable<RoleDTO> {
    return this.http.get<RoleDTO>(this.base_url + '/' + id).pipe(retry(2), catchError(this.handleError));


  }


  // update team by Id the
  updaterole(item: RoleDTO) {
    return this.http.put<RoleDTO>(this.base_url, JSON.stringify(item), this.httpOptions).pipe(retry(2), catchError(this.handleError));
  }

  // delete groupe
  deleterole(id: number) {
    return this.http.delete<RoleDTO>(this.base_url + '/' + id, this.httpOptions).pipe(retry(2), catchError(this.handleError));

  }
  // get all groupe 
  deleteAllrole() {
    return this.http.delete(this.base_url).pipe(retry(2), catchError(this.handleError));

  }


  //find by active
  findByActiverole(active: any) {
    return this.http.get(`${this.base_url}?active${active}`);
  }



  //find by confirmed
  findByConfirmedrole(confirme: any) {
    return this.http.get(`${this.base_url}?confirme${confirme}`);
  }


  //validation formulaire
  form: FormGroup = new FormGroup({
    id: new FormControl(null),
    name: new FormControl('', Validators.required),
  });

  // inialisation formulaire 
  initializeFormGroup() {
    this.form.setValue({
      id: null,
      name: '',
    });
  }
  populateForm(role: any) {
    this.form.patchValue(_.omit(role));
  }

   //update groupe  by status
   updateroleByStatus(id : number,item : ERole){
 return this.http.put<any>(this.base_url + '/toggle-status/' + id,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
  }


}

