import { CompanyBusinessDTO } from "./companyBusinessDTO";


export  interface GroupeDTO{
id: number;
name: string;
description: string;
active: boolean;
createdDate : Date;
lastModifiedDate: Date; 
confirmed?: boolean;
deleted?: boolean;
//companyBusiness?:CompanyBusinessDTO[];
}