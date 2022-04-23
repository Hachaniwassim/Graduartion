import { GroupeDTO } from "./groupeDTO";

export interface EntreprisDTO{
        id?: number;
        companyname?: string;
        fiscalCode?: string;
        adresse?: string;
        phone: string;
        fax: string;
        email: string;
        contact : string;
        note: string;
        groupe ?: GroupeDTO[];
       
      

}