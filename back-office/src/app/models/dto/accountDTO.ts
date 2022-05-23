/**
 * @author Tarchoun Abir
 */

export class AccountDTO{
    id!:number ;
	username! : string;
    email!: string;
    password!: string;
    groupeId!: number;
    accountStatus!: string;
    matchingPassword!: string;
    fiscaleCode !: string;
    createdDate !:   Date;
    lastModifiedDate!: Date; 
    role ?: string ;
    entrepriseId?:number;
}