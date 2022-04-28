import { CompanyBusiness } from "./conpanybusiness";


export interface Groupe{
id?: number;
name: string;
description: string;
active: boolean;
createdAt?: Date;
updateAt?: Date;
confirmed?: boolean;
companyBusiness?:CompanyBusiness[];
}