import { Groupe } from "./groupe";

export interface Entrepris{
    id?: number;
    companyname?: string;
    fiscalCode?: string;
    adresse?: string;
    phone: string;
    fax: string;
    email: string;
    contact : string;
    note: string;
    groupe ?: Groupe[];
   
  

}