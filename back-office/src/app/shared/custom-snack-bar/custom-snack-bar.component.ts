import {Component, OnInit, Inject} from '@angular/core';
import {MatSnackBar, MatSnackBarRef, MAT_SNACK_BAR_DATA} from '@angular/material/snack-bar';


@Component({
  selector: 'app-custom-snack-bar',
  template: `
      <i class="ft-{{data.actionIcon}}" style="font-size: 18px"></i> {{data.type}}:
      <span style="font-weight: bold">{{data.text}}</span> {{data.actionMsg}}
      <!--<button mat-button (click)="dismiss()">Ok</button>-->
  `,
  styles: [
    ':host {color: white}'
  ]
})
export class CustomSnackBarComponent implements OnInit {

  ngOnInit() {
  }

  constructor(public snackBarRef: MatSnackBarRef<CustomSnackBarComponent>,
              @Inject(MAT_SNACK_BAR_DATA) public data: SnackBarData) {
  }

  dismiss() {
    this.snackBarRef.dismiss();
  }
}


export class SnackBarData {
  type: any;
  text: any;
  actionIcon!: string;
  actionMsg!: string;
  verticalPosition!: string;
}

