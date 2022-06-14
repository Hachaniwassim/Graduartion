import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  //form initialiaze
  form: any = {
    username: "",
    email: "",
    fiscaleCode: "",
    password:"",  
    matchingPassword: ""}

  fieldTextType!: boolean;
  fieldTextType2!: boolean;
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
 

  constructor(private authService: AuthService, private _snackBar :MatSnackBar) { 
    
  
   }
  ngOnInit(): void {
   
  }

  onSubmit(): void {
    const {
      username, email, password,
      matchingPassword,
      fiscaleCode } = this.form;

    this.authService.create(username, email, password, matchingPassword, fiscaleCode).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },

      err => {
        //get error from backend : inactivate account || blocked account
        if (err.error.text) {
            console.log(err.error.text)
            this._snackBar.open(err.error.text, '', {
            duration: 4000,
            horizontalPosition: "center",
            verticalPosition: "top",
            panelClass: ['mat-toolbar', 'mat-warn']

          });
          return
        }
      });

  }

  // show password 
  toggleFieldTextType() {
    this.fieldTextType = !this.fieldTextType;
  }

  // show password 
  toggleFieldTextType2() {
    this.fieldTextType2 = !this.fieldTextType2;
  }

}
