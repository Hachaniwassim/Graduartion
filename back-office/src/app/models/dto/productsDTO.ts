export class productsDTO{
    id!:number ;
	title!: string;
    caracteristique! :string;
    requirements!:string;
    createdDate !:Date;
    lastModifiedDate!:Date;
    description!:string;
    name ! : string;
    entrepriseId?:number;
    categorieId? :number
    image !: string;
    slug!: string;
}