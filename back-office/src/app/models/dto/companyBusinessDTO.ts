import { GroupeDTO } from "./groupeDTO";

export interface CompanyBusinessDTO{
    id?:number ;
	description? : string;
    domainename? : string;
    createdDate :   Date;
    lastModifiedDate: Date; 
}