import { Component, OnInit , ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { DialogService } from '../shared/dialog.service';
import { NotificationService } from '../shared/notification.service';
import { Privacyservice } from '../_services/privacy.service';
import { privacyDTO } from '../models/dto/privacyDTO';
import { Observable } from 'rxjs';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-privacypolicy',
  templateUrl: './privacypolicy.component.html',
  styleUrls: ['./privacypolicy.component.css']
})
export class PrivacypolicyComponent implements OnInit {
  
  data !:  privacyDTO;
  privacy !: FormGroup;
  config = {
    language: 'en'
  };

  constructor(private fb: FormBuilder,private privacyService : Privacyservice,private dialogService: DialogService,
    private notificationService: NotificationService,private route: ActivatedRoute, public router: Router, public _location: Location) {

    this.privacy= this.fb.group({
      id: new FormControl(),
      title: new FormControl(),
      htmlContent: new FormControl()
   });
  }

  ngOnInit() {

   this.getprivacys();
  }

   getprivacys(){
      this.privacyService.getallPrivacy().subscribe(r=>{
      this.data=r;
      console.log(this.data);
      this.privacy.get('name')?.value;
      this.privacy.get('htmlContent')?.value;
      
      this.privacy.patchValue(r);

   });
  }

  save(){
    this.privacyService.updatePrivacy(this.privacy.getRawValue()).subscribe(r=>{
      console.log("updated");
    });
  }



//refrech 
refresh(): void {
  this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
    console.log(decodeURI(this._location.path()));
    this.router.navigate([decodeURI(this._location.path())]);
  });
}





}


