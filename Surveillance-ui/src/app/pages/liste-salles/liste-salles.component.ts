import { Component, OnInit } from '@angular/core';
import { Salle } from '../../Models/salle.model.';
import { departement } from '../../Models/departement.model';
import { etablissement } from '../../Models/etablissement.model';

@Component({
  selector: 'app-liste-salles',
  templateUrl: './liste-salles.component.html',
  styleUrl: './liste-salles.component.css'
})
export class ListeSallesComponent implements OnInit{
  selectedRow: number | null = null;
  paginatedSalles: Salle[] = []; // Current page data
  currentPage: number = 1; // Default page number
  pageSize: number = 10; // Number of items per page
  totalPages: number = 0; // Total number of pages
  salles: Salle[] = [];
  selectedSalles: Set<number> = new Set(); // Track selected salles by ID
  selectAll: boolean = false; // Whether the "Select All" checkbox is checked
  pages: number[] = [];
  filterDropdownOpen: boolean= false;
  departements: departement[] = [];
  etablissements: etablissement[] = [];
  filters: {
    nom: string;
    type: string;
    capacite?: number;
    departement: string;
    etablissement: string;
  };




  ngOnInit(): void {
    this.loadEtablissements();
    this.loadDepartements();
    this.loadSalles();

  }
  constructor() {
    this.filters= { nom: '', type: '', capacite: undefined, departement: '', etablissement: '' } ;

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

  loadDepartements() {
    this.departements=[
       {
         id: 1,
         nom: 'Math-Department',
         specialité: 'mathématiques',
         etablissement:this.etablissements[0]
       },
       {
         id: 2,
         nom: 'physics',
         specialité: 'mathématiques',
         etablissement:this.etablissements[2]
       },
       {
         id: 3,
         nom: 'info-Department',
         specialité: 'mathématiques',
         etablissement:this.etablissements[3]
       },
       {
         id: 4,
         nom: 'chimie-Department', // should be 'Mathematics
         specialité: 'mathématiques',
         etablissement:this.etablissements[4]
       },
       {
         id: 5,
         nom: 'biologie-Department', // should be 'Mathematics
         specialité: 'mathématiques',
         etablissement:this.etablissements[4]
       }

    ]

   }

   loadSalles() {
    const salleTypes = ['Normale', 'Multiclasse'];
    const maxSalles = 25; // Maximum number of salles to generate
    const minSalles = 2; // Minimum number of salles to generate
    const numSalles = Math.floor(Math.random() * (maxSalles - minSalles + 1)) + minSalles;

    this.salles = [];

    for (let i = 0; i < numSalles; i++) {
      const randomDepartementIndex = Math.floor(Math.random() * this.departements.length);
      const randomTypeIndex = Math.floor(Math.random() * salleTypes.length);
      const randomCapacite = Math.floor(Math.random() * 50) + 10; // Random capacity between 10 and 60

      this.salles.push({
        id: i + 1,
        nom: `Salle ${String.fromCharCode(65 + i)}`, // Generates names like Salle A, Salle B, etc.
        capacite: randomCapacite,
        type: salleTypes[randomTypeIndex],
        departement: this.departements[randomDepartementIndex]
      });
    }

    this.totalPages = Math.ceil(this.salles.length / this.pageSize);
    this.updatePageData();
  }

  updatePageData() {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.paginatedSalles = this.salles.slice(startIndex, endIndex);
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
  deleteSalle(id: number): void {
    this.salles = this.salles.filter(salle => salle.id !== id);
    this.updatePageData();
  }

  editSalle(id: number): void {
    console.log('Editing salle with id:', id);
    // Implement edit logic
  }

  viewSalle(id: number): void {
    console.log('Viewing salle with id:', id);
  }


  // Toggle the selection of a specific salle
  toggleSelection(salleId: number) {
    if (this.selectedSalles.has(salleId)) {
      this.selectedSalles.delete(salleId); // Deselect
    } else {
      this.selectedSalles.add(salleId); // Select
    }
  }

  // Toggle the "Select All" checkbox
  toggleSelectAll() {
    if (this.selectAll) {
      this.selectedSalles = new Set(this.salles.map(salle => salle.id)); // Select all
    } else {
      this.selectedSalles.clear(); // Deselect all
    }
  }

  // Delete selected salles

  deleteSelected() {
    this.salles = this.salles.filter(salle => !this.selectedSalles.has(salle.id));
    this.selectedSalles.clear(); // Clear the selection
  }


  addSalle() {

  }


  onRowClick(salleId: number) {
    if (this.selectedRow === salleId) {
      this.selectedRow = null; // Deselect if the same row is clicked
    } else {
      this.selectedRow = salleId; // Select the clicked row
    }
  }

  toggleFilterDropdown() {
    this.filterDropdownOpen= !this.filterDropdownOpen;
  }
  applyFilters() {
    const { nom, capacite,type,departement,etablissement } = this.filters;

    // Apply filtering logic
    const filteredData = this.salles.filter((item) => {
      return (
        (!nom || item.nom.toLowerCase().includes(nom.toLowerCase())) &&
        (!type || item.type === type) &&
        (!capacite || item.capacite === capacite) &&
        (!departement || item.departement.nom === departement) &&
        (!etablissement || item.departement.etablissement.nom === etablissement)
      );
    });

    this.totalPages = Math.ceil(filteredData.length / this.pageSize);
    this.paginatedSalles = filteredData.slice(
      (this.currentPage - 1) * this.pageSize,
      this.currentPage * this.pageSize
    );

    this.filterDropdownOpen = false;
  }
}
