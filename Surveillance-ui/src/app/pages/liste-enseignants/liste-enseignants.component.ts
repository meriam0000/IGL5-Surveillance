import {Component, OnInit} from '@angular/core';
import {Enseignant} from "../../Models/enseignant.model";
import {departement} from "../../Models/departement.model";
import {etablissement} from "../../Models/etablissement.model";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-liste-enseignants',
  templateUrl: './liste-enseignants.component.html',
  styleUrl: './liste-enseignants.component.css'
})
export class ListeEnseignantsComponent implements OnInit {

  enseignants: Enseignant[] = [];

  departements: departement[] = [];
  etablissements: etablissement[] = [];
  showModal = false;
  filteredEnseignants: Enseignant[] = [];
  currentPage = 1;
  pageSize = 10;
  pages: number[] = [];
  searchTerm = '';
  sortColumn = '';
  sortDirection = 'asc';




  ngOnInit(): void {
    this.loadEnseignants();
    this.loadDepartements();
    this.loadEtablissements();
    this.filterEnseignants();
    this.updatePagination();

  }

  constructor(private dialog: MatDialog) {

  }


  loadDepartements() {

    this.departements = [
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
    this.etablissements = [
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
    this.enseignants = [
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
          }

        }
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
          }

        }
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
          }

        }
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
          }

        }
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
          }

        }

      }
    ];


  }


  filterEnseignants() {
    this.filteredEnseignants= this.enseignants.filter(user =>
      Object.entries(user).some(([key, value]) => {
        if (key === 'role' && value && typeof value === 'object') {
          return Object.values(value).some(nestedValue =>
            typeof nestedValue === 'string' &&
            nestedValue.toLowerCase().includes(this.searchTerm.toLowerCase())
          );
        }
        return (
          typeof value === 'string' &&
          value.toLowerCase().includes(this.searchTerm.toLowerCase())
        );
      })
    );
    this.sortUsers();
    this.updatePagination();
  }


  updatePagination() {
    const pageCount = Math.ceil(this.filteredEnseignants.length / this.pageSize);
    this.pages = Array.from({ length: pageCount }, (_, i) => i + 1);
  }

  get paginatedUsers() {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    return this.filteredEnseignants.slice(startIndex, startIndex + this.pageSize);
  }

  sortUsers() {
    if (this.sortColumn) {
      this.filteredEnseignants.sort((a, b) => {
        const aValue = a[this.sortColumn as keyof Enseignant];
        const bValue = b[this.sortColumn as keyof Enseignant];

        // Check if either aValue or bValue is undefined
        if (aValue == null || bValue == null) {
          return 0;
        }
        // Perform the comparison when values are not null/undefined
        if (aValue < bValue) return this.sortDirection === 'asc' ? -1 : 1;
        if (aValue > bValue) return this.sortDirection === 'asc' ? 1 : -1;

        return 0;
      });
    }
  }


  onSort(column: string) {

    if (this.sortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortColumn = column;
      this.sortDirection = 'asc';
    }
    this.sortUsers();

  }

  onSearch(event: Event) {
    this.searchTerm = (event.target as HTMLInputElement).value;
    this.currentPage = 1;
    this.filterEnseignants();
  }

  onPageChange(page: number) {
    this.currentPage = page;
  }



  closeModal(): void {
    this.showModal = false;  // Close the modal
  }


  viewEnseignant(user: Enseignant) {
    console.log('View user:', user);
  }

  editEnseignant(user: Enseignant) {
    console.log('Edit user:', user);
  }

  deleteEnseignant(user: Enseignant) {
    console.log('Delete user:', user);
  }

  addEnseignant() {
     this.showModal = true;
  }
}
