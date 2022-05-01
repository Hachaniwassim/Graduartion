import { GroupeDTO } from "./groupeDTO";

export interface EntrepriseDTO{
        id?: number;
        companyname?: string;
        codefiscale?: string;
        adresse?: string;
        phone: string;
        fax: string;
        email: string;
        note: string;
        createdDate : Date;
        lastModifiedDate: Date; 
        //groupe ?: GroupeDTO[];
       
      

}