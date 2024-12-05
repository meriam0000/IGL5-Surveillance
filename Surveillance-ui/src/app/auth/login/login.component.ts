import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  // Handle login submission
  onSubmit(): void {
    this.authService.login(this.email, this.password).subscribe({
      next: (response) => {
        console.log(response)
        // If login is successful, save the tokens and navigate to the dashboard or home page
        this.authService.saveToken(response.access_token, response.refresh_token);
        this.router.navigate(['/']);  // Navigate to a secure route
      },
      error: (error) => {
        // Handle error, show error message
        this.errorMessage = 'Invalid email or password';
      }
    });
  }
}
