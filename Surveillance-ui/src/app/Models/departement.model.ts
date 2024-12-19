import {etablissement} from "./etablissement.model";


export interface departement {
    id?:number;
    nom:string;
    specialité:string;
    etablissement:etablissement;
}
