export interface productsDTO{
    id?:number ;
	title? : string;
    caracteristique? :string;
    requirements:string;
    createdDate :Date,
    lastModifiedDate:Date,
    description:string;
    subtitle ? : string;
    entrepriseId?:number;
    categorieId? :number
    image ? : string;
}