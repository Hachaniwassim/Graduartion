import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { MatDialogRef } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountDTO } from 'src/app/models/dto/accountDTO';
import { NotificationService } from 'src/app/shared/notification.service';
import { Accountservice } from 'src/app/_services/account.service';
import Swal from 'sweetalert2';
import { Subscription } from 'rxjs';
import { RoleService } from 'src/app/_services/role.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Role } from 'src/app/models/entity/role';

@Component({
  selector: 'app-account-edit',
  templateUrl: './account-edit.component.html',
  styleUrls: ['./account-edit.component.css']
})


/**
 * 
 * @author Tarchoun Abir
 * 
 * 
 */
export class AccountEditComponent implements OnInit {

  /*********************************
   *  Variable initialisation 
   */
  account: AccountDTO[] = [];
  acc !: AccountDTO;
  public roles: Role[] = [];
  private subscriptions: Subscription[] = [];
  datasource = new MatTableDataSource(this.account);


  constructor(public Accountservice: Accountservice, private roleService: RoleService, private notificationService: NotificationService, private route: ActivatedRoute, public router: Router, public _location: Location, public dialogRef: MatDialogRef<AccountEditComponent>) { }


  ngOnInit(): void {
    this.getAll();
    this.getRoles();
  }



  /*********************************
   * get All Data
   */
  getAll() {
    this.Accountservice.all().subscribe((response: any) => {
      this.datasource.data = response;
    });

  }

  /*********************************
   * Clear Data
   */

  onClear() {
    this.Accountservice.form.reset();
    this.Accountservice.initializeFormGroup();
  }


  /***********************************************
   * Submit with Contexts :: Update || Create ::
   */
  onSubmit() {

    if (this.Accountservice.form.valid) {
      if (!this.Accountservice.form.get('id')?.value)

      //create
        this.Accountservice.create(this.Accountservice.form.value).subscribe(() => {
          this.notificationService.success(':: Submitted successfully');
        })
      else
      //update
        this.Accountservice.update(this.Accountservice.form.value).subscribe(() => {

          this.notificationService.success(':: Submitted successfully');
        })

      this.onClose();
    }

    this.refresh();

  }




  /*********************************
   * Dialog close function
   */

  onClose() {
    this.Accountservice.form.reset();
    this.Accountservice.initializeFormGroup();
    this.dialogRef.close();
  }


  /*********************************
   * alert of  confirmation
   */
  alertConfirmation() {
    Swal.fire({
      title: 'Are you sure?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, go ahead.',
      cancelButtonText: 'No, let me think',
    }).then((result) => {
      if (result.value) {
        Swal.fire('Updated!', 'Company updated successfully.', 'success');
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelled');
      }
    });
  }

  /*********************************
   * refresh
   */
  refresh(): void {
    this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
      console.log(decodeURI(this._location.path()));
      this.router.navigate([decodeURI(this._location.path())]);
    });
  }


  /*********************************
   * get Roles
   */
  public getRoles(): void {
    this.subscriptions.push(
      this.roleService.getRoles().subscribe(
        (response: Role[]) => {
          this.roles = response;
        },
        (errorResponse: HttpErrorResponse) => {
          console.log(errorResponse.message);
        })
    );
  }

  /* async OnupdateUserRole(acc: AccountDTO){
     await this.Accountservice.updateAccount(acc).then(async (cc: any) => {
       await this.router.navigate(['/account']);
       console.log(cc);
       },
       (error: HttpErrorResponse) => {
       alert(error.message);
     });

   }*/

}
