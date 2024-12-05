import {Component, OnInit} from '@angular/core';
import {Enseignant} from "../../Models/enseignant.model";
import {departement} from "../../Models/departement.model";
import {etablissement} from "../../Models/etablissement.model";

@Component({
  selector: 'app-liste-enseignants',
  templateUrl: './liste-enseignants.component.html',
  styleUrl: './liste-enseignants.component.css'
})
export class ListeEnseignantsComponent implements OnInit{
  selectedRow: number | null = null;
  paginatedEnseignants: Enseignant[] = []; // Current page data
  currentPage: number = 1; // Default page number
  pageSize: number = 3; // Number of items per page
  totalPages: number = 0; // Total number of pages
  enseignants: Enseignant[] = [];
  selectedEnseignants: Set<number> = new Set(); // Track selected enseignants by ID
  selectAll: boolean = false; // Whether the "Select All" checkbox is checked
  pages: number[] = [];
  filterDropdownOpen: boolean= false;
  departements: departement[] = [];
  etablissements: etablissement[] = [];
   filters: {
    departement: string;
    grade: string;
    cin: string;
    etablissement: string;
    nom: string;
    prenom: string
  };




  ngOnInit(): void {
    this.loadEnseignants();
    this.loadDepartements();
    this.loadEtablissements();

  }
  constructor() {
    this.filters= { nom: '', prenom: '', cin: '', grade: '', departement: '', etablissement: '' } ;

  }
  loadDepartements() {
   this.departements=[
      {
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
  loadEtablissements() {
    this.etablissements=[
      {
        id: 1,
        nom: 'Fst',
        localisation: 'Manar 1 Tunis',
        universite: 'Université de Tunis Manar'
      },
      {
        id: 2,
        nom: 'Enit',
        localisation: 'Manar 1 Tunis',
        universite: 'Université de Tunis Manar'
      },
      {
        id: 3,
        nom: 'FSEG',
        localisation: 'Manar 1 Tunis',
        universite: 'Université de Tunis Manar'
      },
      {
        id: 4,
        nom: 'hightech',
        localisation: 'Manar 1 Tunis',
        universite: 'Université de Tunis Manar'
      },
      {
        id: 5,
        nom: 'Iset',
        localisation: 'Manar 1 Tunis',
        universite: 'Université de Tunis Manar'
      }
    ]


  }


  loadEnseignants() {
    this.enseignants= [
      {
        id: 1,
        nom: 'Doe',
        prenom: 'John',
        cin: 'AB123456',
        email: 'john.doe@example.com',
        numeroTelephone: '0123456789',
        grade: 'Professor',
        nbHeureSurveillanceMaximale: 10,
        departement: {
          id: 1,
          nom: 'Math-Department', // should be 'Mathematics
          specialité: 'mathématiques',
          etablissement: {
            id: 1,
            nom: 'Fst',
            localisation: 'Manar 1 Tunis',
            universite: 'Université de Tunis Manar'
          } as etablissement

        } as departement
      },
      {
        id: 2,
        nom: 'louati',
        prenom: 'John',
        cin: 'AB123456',
        email: 'louati.doe@example.com',
        numeroTelephone: '0123456789',
        grade: 'Professor',
        nbHeureSurveillanceMaximale: 10,
        departement: {
          id: 1,
          nom: 'Math-Department', // should be 'Mathematics
          specialité: 'mathématiques',
          etablissement: {
            id: 1,
            nom: 'Fst',
            localisation: 'Manar 1 Tunis',
            universite: 'Université de Tunis Manar'
          } as etablissement

        } as departement
      },
      {
        id: 3,
        nom: 'louati',
        prenom: 'John',
        cin: 'AB123456',
        email: 'louati.doe@example.com',
        numeroTelephone: '0123456789',
        grade: 'Professor',
        nbHeureSurveillanceMaximale: 10,
        departement: {
          id: 1,
          nom: 'Math-Department', // should be 'Mathematics
          specialité: 'mathématiques',
          etablissement: {
            id: 1,
            nom: 'Fst',
            localisation: 'Manar 1 Tunis',
            universite: 'Université de Tunis Manar'
          } as etablissement

        } as departement
      },
      {
        id: 4,
        nom: 'louati',
        prenom: 'John',
        cin: 'AB123456',
        email: 'louati.doe@example.com',
        numeroTelephone: '0123456789',
        grade: 'Professor',
        nbHeureSurveillanceMaximale: 10,
        departement: {
          id: 1,
          nom: 'Math-Department', // should be 'Mathematics
          specialité: 'mathématiques',
          etablissement: {
            id: 1,
            nom: 'Fst',
            localisation: 'Manar 1 Tunis',
            universite: 'Université de Tunis Manar'
          } as etablissement

        } as departement
      },
      {
        id: 5,
        nom: 'louati',
        prenom: 'John',
        cin: 'AB123456',
        email: 'louati.doe@example.com',
        numeroTelephone: '0123456789',
        grade: 'Professor',
        nbHeureSurveillanceMaximale: 10,
        departement: {
          id: 1,
          nom: 'Math-Department', // should be 'Mathematics
          specialité: 'mathématiques',
          etablissement: {
            id: 1,
            nom: 'Fst',
            localisation: 'Manar 1 Tunis',
            universite: 'Université de Tunis Manar'
          } as etablissement

        } as departement
      }
    ];
    this.totalPages = Math.ceil(this.enseignants.length / this.pageSize);
    this.updatePageData();


  }
  updatePageData() {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.paginatedEnseignants = this.enseignants.slice(startIndex, endIndex);
    this.pages = Array.from({ length: this.totalPages }, (_, i) => i + 1);
  }

  // Function to go to a specific page
  goToPage(page: number) {
    if (page > 0 && page <= this.totalPages) {
      this.currentPage = page;
      this.updatePageData();
    }
  }

  // Next and previous page functions
  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.updatePageData();
    }
  }

  prevPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updatePageData();
    }
  }
  goToFirstPage(){
    this.currentPage=1;
    this.updatePageData();
  }
  goToLastPage(){
    this.currentPage=this.totalPages;
    this.updatePageData();
  }
  get isLastPage(){
    return this.currentPage==this.totalPages;
  }
  deleteEnseignant(id: number): void {
    this.enseignants = this.enseignants.filter(enseignant => enseignant.id !== id);
    this.updatePageData();
  }

  editEnseignant(id: number): void {
    console.log('Editing enseignant with id:', id);
    // Implement edit logic
  }

  viewEnseignant(id: number): void {
    console.log('Viewing enseignant with id:', id);
  }


  // Toggle the selection of a specific enseignant
  toggleSelection(enseignantId: number) {
    if (this.selectedEnseignants.has(enseignantId)) {
      this.selectedEnseignants.delete(enseignantId); // Deselect
    } else {
      this.selectedEnseignants.add(enseignantId); // Select
    }
  }

  // Toggle the "Select All" checkbox
  toggleSelectAll() {
    if (this.selectAll) {
      this.selectedEnseignants = new Set(this.enseignants.map(enseignant => enseignant.id)); // Select all
    } else {
      this.selectedEnseignants.clear(); // Deselect all
    }
  }

  // Delete selected enseignants

  deleteSelected() {
    this.enseignants = this.enseignants.filter(enseignant => !this.selectedEnseignants.has(enseignant.id));
    this.selectedEnseignants.clear(); // Clear the selection
  }


  addEnseignant() {

  }


  onRowClick(enseignantId: number) {
    if (this.selectedRow === enseignantId) {
      this.selectedRow = null; // Deselect if the same row is clicked
    } else {
      this.selectedRow = enseignantId; // Select the clicked row
    }
  }

  toggleFilterDropdown() {
    this.filterDropdownOpen= !this.filterDropdownOpen;
  }
  applyFilters() {
    const { nom, prenom,departement,grade,cin,etablissement } = this.filters;

    // Apply filtering logic
    const filteredData = this.enseignants.filter((item) => {
      return (
        (!nom || item.nom.toLowerCase().includes(nom.toLowerCase())) &&
        (!prenom || item.prenom === prenom) &&
        (!departement || item.departement.nom === departement) &&
        (!grade || item.grade === grade) &&
        (!cin || item.cin === cin) &&
        (!etablissement || item.departement.etablissement.nom === etablissement)
      );
    });

    this.totalPages = Math.ceil(filteredData.length / this.pageSize);
    this.paginatedEnseignants = filteredData.slice(
      (this.currentPage - 1) * this.pageSize,
      this.currentPage * this.pageSize
    );

    this.filterDropdownOpen = false;
  }
}
