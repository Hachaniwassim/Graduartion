import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {AuthService} from "../_services/auth.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {HttpErrorResponse} from "@angular/common/http";
import { CustomHttpRespone } from '../models/entity/custom-http-response';

@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.css']
})
export class ResetpasswordComponent implements OnInit,OnDestroy {
  public refreshing: boolean | undefined;
  private subscriptions: Subscription[] = [];
  constructor(private authService: AuthService,private router: Router) { }

  ngOnInit(): void {
  }

  public onResetPassword(emailForm: NgForm): void {
    this.refreshing = true;
    const emailAddress = emailForm.value['reset-password-email'];
    this.subscriptions.push(
      this.authService.forgetPassword(emailAddress).subscribe(
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


  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }
}
