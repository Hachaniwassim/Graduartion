import { ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EntrepriseDTO } from 'src/app/models/dto/entreprisDTO';

@Component({
  selector: 'app-entreprise-view',
  templateUrl: './entreprise-view.component.html',
  styleUrls: ['./entreprise-view.component.css']
})
/**
 * 
 * @author Tarchoun Abir
 *
 **/

export class EntrepriseViewComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public entreprise :EntrepriseDTO, public dialog: MatDialog,private ref: ChangeDetectorRef
  ) { }

  ngOnInit(): void {
  }

  

}
