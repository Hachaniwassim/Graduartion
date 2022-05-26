import { Component, Inject, OnInit } from '@angular/core';
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
import { FormControl } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RoleDTO } from 'src/app/models/dto/roleDTO';
import{FormArray}from '@angular/forms';

@Component({
  selector: 'app-account-edit',
  templateUrl: './account-edit.component.html',
  styleUrls: ['./account-edit.component.css'],
})
export class AccountEditComponent implements OnInit {
  entrepriseSelected = null;
  roleSelected = null;

  account: AccountDTO[] = [];
  acc!: AccountDTO;
  public roleservices: RoleDTO[] = [];
  private subscriptions: Subscription[] = [];
  datasource = new MatTableDataSource(this.account);
  entreprises = null;
  selectedEntreprise = 0;
  selectedRoleID = 0;
  constructor(
    public Accountservice: Accountservice,
    private roleService: RoleService,
    private notificationService: NotificationService,
    private route: ActivatedRoute,
    public router: Router,
    public _location: Location,
    public dialogRef: MatDialogRef<AccountEditComponent>,
    @Inject(MAT_DIALOG_DATA)
    public data: {
      id: Number;
      entrepriseId: Number;
      roleId: Number;
      //besh namel catch lil role
      roles:string;
    }
  ) {}

  ngOnInit(): void {
    this.Accountservice.form.value.rolesArray = [];
    this.getAll();
    this.getRoles();
    this.getEntreprises();
    this.fetchFirstRoleID();
  }


  //fetch
  fetchFirstRoleID() {
    this.selectedRoleID = JSON.parse(this.data.roles)[0].id;
  }

//roleArray
  checkCheckBoxvalue(event: any) {
    const checkArray: FormArray = this.Accountservice.form.get(
      'rolesArray'
    ) as FormArray;
    if (event.target.checked) {
      checkArray.push(new FormControl(event.target.value));
    } else {
      let i: number = 1;
      checkArray.controls.forEach((item: any) => {
        if (item.value == event.target.value) {
          console.log('item.value====>', item.value);
          checkArray.removeAt(i);
          this.Accountservice.form.value.rolesArray = checkArray;
          return;
        }
        i++;
      });
    }
  }

  onChangeRole(val: any) {
    console.log('onChangeRole==========================>', val);
  }

  getAll() {
    this.Accountservice.all().subscribe((response: any) => {
      this.datasource.data = response;
    });
  }

  getEntreprises() {
    this.Accountservice.entreprises().subscribe((response: any) => {
      this.entreprises = response;
    });
    console.log('entreprises = ' + this.entreprises);
  }

  onClear() {
    this.Accountservice.form.reset();
    this.Accountservice.initializeFormGroup();
  }

  onSubmit() {
    console.log('====>rolesArray =>'+this.Accountservice.form.value.rolesArray);
    if (this.Accountservice.form.valid) {
      if (!this.Accountservice.form.get('id')?.value) {
        this.Accountservice.update(this.Accountservice.form.value).subscribe(
          (res) => {
            this.notificationService.success(':: Submitted successfully');
          }
        );
      } else {
        if (
          this.data.entrepriseId != this.Accountservice.form.value.entrepriseId
        ) {
          this.Accountservice.assignEntreprise(
            this.data.id,
            this.Accountservice.form.value.entrepriseId
          ).subscribe((res) => {
            this.notificationService.success(
              ':: Entreprise Assigned successfully'
            ),
              (error: any) => {
                console.log('errr =>', error);
              };
          });
        }
        /*this.Accountservice.update(this.Accountservice.form.value).subscribe(
          () => {
            this.notificationService.success(':: Submitted successfully');
          }
        );*/
        this.Accountservice.changeRoel(
          this.data.id,
          this.Accountservice.form.value.roleId
        ).subscribe((res) => {
          console.log('test ', res);

          this.notificationService.success(':: Role updated successfully'),
            (error: any) => {
              console.log('errr =>', error);
            };
        });
      }

      this.onClose();
    }

    this.refresh();
  }

  /**********
   * Get Role
   */
  public getRoles(): void {
    this.subscriptions.push(
      this.roleService.getRoles().subscribe(
        (response: RoleDTO[]) => {
          console.log('response roles : ', JSON.stringify(response));
          this.roleservices = response;
        },
        (errorResponse: HttpErrorResponse) => {
          console.log(errorResponse.message);
        }
      )
    );
  }

  //on fetch role of selected account
  fetchRolePerAccount(idAccoun: Number, allRoles: []) {
    allRoles.forEach((role) => {});
  }
  async OnupdateUserRole(acc: AccountDTO) {
    await this.Accountservice.updateAccount(acc).then(
      async (cc: any) => {
        await this.router.navigate(['/account']);
        console.log(cc);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  //refrech
  refresh(): void {
    this.router
      .navigateByUrl('/refresh', { skipLocationChange: true })
      .then(() => {
        console.log(decodeURI(this._location.path()));
        this.router.navigate([decodeURI(this._location.path())]);
      });
  }

  //close matdialog

  onClose() {
    this.Accountservice.form.reset();
    this.Accountservice.initializeFormGroup();
    this.dialogRef.close();
  }

  //alerte de confirmation
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
}
