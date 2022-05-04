import { CompanyBusinessDTO } from "./companyBusinessDTO";


export  interface GroupeDTO{
id: number;
name: string;
description: string;
createdDate : Date;
lastModifiedDate: Date; 
companyId: number;
groupStatus: string;
//companyBusiness?:CompanyBusinessDTO[];
}