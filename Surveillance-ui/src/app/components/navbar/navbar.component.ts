// src/app/components/navbar/navbar.component.ts
import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  searchControl = new FormControl('');

  onSearch() {
    // Implement search functionality
    console.log('Searching for:', this.searchControl.value);
  }
}