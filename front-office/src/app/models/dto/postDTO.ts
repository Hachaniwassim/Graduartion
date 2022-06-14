import { environment } from "src/environments/environment";

export class postDTO{
    id!:number ;
	title! : string;
    htmlContent!:string;
    createdDate !:   Date;
    lastModifiedDate!: Date; 
    entrepriseId=environment.enterpriseId;
}