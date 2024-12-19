// crud-users.component.ts
import { Component, OnInit } from '@angular/core';
import {User} from "../../Models/user.model";



@Component({
  selector: 'app-crud-users',
  templateUrl: './crud-users.component.html',
  styleUrls: ['./crud-users.component.css']
})
export class CrudUsersComponent implements OnInit {
  users: User[] = [
    {
      id: 1,
      firstname: "Meriam",
      fullName: "Meriam Belhadj Hamida",
      email: "meriam@gmail.com",
      password: "strongPassword123",
      role: {
        id: 1,
        name: "SUPERADMIN",
        authorities: ["MANAGE_USERS", "VIEW_DASHBOARD"]
      },

    }
  ];
  showModal = false;
  filteredUsers: User[] = [];
  currentPage = 1;
  pageSize = 10;
  pages: number[] = [];
  searchTerm = '';
  sortColumn = '';
  sortDirection = 'asc';

  ngOnInit() {
    this.filterUsers();
    this.updatePagination();
  }

  filterUsers() {
    this.filteredUsers = this.users.filter(user =>
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
    const pageCount = Math.ceil(this.filteredUsers.length / this.pageSize);
    this.pages = Array.from({ length: pageCount }, (_, i) => i + 1);
  }

  get paginatedUsers() {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    return this.filteredUsers.slice(startIndex, startIndex + this.pageSize);
  }

  sortUsers() {
    if (this.sortColumn) {
      this.filteredUsers.sort((a, b) => {
        const aValue = a[this.sortColumn as keyof User];
        const bValue = b[this.sortColumn as keyof User];
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
    this.filterUsers();
  }

  onPageChange(page: number) {
    this.currentPage = page;
  }

  addSuperAdmin(): void {
    console.log('Add Super Admin button clicked');
    this.showModal = true;  // Show the modal when button is clicked
  }

  closeModal(): void {
    this.showModal = false;  // Close the modal
  }

  addAdminEtablissement() {
    console.log('Add Admin Etablissement clicked');
  }

  addAdminDepartement() {
    console.log('Add Admin Departement clicked');
  }

  viewUser(user: User) {
    console.log('View user:', user);
  }

  editUser(user: User) {
    console.log('Edit user:', user);
  }

  deleteUser(user: User) {
    console.log('Delete user:', user);
  }
}
