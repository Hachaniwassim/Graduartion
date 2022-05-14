import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Accountservice } from '../_services/account.service';
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

  constructor(private token: TokenStorageService, private accountService : Accountservice,private fb: FormBuilder) { 
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

  save(){
    this.accountService.update(this.account.getRawValue()).subscribe(r=>{
      console.log("updated",r);
      
    });
  }
}
