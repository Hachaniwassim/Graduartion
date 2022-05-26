
import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, of, ReplaySubject, throwError } from 'rxjs';
import { catchError, map, retry, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { AccountDTO } from '../models/dto/accountDTO';
import { CustomHttpRespone } from '../models/entity/custom-http-response';
import { FormBuilder } from '@angular/forms';

@Injectable({
  providedIn: 'root',
})
export class Accountservice {
  //api backend
  private base_url = environment.privateApi + '/user';

  constructor(private http: HttpClient, private  fb: FormBuilder) {}

  //http opttion
  httpOptions = {
    headers: new HttpHeaders({
      'content-type': 'application/json',
    }),
  };
  //handel api  errors
  handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      //a client-side or a neetwork error occurend .Handel it accordingly
      console.error('An Error occurend', error.error.message);
    } else {
      // the backend may returned an successfully response code
      // the response body may contain clues as to what went wrong
      console.error(
        `backend returned code ${error.status}, ` + `body was : ${error.error}`
      );
    }
    // return an observabel with a user-facing error message
    return throwError('something bad happined , please try again later .');
  }

  // insert
  create(item: any) {
    return this.http
      .post<any>(`${this.base_url}`, JSON.stringify(item), this.httpOptions)
      .pipe(retry(2), catchError(this.handleError));
  }

  //get all account data
  all(): Observable<AccountDTO[]> {
    return this.http
      .get<AccountDTO[]>(`${this.base_url}`)
      .pipe(retry(2), catchError(this.handleError));
  }

  //zied
  entreprises(): Observable<any[]> {
    return this.http.get<any[]>(environment.privateApi + '/entreprise');
  }
  //semah
  changeRoel(id_account: Number, id_role: any) {
    id_role = Number(id_role);
    let result = this.http.post<any>(
      environment.privateApi + '/role/update-role',
      {
        id_role,
        id_account,
      }
    );

    console.log(
      'Request result ====>' + environment.privateApi + '/assign-entreprise'
    );
    return result;
  }

  // get account by id
  getByid(id: number): Observable<AccountDTO> {
    return this.http
      .get<AccountDTO>(`${this.base_url}` + '/' + id)
      .pipe(retry(2), catchError(this.handleError));
  }

  // update account by Id the
  update(item: any) {
    return this.http
      .post<any>(`${this.base_url}`, JSON.stringify(item), this.httpOptions)
      .pipe(retry(2), catchError(this.handleError));
  }

  assignEntreprise(id_account: Number, id_entreprise: Number) {
    id_entreprise = Number(id_entreprise);
    let result = this.http.post<any>(
      environment.privateApi + '/entreprise/assign-entreprise',
      {
        id_entreprise,
        id_account,
      }
    );

    console.log(
      'Request result ====>' + environment.privateApi + '/assign-entreprise'
    );
    return result;
  }

  // delete accounts
  delete(id: number) {
    return this.http
      .delete<AccountDTO>(`${this.base_url}` + '/' + id, this.httpOptions)
      .pipe(retry(2), catchError(this.handleError));
  }

  //get value for update
  populateForm(account: any) {
    this.form.patchValue(_.omit(account));
  }

  //update account by status
  updateAccountByStatus(id: number, item: string) {
    return this.http
      .put<any>(
        this.base_url + '/toggle-status/' + id,
        JSON.stringify(item),
        this.httpOptions
      )
      .pipe(retry(2), catchError(this.handleError));
  }

  //reset password with token

  public resetPasswordtoken(email: string): Observable<CustomHttpRespone> {
    return this.http.get<CustomHttpRespone>(
      `${this.base_url}/resetpasswordtoken/${email}`
    );
  }

  //validation formulaire
  form: FormGroup = new FormGroup({
    id: new FormControl(null),
    username: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    matchingPassword: new FormControl(''),
    fiscaleCode: new FormControl(''),
    accountStatus: new FormControl(''),
    groupeId: new FormControl(''),
    entrepriseId: new FormControl(''),
    roleId: new FormControl(''),
    rolesArray: this.fb.array([]),
    role: new FormControl(''),
  });

  // inialisation formulaire
  initializeFormGroup() {
    this.form.setValue({
      id: null,
      username: null,
      email: null,
      password: null,
      matchingPassword: null,
      fiscaleCode: null,
      accountStatus: null,
      groupeId: null,
      entrepriseId: null,
      roleId: null,
      rolesArray: [],
      role: null,
    });
  }

  updateAccount(account: AccountDTO) {
    return this.http.put(this.base_url, account).toPromise();
  }
}
