import { Groupe } from "./groupe";

export class Entreprise{
     
    id!: number;
    companyname!: string;
    codefiscale!: string;
    phone!: string;
    fax!: string;
    email!: string;
    note!: string;
    groupeId!: string;
    companyId!: string;
    createdDate !: Date;
    lastModifiedDate!: Date; 
    adresse !: string;
    city!:string ;
    street !:string ;
    referente!: string;
    Valnumber!: string;
   
  

}