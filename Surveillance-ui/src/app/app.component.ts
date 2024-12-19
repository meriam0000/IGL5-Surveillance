import { Component } from '@angular/core';
import { AuthService } from './auth/auth.service'; // Import the AuthService

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Surveillance-ui';
  static API_URL = "http://localhost:8080";

  constructor(private authService: AuthService) {}

  // Method to check if the user is logged in
  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }
}
