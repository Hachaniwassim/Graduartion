import { Component, Inject, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { MatDialogRef } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountDTO } from 'src/app/models/dto/accountDTO';
import { NotificationService } from 'src/app/shared/notification.service';
import { Accountservice } from 'src/app/_services/account.service';
import { Subscription } from 'rxjs';
import { RoleService } from 'src/app/_services/role.service';
import { HttpErrorResponse } from '@angular/common/http';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RoleDTO } from 'src/app/models/dto/roleDTO';

@Component({
  selector: 'app-account-edit',
  templateUrl: './account-edit.component.html',
  styleUrls: ['./account-edit.component.css'],
})
export class AccountEditComponent implements OnInit {
  roleSelected = null;
  account: AccountDTO[] = [];
  public roleservices: RoleDTO[] = [];
  private subscriptions: Subscription[] = [];
  datasource = new MatTableDataSource(this.account);
  groups = null;
  idGroup:any
  selectedRoleID = 0;
  constructor(
    public Accountservice: Accountservice,
    private roleService: RoleService,
    private notificationService: NotificationService,
    public router: Router,
    public _location: Location,
    public dialogRef: MatDialogRef<AccountEditComponent>,
    @Inject(MAT_DIALOG_DATA)
    public data: {
      id: Number;
      groupeId: Number;
      roleId: Number;
      roles: string;
    }
  ) {}

  ngOnInit(): void {
    this.fetchFirstRoleID();
    this.getAll();
    this.getRoles();
    this.getGroups();
  }

  fetchFirstRoleID() {
    this.selectedRoleID = JSON.parse(this.data.roles)[0].id;
    console.log('fetchFirst role =>', this.selectedRoleID);
  }

  getAll() {
    this.Accountservice.all().subscribe((response: any) => {
      this.datasource.data = response;
    });
  }

  getGroups() {
    this.Accountservice.getallGroupe().subscribe((response: any) => {
      this.groups = response;
    });
    console.log('groups = ' + this.groups);
  }


  onClear() {
    this.Accountservice.form.reset();
    this.Accountservice.initializeFormGroup();
  }


  // change role 
  onChangeRole(event: any) {
    this.selectedRoleID = event.target.value;
    console.log('test on change role =>', event.target.value);
  }

  onSubmit() {
    if (this.Accountservice.form.valid) {
      console.log('this.Accountservice.form.value.groupId=>',this.idGroup)   
      console.log('this.Accountservice.form.value.email=>',this.Accountservice.form.value.email)   
       console.log(' this.data.groupId=>', this.data.groupeId)   
       console.log(' this.Accountservice.form.value=======>', this.Accountservice.form.value)
      if (!this.Accountservice.form.get('id')?.value) {
        this.Accountservice.update(this.Accountservice.form.value).subscribe(
          (res) => {
            this.notificationService.success(':: Submitted successfully');
          }
        );
      } else {
        if (
          this.idGroup!=null&&
          this.data.groupeId != this.idGroup
        ) {
                
          this.Accountservice.assignGroup(
            this.idGroup,
            this.data.id
          ).subscribe((res) => {
            console.log(res);
            this.notificationService.success(':: group Assigned successfully'),
              (error: any) => {
                console.log('errr =>', error);
              };
          });
        }

      }
      if (
        Number(this.selectedRoleID) != Number(JSON.parse(this.data.roles)[0].id)
      ) {
        this.Accountservice.changeRole(
          this.data.id,
          this.selectedRoleID
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
    
   // this.refresh();

  }
  onChangeGroup(e:any){
    this.idGroup=e.target.value;
  }

  
  onClose() {
    this.Accountservice.form.reset();
    this.Accountservice.initializeFormGroup();
    this.dialogRef.close();
  }

  /**********
   * Get Role
   */
  public getRoles(): void {
    this.subscriptions.push(
      this.roleService.getRoles().subscribe(
        (response: RoleDTO[]) => {
          this.roleservices = response;
        },
        (errorResponse: HttpErrorResponse) => {
          console.log(errorResponse.message);
        }
      )
    );
  }

 /* //on fetch role of selected account
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
  }*/

  //refrech
  refresh(): void {
    this.router
      .navigateByUrl('/refresh', { skipLocationChange: true })
      .then(() => {
        console.log(decodeURI(this._location.path()));
        this.router.navigate([decodeURI(this._location.path())]);
      });
  }


}
