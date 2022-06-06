import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { EntrepriseDTO } from '../models/dto/entreprisDTO';
import { GroupeDTO } from '../models/dto/groupeDTO';
@Injectable({
  providedIn: 'root'
})



/**************************
 * 
 * @author Tarchoun Abir
 * 
 */
export class EntrepriseService {



  //api backend
  private base_url = environment.privateApi + '/entreprise';

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


  // insert entreprise 
  createEntreprise(item: EntrepriseDTO): Observable<EntrepriseDTO> {
    return this.http.post<EntrepriseDTO>(this.base_url, item, this.httpOptions).pipe(retry(2), catchError(this.handleError));
  }



  //get all entreprise
  getAllEntreprise(): Observable<EntrepriseDTO[]> {
    return this.http.get<EntrepriseDTO[]>(this.base_url).pipe(retry(2), catchError(this.handleError));
  }


  //get all groupe
  getallGroupe(): Observable<GroupeDTO[]> {
    return this.http.get<GroupeDTO[]>(this.base_url).pipe(retry(2), catchError(this.handleError));
  }


 
  // update entreprise
  updateEntreprise(item: EntrepriseDTO) {
    return this.http.put<EntrepriseDTO>(this.base_url, item, this.httpOptions).pipe(retry(2), catchError(this.handleError));
  }

  // delete groupe
  deleteEntreprise(id: number) {
    return this.http.delete<EntrepriseDTO>(this.base_url + '/' + id, this.httpOptions).pipe(retry(2), catchError(this.handleError));

  }



  //validation formulaire
    form: FormGroup = new FormGroup({
    id: new FormControl(null),
    companyname: new FormControl('', [Validators.required]),
    phone: new FormControl(''),
    email: new FormControl('', [Validators.email, Validators.required]),
    codefiscale: new FormControl('', [Validators.required]),
    note: new FormControl(''),
    fax: new FormControl('', [Validators.required,Validators.minLength(8)]),
    groupeId: new FormControl(null, [Validators.required]),
    companyBusinessId: new FormControl(null, [Validators.required]),
    city: new FormControl('',[ Validators.required]),
    refrente: new FormControl(''),
    street: new FormControl(''),
    adresse: new FormControl('',Validators.required),
    websiteUrl: new FormControl(''),
    siretNumber:new FormControl('',[ Validators.required,Validators.maxLength(14)]),
    codeBank:new FormControl('',[ Validators.required,Validators.minLength(14),Validators.maxLength(14)])



  });

  // inialisation formulaire 
  initializeFormGroup() {
    this.form.setValue({
      id: null,
      companyname: '',
      phone: '',
      fax: '',
      codefiscale: '',
      note: '',
      email: '',
      groupeId: null,
      companyBusinessId: null,
      adresse: '',
      city: '',
      street: '',
      refrente: '',
      siretNumber: '',
      CodeBank:'',
      websiteUrl: ''

    });
  }

  // get info for update
  populateForm(entreprise: any) {
    console.log(entreprise);
    this.form.patchValue(_.omit(entreprise));
  }

}

