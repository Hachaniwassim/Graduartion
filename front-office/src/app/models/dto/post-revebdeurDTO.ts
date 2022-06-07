import { environment } from "src/environments/environment";

export class PostRevendeurDTO {
     id !: number;
	companyname! : string
     mobile!: string ;
	cap!: string ;
     email!: string
     adresse !: string;
     nationality!: string;
     referent!: string;
     contactStatus!: string;
     productId!:string;
     message !: string;
     entrepriseId = environment.enterpriseId
     lastModifiedDate!: string;
     createdDate!: string;
}