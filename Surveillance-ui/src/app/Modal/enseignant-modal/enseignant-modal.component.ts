import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Enseignant} from "../../Models/enseignant.model";
import {etablissement} from "../../Models/etablissement.model";
import {departement} from "../../Models/departement.model";
import {EtablissementService} from "../../Services/ServiceEtablissement/etablissement.service";
import {DepartementService} from "../../Services/serviceDepartement/departement.service";
import {EnseignantService} from "../../Services/ServiceEnseignant/enseignant.service";

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
  enseignant: Enseignant = { // Initialize the enseignant object here
    nom: '',
    prenom: '',
    cin: '',
    email: '',
    numeroTelephone: '',
    grade: '',
    nbHeureSurveillanceMaximale: 0,
    departement: { // Default empty departement
      id: 0,
      nom: '',
      specialité: '',
      etablissement: { id: 0, nom: '', localisation: '', universite: '' }
    }
  };

  onClose(): void {
    this.closeModal.emit();
  }


  ngOnInit(): void {
    this.loadEtablissements();

  }

  constructor(private etablissementService: EtablissementService, private departementService: DepartementService, private enseignantService: EnseignantService) {

  }



  private loadEtablissements() {
    this.etablissementService.getAllEtablissements().subscribe({
      next: (data) => (this.etablissements = data),
      error: (err) => (this.error = err.message)
    });
  }


    etablissementId: any;

  onSubmit(): void {
    console.log(this.departements);

    // Ensure 'departement' is updated with full object
    const selectedDepartement = this.departements.find(dept => dept.id === +this.enseignant.departement.id);
    console.log('Departement IDs:', this.departements.map(dept => dept.id));
    console.log('Selected Departement ID:', this.enseignant.departement.id);
    console.log('Type of Departement IDs:', this.departements.map(dept => typeof dept.id));
    console.log('Type of Selected Departement ID:', typeof this.enseignant.departement.id);
    if (selectedDepartement) {
      this.enseignant.departement = selectedDepartement; // Assign the full departement object
    } else {
      // Handle case where department is not found, if necessary
      console.warn('Departement not found:', this.enseignant.departement.id);
    }

    // Now submit the data
    this.enseignantService.addEnseignant(this.enseignant).subscribe(
      (response) => {
        console.log('Enseignant added successfully:', response);
        this.resetForm();
      },
      (error) => {
        console.error('Error adding enseignant:', error);
      }
    );

    this.onClose();
  }


  onEtablissementChange() {
    const etablissementId = this.etablissementId;
    if (etablissementId) {
      this.departementService.findByEtablissement(etablissementId).subscribe({
        next: (data) => {this.departements = data; console.log('Departements:', this.departements); },
        error: (err) => (this.error = err.message)
      });


    }
  }


  private resetForm() {
    this.enseignant = {
      nom: '',
      prenom: '',
      cin: '',
      email: '',
      numeroTelephone: '',
      grade: '',
      nbHeureSurveillanceMaximale: 0,
      departement: { id: 0, nom: '', specialité: '', etablissement: { id: 0, nom: '', localisation: '', universite: '' } }
    };
  }
}
