import { Component, OnInit } from '@angular/core';
import { AbstractControl } from '@angular/forms';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: any = {
    username :"",
    email: "",
    password: "",
    matchingPassword:"",
    fiscaleCode: "",

  };
  MatchPassword(AC: AbstractControl) {
    const password = AC.get('password')?.value; // to get value in input tag
    const matchingPassword= AC.get('matchingPassword')?.value; // to get value in input tag
    if (password !== matchingPassword ){
      return {MatchPassword: true};
    } else {
        return null
    }
  }
  

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const { username, email, password ,
      matchingPassword ,
      fiscaleCode} = this.form;

    this.authService.register(username, email, password, matchingPassword, fiscaleCode).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

}
