import { environment } from "src/environments/environment";

export class FormEntityDTO {
     id !: number;
	companyname! : string
     mobile!: string ;
	fax!: string ;
     email!: string
     adresse !: string;
     nationality!: string;
     referent!: string;
     contactstatus!: string;
     productId!:string;
     message !: string;
     entrepriseId = environment.enterpriseId
     lastModifiedDate!: string;
     createdDate!: string;
}