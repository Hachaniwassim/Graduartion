
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NotificationsService } from 'angular2-notifications';
import Swal from 'sweetalert2';
import { Location } from '@angular/common';
import { NotificationService } from '../shared/notification.service';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Accountservice } from '../_services/account.service';
import { GroupeDTO } from '../models/dto/groupeDTO';
import { GroupeService } from '../_services/groupe.service';
//variale for test validation robots
declare var grecaptcha: any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  protected f !: FormGroup;
  form: any = {
    username: "",
    password: ""
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  username?: string;
  captchaError: boolean = false;
  password?: string;
  recaptchaResponse = "";
  fieldTextType!: boolean;
  accountStatus !: string;
  siteSecret: string = "6Lc5l5AfAAAAAHOzhA9CEDiwe3n-W6GKdbQadMeq";
  showUserBoard = true;
  groupeServices: GroupeDTO[] = [];
  schooseGroup = false;
  groupeFormControl = new FormControl(null, Validators.required);
  data: any;

  constructor(private notfication: NotificationsService, private groupeService:GroupeService,private tokenStorageService: TokenStorageService, private _snackBar: MatSnackBar, private authService: AuthService, private accountService: Accountservice, private matSnackBar: MatSnackBar, public router: Router, public _location: Location, private tokenStorage: TokenStorageService, private notificationService: NotificationService) { }

  ngOnInit(): void {

    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.groupeService.getallGroupe().subscribe(res => {
        console.log(res)
        this.groupeServices = res;
      })
      
    }
  }

  // show password 
  toggleFieldTextType() {
    this.fieldTextType = !this.fieldTextType;
  }

  //post login 
  onSubmit(): void {
    const { username, password } = this.form;

    //constant response for test robots
    const response = grecaptcha.getResponse();
    //debugger;

    //test validation recaptcha 
    if (response.length === 0) {
      this.captchaError = true;
      return;
    }


    this.authService.login(username, password).
      subscribe((data: any) => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        //recuperation response 
        this.recaptchaResponse = response;
      
        //this.schooseGroup= true;
        if (data.roles === 'ROLE_ADMIN' || data.roles === 'ROLE_MODERATEUR') {

          this.router.navigate(['/dashboard']);
          return;
     }
        else if (data.roles === 'ROLE_USER') {
          this.router.navigate(['/']);
          return;
        }
        this.reloadPage();
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
          //  login failed if username or password not valid 
          else if (this.isLoginFailed = true) {
            this._snackBar.open('INVALID CREDENTIALS', '', {
              duration: 4000,
              horizontalPosition: "center",
              verticalPosition: "top",
              panelClass: ['mat-toolbar', 'mat-warn']
            })
          }
        }
      )
  }


  logout(): void {
    this.tokenStorage.signOut();
    this.router.navigate(['/']);
  }

  //reload pages 
  reloadPage() {
    window.location.reload();
  }


  //succes notification
  successNotification() {
    Swal.fire('welcome', ' you have been logged successfully ', 'success');
  }

  //refrech 
  refresh(): void {
    this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
      console.log(decodeURI(this._location.path()));
      this.router.navigate([decodeURI(this._location.path())]);
    });
  }

}
