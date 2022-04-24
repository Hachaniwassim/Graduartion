
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NotificationsService } from 'angular2-notifications';
import Swal from 'sweetalert2';
import { NotificationService } from '../shared/notification.service';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';
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
  siteSecret : string="6Lc5l5AfAAAAAHOzhA9CEDiwe3n-W6GKdbQadMeq";


  constructor( private notfication : NotificationsService,private authService: AuthService, public router: Router, private tokenStorage: TokenStorageService, private notificationService: NotificationService) { }

  ngOnInit(): void {


    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
      this.username = this.tokenStorage.getUser().username;
      // this.notificationService.success(' : : Your logged successfully');
      this.router.navigate(['/dashboard']);
      this.successNotification();
      

    }


  }
  toggleFieldTextType() {
    this.fieldTextType = !this.fieldTextType;
  }

  onSubmit(): void {
    const { username, password } = this.form;
   // this.siteKey = "6Lff7WIUAAAAAG2UuSYktpVi2Mz7tB6cgXnO1Tez";

    //constant response forn test robots
    const response = grecaptcha.getResponse();
    debugger;

    //test validation
    if (response.length === 0) {
      this.captchaError = true;
      return;
    }

    this.authService.login(username, password).subscribe(
      data => {

        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.username = this.tokenStorage.getUser().username;
        //recuperation response 
        this.recaptchaResponse = response;
        this.reloadPage();
      

      },
      err => {
        this.errorMessage = err.error.message;//includes('INVALID_CREDENTIALS')
        this.isLoginFailed = true;
      }

    );
    grecaptcha.reset();

  }

  reloadPage() {
    window.location.reload();
  }

  successNotification() {
    Swal.fire('welcome', ' you have been logged successfully ', 'success');


  }
  onSucces(_message: undefined){
    this.notfication.success('Succes',_message,{
    position :['bottom','right'],
    timeout:3000,
    animations:'fade',
    showProgressBare:true

});

}
onError(){this.notfication.error('Error',onmessage,{
  position :['bottom','right'],
  timeout:4000,
  animations:'fade',
  showProgressBare:true

});

}
}
