import { CompanyBusiness } from "./conpanybusiness";


export interface Groupe{
id?: number;
name: string;
description: string;
active: boolean;
createdDate :Date,
lastModifiedDate:Date,
confirmed?: boolean;
deleted?:boolean;
maxOperateur?: string;
}