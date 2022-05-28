import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm } from '@angular/forms';
import { Subscription } from 'rxjs';
import { CustomHttpRespone } from '../models/custom-http-response';
import { Accountservice } from '../_services/account.service';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

/**
 * 
 * @author Tarchoun Abir
 * 
 */

export class ProfileComponent implements OnInit {

  /***************************
   * variable initialisations 
   * 
   */
  currentUser: any;
  hide = true;
  panelOpenState = true;
  account !: FormGroup;
  public refreshing: boolean | undefined;
  private subscriptions: Subscription[] = [];


  constructor(private tokenStorage: TokenStorageService, private authService: AuthService, private accountService: Accountservice, private fb: FormBuilder) {

    //validation Form
    this.account = this.fb.group({
      id: new FormControl(),
      username: new FormControl(),
      email: new FormControl(),
      password: new FormControl(),
      matchingPassword: new FormControl(),
      fiscaleCode: new FormControl(),
      accountStatus: new FormControl(),
      groupeId :new FormControl(),
      role: new FormControl(),
      createdDate: new FormControl(),
      lastModifiedDate: new FormControl(),
      entrepriseId: new FormControl(),
    });
  }

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  username? : string;

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
    this.account.patchValue(this.currentUser);
    console.log(this.currentUser)
    //authorisation
    console.log('Life Cyle Hook with spontaneous response.');
    this.isLoggedIn= true;
    this.roles = this.tokenStorage.getUser().roles;
    this.username=this.tokenStorage.getUser().username;
    this.successNotification();
  }
  tinyAlert() {
    Swal.fire('Hey there!');
  }
  successNotification() {
    Swal.fire('welcome', ' you have been logged successfully', 'success');
  }
  alertConfirmation() {
    Swal.fire({
      title: 'Are you sure?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, go ahead.',
      cancelButtonText: 'No, let me think',
    }).then((result) => {
      if (result.value) {
        Swal.fire('Removed!', 'Product removed successfully.', 'success');
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelled', 'Product still in our database.)', 'error');
      }
    });
  }


  /*********************************
   * Change Password With mail 
   */
  public onResetPassword(emailForm: NgForm): void {
    this.refreshing = true;
    const emailAddress = emailForm.value['reset-password-email'];
    this.subscriptions.push(
      this.accountService.resetPasswordtoken(emailAddress).subscribe(
        (response: CustomHttpRespone) => {
          console.log(response);
          this.refreshing = false;
        },
        (error: HttpErrorResponse) => {

          this.refreshing = false;
        },
        () => emailForm.reset()
      )
    );
  }


  /*********************************
   * update info
   */
  save() {
    this.accountService.update(this.account.getRawValue()).subscribe(r => {
      console.log("updated", r);

    });
  }
}
