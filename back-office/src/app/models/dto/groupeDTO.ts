import { CompanyBusinessDTO } from "./companyBusinessDTO";


export  interface GroupeDTO{
id: number;
name: string;
description: string;
active: boolean;
createdAt?: Date;
updateAt?: Date;
confirmed?: boolean;
deleted?: boolean;
//companyBusiness?:CompanyBusinessDTO[];
}