import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Accountservice } from 'src/app/_services/account.service';
import { AuthService } from 'src/app/_services/auth.service';

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.css']
})
/****
 * 
 * @author Tarchoun Abir
 * 
 */
export class AddAccountComponent implements OnInit {
  groups = null;
  idGroup:any

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
    constructor(private authService: AuthService, private _snackBar :MatSnackBar,
    private dialog: MatDialog,
    public dialogRef: MatDialogRef<AddAccountComponent> , private Accountservice : Accountservice, @Inject(MAT_DIALOG_DATA)
    public data: {
      id: Number;
      groupId: Number;
    }) { }

    ngOnInit(): void {
    this.getGroups();
    }
    getGroups() {
      this.Accountservice.getallGroupe().subscribe((response: any) => {
        this.groups = response;
      });
      console.log('groups = ' + this.groups);
    }


    onChangeGroup(e:any){
      this.idGroup=e.target.value;
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
      /*if (
        this.idGroup!=null&&
        this.data.groupId != this.idGroup
      ) {
              
        this.Accountservice.assignGroup(
          this.idGroup,
          this.data.id
        ).subscribe((res) => {
          console.log(res);
            (error: any) => {
              console.log('errr =>', error);
            };
        });
      }*/
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
    this.onClose();

}

// show password 
toggleFieldTextType() {
  this.fieldTextType = !this.fieldTextType;
}

// show password 
toggleFieldTextType2() {
  this.fieldTextType2 = !this.fieldTextType2;
}

onClose() {
  this.dialogRef.close();
}

onrelod(){
  window.location.reload();
}
}



