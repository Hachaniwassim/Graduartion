import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm } from '@angular/forms';
import { Subscription } from 'rxjs';
import { CustomHttpRespone } from '../models/custom-http-response';
import { Accountservice } from '../_services/account.service';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  hide= true;
  panelOpenState =true;
  account !: FormGroup;
  public refreshing: boolean | undefined;
  private subscriptions: Subscription[] = [];
  constructor(private token: TokenStorageService, private authService : AuthService,private accountService : Accountservice,private fb: FormBuilder) { 
    this.account= this.fb.group({
       id: new FormControl(),
       username: new FormControl(),
       email: new FormControl(),
       password :new FormControl(),
       matchingPassword : new FormControl(),
       fiscaleCode : new FormControl(),
       accountStatus : new FormControl(),
   });
  }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.account.patchValue(this.currentUser);
    console.log(this.currentUser)
  }
  public onResetPassword(emailForm: NgForm): void {
    this.refreshing = true;
    const emailAddress = emailForm.value['reset-password-email'];
    this.subscriptions.push(
      this.authService.resetPassword(emailAddress).subscribe(
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

  save(){
    this.accountService.update(this.account.getRawValue()).subscribe(r=>{
      console.log("updated",r);
      
    });
  }
}
