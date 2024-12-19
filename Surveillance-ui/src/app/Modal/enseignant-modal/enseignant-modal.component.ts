import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Enseignant} from "../../Models/enseignant.model";
import {etablissement} from "../../Models/etablissement.model";
import {departement} from "../../Models/departement.model";
import {EtablissementService} from "../../Services/ServiceEtablissement/etablissement.service";

@Component({
  selector: 'app-enseignant-modal',
  templateUrl: './enseignant-modal.component.html',
  styleUrl: './enseignant-modal.component.css'
})
export class EnseignantModalComponent implements OnInit {
  @Output() closeModal = new EventEmitter<void>();
  error: string | null = null;
  departements: departement[] = [];
  etablissements: etablissement[] = [];

  onClose(): void {
    this.closeModal.emit();
  }



  ngOnInit(): void {
    this.loadDepartementsByEtablissement();
    this.loadEtablissements();
  }
  constructor(private etablissementService: EtablissementService) {}

  loadDepartementsByEtablissement() {

    this.departements = [{
      id: 1,
      nom: 'Math-Department',
      specialité: 'mathématiques',
      etablissement: {
        id: 1,
        nom: 'Fst',
        localisation: 'Manar 1 Tunis',
        universite: 'Université de Tunis Manar'
      } as etablissement
    },
      {
        id: 2,
        nom: 'physics',
        specialité: 'mathématiques',
        etablissement: {
          id: 1,
          nom: 'Fst',
          localisation: 'Manar 1 Tunis',
          universite: 'Université de Tunis Manar'
        } as etablissement
      },
      {
        id: 3,
        nom: 'info-Department',
        specialité: 'mathématiques',
        etablissement: {
          id: 1,
          nom: 'Fst',
          localisation: 'Manar 1 Tunis',
          universite: 'Université de Tunis Manar'
        } as etablissement
      },
      {
        id: 4,
        nom: 'chimie-Department', // should be 'Mathematics
        specialité: 'mathématiques',
        etablissement: {
          id: 1,
          nom: 'Fst',
          localisation: 'Manar 1 Tunis',
          universite: 'Université de Tunis Manar'
        } as etablissement
      },
      {
        id: 5,
        nom: 'biologie-Department', // should be 'Mathematics
        specialité: 'mathématiques',
        etablissement: {
          id: 1,
          nom: 'Fst',
          localisation: 'Manar 1 Tunis',
          universite: 'Université de Tunis Manar'
        } as etablissement

      }

    ]

  }
  private loadEtablissements() {
      this.etablissementService.getAllEtablissements().subscribe({
        next: (data) => (this.etablissements = data),
        error: (err) => (this.error = err.message)
      });
  }


  enseignant: Enseignant = {
    nom: '',
    prenom: '',
    cin: '',
    email: '',
    numeroTelephone: '',
    grade: '',
    nbHeureSurveillanceMaximale: 0,
    departement: {
      nom: '',
      specialité: '',
      etablissement:{
        nom: '',
        localisation: '',
        universite: ''
      }
    }

  };

  onSubmit(): void {

    this.onClose();
  }

}
