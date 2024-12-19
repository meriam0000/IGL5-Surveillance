import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpHandler } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/v1/auth/authenticate';  // Backend authentication URL

  constructor(private http: HttpClient) { } // HttpClient injected via constructor

  // Login method
  login(email: string, password: string): Observable<any> {
    const body = { email, password };

    return this.http.post<any>(this.apiUrl, body);
  }

  // Save tokens in localStorage (or sessionStorage, depending on your requirement)
  saveToken(accessToken: string, refreshToken: string): void {
    localStorage.setItem('accessToken', accessToken);
    localStorage.setItem('refreshToken', refreshToken);
  }

  // Retrieve access token from localStorage
  getAccessToken(): string | null {
    return localStorage.getItem('accessToken');
  }

  // Clear tokens on logout
  logout(): void {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
  }

  // Check if the user is logged in
  isLoggedIn(): boolean {
    return this.getAccessToken() !== null;
  }
}
