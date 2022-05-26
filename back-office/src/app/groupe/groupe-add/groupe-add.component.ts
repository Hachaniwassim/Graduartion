import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Location } from '@angular/common';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { GroupeDTO } from 'src/app/models/dto/groupeDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { GroupeService } from 'src/app/_services/groupe.service';
import { CompanybusinessService } from 'src/app/_services/companybusiness.service';
import { CompanyBusinessDTO } from 'src/app/models/dto/companyBusinessDTO';

@Component({
  selector: 'app-groupe-add',
  templateUrl: './groupe-add.component.html',
  styleUrls: ['./groupe-add.component.css']
})


/**
 * 
 * @author Tarchoun Abir
 *
 **/

export class GroupeAddComponent implements OnInit {

  @ViewChild('groupeForm', { static: false })
  groupeForm !: FormGroup;
  groupeData !: GroupeDTO;
  groupe: GroupeDTO[] = [];
  searchKey!: string;
  showspinner = false;
  datasource = new MatTableDataSource(this.groupe)
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  companyServices: CompanyBusinessDTO[] = [];

  constructor(private dialog: MatDialog, private dialogService: DialogService, public groupeService: GroupeService, public dialogRef: MatDialogRef<GroupeAddComponent>,
    private notificationService: NotificationService,
    private companyService: CompanybusinessService, private router: Router, public _location: Location) {
    this.groupeData = {} as GroupeDTO;
  }

  // sorting + pagination data 
  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }

  ngOnInit(): void {
    this.getAllGroupe();
    /* this.companyService.getAllCompanyBussiness().subscribe(res => {
       console.log(res)
       this.companyServices = res;
     })*/

  }

  getAllGroupe() {
    this.groupeService.getallGroupe().subscribe((response: any) => {
      this.datasource.data = response;
    })
  }


  //clear data
  onClear() {
    this.groupeService.form.reset();
    this.groupeService.initializeFormGroup();
  }

  // submit data with context EDITE : CREATE
  onSubmit() {
    if (this.groupeService.form.valid) {

      if (!this.groupeService.form.get('id')?.value) {
        console.log(this.groupeService.form.value);
        this.groupeService.createGroupe(this.groupeService.form.value).subscribe((res) => {
          console.log(res);
          this.notificationService.success('  ::  ' + ' ' + ' add successfully ' + '⚡');
          this.groupe.push(res);
          this.refresh();

        });
      }

      else (
        this.groupeService.updateGroupe(this.groupeService.form.value).subscribe((res) => {
          this.groupe.push(res);
          this.notificationService.success('  ::  ' + ' ' + ' updated successfully ' + '⚡');
          this.refresh();

        }));
    }
    this.onClose();
    this.refresh();
  }


  //refrech 

  refresh(): void {
    this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
      console.log(decodeURI(this._location.path()));
      this.router.navigate([decodeURI(this._location.path())]);
    });
  }


  // dialogue close 
  onClose() {
    this.groupeService.form.reset();
    this.groupeService.initializeFormGroup();
    this.dialogRef.close();
  }


}

