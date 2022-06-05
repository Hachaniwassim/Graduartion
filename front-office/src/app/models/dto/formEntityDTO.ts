import { environment } from "src/environments/environment";

export class FormEntityDTO {
     id !: number;
	name !: string;
	companyname! : string
     mobile!: string ;
	 fax!: string ;
     email!: string
     adresse !: string;
     nationality!: string;
     refrente!: string;
     contactstatus!: string;
     entrepriseId = environment.enterpriseId
     lastModifiedDate!: string;
     createdDate!: string;
}