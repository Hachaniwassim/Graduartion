import { ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { GroupeDTO } from 'src/app/models/dto/groupeDTO';

@Component({
  selector: 'app-groupe-view',
  templateUrl: './groupe-view.component.html',
  styleUrls: ['./groupe-view.component.css']
})

/**
 * 
 * @author Tarchoun Abir
 *
 **/

export class GroupeViewComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public groupe:GroupeDTO,public dialog: MatDialog,private ref: ChangeDetectorRef
  ) { }

  ngOnInit(): void {
  }

}
