export class pagesDTO{
    id?:number ;
	published !: boolean;
    pagetype !: string;
    title !: string;
    createdDate !:   Date;
    lastModifiedDate !: Date; 
    description? : string;
}