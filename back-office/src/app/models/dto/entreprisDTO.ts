import { GroupeDTO } from "./groupeDTO";

export interface EntrepriseDTO{
        id?: number;
        companyname?: string;
        fiscalCode?: string;
        adresse?: string;
        phone: string;
        fax: string;
        email: string;
        contact : string;
        note: string;
        createdAt: string,
        updateAt: string,
        groupe ?: GroupeDTO[];
       
      

}