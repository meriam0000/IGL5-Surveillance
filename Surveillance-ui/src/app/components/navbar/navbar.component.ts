// src/app/components/navbar/navbar.component.ts
import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {
  searchControl = new FormControl('');
  isDropdownOpen = false; // Track the dropdown state

  constructor(private authService: AuthService, private router: Router) {}

  onSearch() {
    console.log('Searching for:', this.searchControl.value);
  }

  logout() {
    this.authService.logout(); // Clear token
    this.router.navigate(['/login']); // Redirect to login page
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn(); // Check if the user is logged in
  }

  toggleDropdown() {
    this.isDropdownOpen = !this.isDropdownOpen; // Toggle the dropdown
  }

  navigateToLogin() {
    this.router.navigate(['/login']); // Navigate to login page
  }
}
