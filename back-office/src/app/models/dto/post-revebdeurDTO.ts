
export class PostRevendeurDTO {
     id !: number;
	companyname! : string
     mobile!: string ;
	cap!: string ;
     email!: string
     adresse !: string;
     nationality!: string;
     referent!: string;
     revendeursStatus!: string;
     productId!:string;
     message !: string;
     entrepriseId = localStorage.getItem('idEntreprise')
     lastModifiedDate!: string;
     createdDate!: string;
}