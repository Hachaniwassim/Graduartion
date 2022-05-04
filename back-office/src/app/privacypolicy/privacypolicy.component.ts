import { Component, OnInit} from '@angular/core';
import {Router } from '@angular/router';
import { Location } from '@angular/common';
import { Privacyservice } from '../_services/privacy.service';
import { privacyDTO } from '../models/dto/privacyDTO';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { CustomSnackBarComponent } from '../shared/custom-snack-bar/custom-snack-bar.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-privacypolicy',
  templateUrl: './privacypolicy.component.html',
  styleUrls: ['./privacypolicy.component.css']
})
export class PrivacypolicyComponent implements OnInit {

  data !:  privacyDTO;
  privacy !: FormGroup;
  config = {
    language: 'en',
    placeholder:'Enter your texte here ..'
  };


  constructor(private fb: FormBuilder,private privacyService : Privacyservice,
   public router: Router, public _location: Location, public snackBar: MatSnackBar,) {

 }

  ngOnInit() {

      this.privacy= this.fb.group({
      id: new FormControl(),
      title: new FormControl('',[ Validators.required]),
      htmlContent: new FormControl('',[ Validators.required])
   });
      this.privacyService.getallPrivacy().subscribe ( (res: privacyDTO[]) => {
      this.data=res[0];
      this.privacy.patchValue(this.data);

   });
  }

  save(){
     this.privacyService.updatePrivacy(this.privacy.value).subscribe(r=>{
      console.log(r);
     this.snackBar.open(" :: updated Successfully ", "", {
      duration: 3000,
      horizontalPosition: "right",
      verticalPosition: "top",
      panelClass: ["mat-toolbar", "mat-primary"],
     });
    },
  error => {
    this.snackBar.open("Erroor occurend !!" + error?.message, "", {
      duration: 3000,
      horizontalPosition: "right",
      verticalPosition: "top",
      panelClass: ["mat-toolbar", "mat-warn"],
    });
  });
  
     this.refresh()
  }


//refrech 
refresh(): void {
  this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
    console.log(decodeURI(this._location.path()));
    this.router.navigate([decodeURI(this._location.path())]);
  });
}





}

