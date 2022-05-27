import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { CompanyBusinessDTO } from '../models/dto/companyBusinessDTO';
import { GroupeDTO } from '../models/dto/groupeDTO';

@Injectable({
  providedIn: 'root'
})

/**************************
 *
 * @author Tarchoun Abir
 * 
 */


export class GroupeService {

  //api backend
  private base_url = environment.publicApi+ '/groupe';


  constructor(private http: HttpClient) { }

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
  createGroupe(item: any): Observable<GroupeDTO> {
  
    return this.http.post<GroupeDTO>(`${this.base_url}`, item, this.httpOptions).pipe(retry(2), catchError(this.handleError));
  }

  //get all data 
  getallGroupe(): Observable<GroupeDTO[]> {
    return this.http.get<GroupeDTO[]>(`${this.base_url}`).pipe(retry(2), catchError(this.handleError));
  }


  // get by id
  getByidGroupe(id: number): Observable<GroupeDTO> {
    return this.http.get<GroupeDTO>(`${this.base_url}` + '/' + id).pipe(retry(2), catchError(this.handleError));


  }
  //get all  data 
getAllCompanyBussiness():Observable<CompanyBusinessDTO[]>{
  return this.http.get<CompanyBusinessDTO[]>(`${this.base_url}`).pipe(retry(2),catchError(this.handleError));
}

  // update by Id 
  updateGroupe(item: GroupeDTO) {
    return this.http.put<GroupeDTO>(`${this.base_url}`, item, this.httpOptions).pipe(retry(2), catchError(this.handleError));
  }

  // delete groupe
  deleteGroupe(id: number) {
    return this.http.delete<GroupeDTO>(`${this.base_url}` + '/' + id, this.httpOptions).pipe(retry(2), catchError(this.handleError));

  }
 


  populateForm(groupe: any) {
    this.form.patchValue(_.omit(groupe));
  }

   
  //update by status
  updateGroupetByStatus(id: number, item: string) {
    return this.http
      .put<any>(
        this.base_url + '/toggle-status/' + id,
        JSON.stringify(item),
        this.httpOptions
      )
      .pipe(retry(2), catchError(this.handleError));
  }
     
   //validation formulaire
   form: FormGroup = new FormGroup({
    id: new FormControl(null),
    description: new FormControl('', Validators.required),
    name: new FormControl('', [Validators.required]),
    groupStatus: new FormControl(null),
    maxOperateur:new FormControl(null)


  });

  // inialisation formulaire 
  initializeFormGroup() {
    this.form.setValue({
      id: null,
      name: '',
      description: '',
      groupStatus: '',
      maxOperateur:''
    });
  }

}

