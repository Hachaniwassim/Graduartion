import { Groupe } from "./groupe";

export interface Entrepris{
    id?: number;
    companyname?: string;
    codefiscale?: string;
    adresse?: string;
    phone: string;
    fax: string;
    email: string;
    note: string;
    createdDate :Date,
    lastModifiedDate:Date,   
    //groupe ?: Groupe[];
   
  

}