import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import Swal from 'sweetalert2';
import { CustomHttpRespone } from '../models/entity/custom-http-response';
import { Accountservice } from '../_services/account.service';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';
import {Location} from '@angular/common';

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
  currentUser !: any;
  hide = true;
  panelOpenState = true;
  account !: FormGroup;
  public refreshing: boolean | undefined;
  private subscriptions: Subscription[] = [];


  constructor(private token: TokenStorageService,public router: Router, public _location : Location,private authService: AuthService, private accountService: Accountservice, public _Snackbar : MatSnackBar,private fb: FormBuilder) {

    //validation Form
    this.account = this.fb.group({
      id: new FormControl(),
      username: new FormControl(),
      email: new FormControl(),
      fiscaleCode: new FormControl(),
    });
  }

  //memoire permanant 
  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.account.patchValue(this.currentUser);
    console.log(this.currentUser)
  }

  /*this.data = res[0];
  this.privacy.patchValue(this.data);*/

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
   * update current user info
   */
  
  save() {
    Swal.fire({
         title: 'Are you sure to update Your profil  !?',
         icon: 'warning',
         showCancelButton: true,
         confirmButtonText: 'Yes',
         cancelButtonText: 'No',
         }).then((result) => {
         if (result.value) {
          this.accountService.updateCurrentUser({
            idAccount:this.account.value.id,
              username: this.account.value.username,
              email: this.account.value.email,
              fiscaleCode: this.account.value.fiscaleCode,
          }).subscribe(r => {
            let user= JSON.parse(window.sessionStorage.getItem("auth-user")||'');
            let newSessionObject={
             token:user.token,
             roles:user.roles,
             groupeId:user.groupeId,
             username: this.account.value.username,
             email: this.account.value.email,
             fiscaleCode: this.account.value.fiscaleCode,

            }
            window.sessionStorage.setItem("auth-user",JSON.stringify(newSessionObject));
            console.log("updated", r);
      
          // snackBar success 
          this._Snackbar.open("Updated Successfully", "OK" + '⚡', {
            duration: 5000,
            horizontalPosition: "right",
            verticalPosition: "top",
            panelClass: ["mat-toolbar", "mat-succes"],
          });
          Swal.fire('Updated!', ' Updated successfully.', 'success');
          if (result.dismiss === Swal.DismissReason.cancel) {
           
          }
          this.refresh();
        },

          error => {
            // snackBar error
            this._Snackbar.open("Error occurend !!" + error?.message, "", {
              duration: 3000,
              horizontalPosition: "right",
              verticalPosition: "top",
              panelClass: ["mat-toolbar", "mat-warn"],
            });
          });
      }
      this.refresh();
    });
  }


    //refrech 
    refresh(): void {
      this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
        console.log(decodeURI(this._location.path()));
        this.router.navigate([decodeURI(this._location.path())]);
      });
    }

}
