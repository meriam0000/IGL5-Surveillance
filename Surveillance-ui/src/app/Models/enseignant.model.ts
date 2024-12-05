import {departement} from "./departement.model";

export interface Enseignant {
  id: number;
  nom: string;
  prenom: string;
  cin: string;
  email: string;
  numeroTelephone: string;
  grade: string;
  nbHeureSurveillanceMaximale: number;
  departement: departement;
}
