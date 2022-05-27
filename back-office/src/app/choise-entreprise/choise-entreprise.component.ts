import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Accountservice } from '../_services/account.service';
import { TokenStorageService } from '../_services/token-storage.service';


@Component({
  selector: 'app-choise-entreprise',
  templateUrl: './choise-entreprise.component.html',
  styleUrls: ['./choise-entreprise.component.css'],
})

/*********
 * 
 * @author Tarchoun Abir
 * 
 */


export class ChoiseEntrepriseComponent implements OnInit {
  entreprisesByGroup: any;
  groupId: any;
  entrepriseChoised: any;
  isLoggedIn = false;
  username: any;
  constructor(
    private route: ActivatedRoute,
    public globalRouter: Router,
    private accountService: Accountservice,
    private tokenStorageService: TokenStorageService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((parms) => {
      console.log('testRouter ===================>', parms['groupId']);
      this.groupId = parms['groupId'];
      this.getEntreprisesById();
    });

  if( this.isLoggedIn = true) {
    
    const user = this.tokenStorageService.getUser();
    this.username = user.username;
  }
  }

  getEntreprisesById(): any {
    if (true) {
      this.accountService.getEntreprisesByGroup(this.groupId).subscribe(
        (entreprises: any) => {
          this.entreprisesByGroup = entreprises;
          console.log(
            'entreprises ====================>',
            this.entreprisesByGroup
          );
        },
        (err) => {
          console.log('error of the get entreprises by group rote=>', err);
        }
      );
    }
  }
  onChangeEntreprise(val: any) {
    console.log('the entreprise choised ==========>', val.target.value);
    this.entrepriseChoised = val.target.value;
  }
  selectEntreprise() {
    localStorage.setItem('idEntreprise', this.entrepriseChoised);
    this.globalRouter.navigate(['/dashboard']);
    //this.refresh();
  }
}
