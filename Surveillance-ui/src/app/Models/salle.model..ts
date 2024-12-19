import { departement } from "./departement.model";
import { etablissement } from "./etablissement.model";

export interface Salle {
  id: number;
  nom: string;
  type: string;
  capacite: number;
  departement: departement;
}
