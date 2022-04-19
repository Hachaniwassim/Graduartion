
import { Component, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NotificationService } from '../shared/notification.service';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {
    username:"",
    password:""
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  username? : string;
  password? : string;

  constructor(private authService: AuthService, public router : Router, private tokenStorage: TokenStorageService,private notificationService: NotificationService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
      this.username=this.tokenStorage.getUser().username;
     // this.notificationService.success(' : : Your logged successfully');
      this.router.navigate(['/home']);
    
    }
    
  }

  onSubmit(): void {
    const { username, password } = this.form;

    this.authService.login(username, password).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.username=this.tokenStorage.getUser().username;

        this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;//includes('INVALID_CREDENTIALS')
        this.isLoginFailed = true;
      }
    );
   
  }

  reloadPage(): void {
    window.location.reload();
  }
}


