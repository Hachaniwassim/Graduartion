import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { CookieDTO } from '../models/dto/cookieDTO';
import { CookiesService } from '../_services/cookies.service';

@Component({
  selector: 'app-cookies',
  templateUrl: './cookies.component.html',
  styleUrls: ['./cookies.component.css']
})
export class CookiesComponent implements OnInit {

  data !: CookieDTO;
  cookie !: FormGroup;
  config = {
    language: 'en'
  };

  constructor(private fb: FormBuilder,
              private cookiesService: CookiesService,
  ) {
    this.cookie = this.fb.group({
      id: new FormControl(),
      title: new FormControl(),
      htmlContent: new FormControl()
   });
  }

  ngOnInit() {

   this.getCookies();
  }

   getCookies(){

    this.cookiesService.get().subscribe(r=>{
      this.cookiesService.get().subscribe ( (res: CookieDTO[]) => {
        this.data=res[0];
        this.cookie.patchValue(this.data);
  
     });
   });
  }

  save(){
    this.cookiesService.update(this.cookie.getRawValue()).subscribe(r=>{
      console.log("updated");
    });
  }

}

