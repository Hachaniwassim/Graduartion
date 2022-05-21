import { Groupe } from "./groupe";

export interface Entreprise{
    id?: number;
    companyname: string;
    codefiscale: string;
    phone: string;
    fax: string;
    email: string;
    note: string;
    createdDate :Date,
    lastModifiedDate:Date,   
    //groupe ?: Groupe[];
   
  

}