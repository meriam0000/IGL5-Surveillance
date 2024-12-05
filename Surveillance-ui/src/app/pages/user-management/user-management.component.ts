import { Component, OnInit } from '@angular/core';

interface User {
  id: number;
  username: string;
  email: string;
  role: string;
  status: string;
}

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit {
  users: User[] = [];
  paginatedUsers: User[] = [];
  currentPage: number = 1;
  pageSize: number = 5;
  totalPages: number = 0;
  selectedUsers: Set<number> = new Set();
  filters: { username: string; role: string; status: string } = { username: '', role: '', status: '' };

  ngOnInit(): void {
    this.loadUsers();
  }

  // Load sample user data
  loadUsers(): void {
    this.users = [
      { id: 1, username: 'johndoe', email: 'johndoe@example.com', role: 'Admin', status: 'Active' },
      { id: 2, username: 'janedoe', email: 'janedoe@example.com', role: 'User', status: 'Inactive' },
      // Add more users here
    ];
    this.totalPages = Math.ceil(this.users.length / this.pageSize);
    this.updatePageData();
  }

  updatePageData(): void {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    this.paginatedUsers = this.users.slice(startIndex, startIndex + this.pageSize);
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.updatePageData();
    }
  }

  prevPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updatePageData();
    }
  }

  applyFilters(): void {
    const { username, role, status } = this.filters;
    const filteredUsers = this.users.filter(user =>
      (!username || user.username.toLowerCase().includes(username.toLowerCase())) &&
      (!role || user.role === role) &&
      (!status || user.status === status)
    );
    this.totalPages = Math.ceil(filteredUsers.length / this.pageSize);
    this.paginatedUsers = filteredUsers.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
  }

  deleteUser(userId: number): void {
    this.users = this.users.filter(user => user.id !== userId);
    this.updatePageData();
  }

  toggleSelection(userId: number): void {
    if (this.selectedUsers.has(userId)) {
      this.selectedUsers.delete(userId);
    } else {
      this.selectedUsers.add(userId);
    }
  }

  deleteSelected(): void {
    this.users = this.users.filter(user => !this.selectedUsers.has(user.id));
    this.selectedUsers.clear();
    this.updatePageData();
  }

  // Additional methods for edit and view functionality
  editUser(userId: number): void {
    console.log(`Edit user with ID: ${userId}`);
  }

  viewUser(userId: number): void {
    console.log(`View user with ID: ${userId}`);
  }
}
