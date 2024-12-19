import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Enseignant} from "../../Models/enseignant.model";
import { AppComponent } from '../../app.component';

@Injectable({
  providedIn: 'root'
})
export class EnseignantService {

  private apiUrl = `/api/v1/enseignant`; // Update with your API base URL

  constructor(private http: HttpClient) {}

  // Fetch all enseignants
  getAllEnseignants(): Observable<Enseignant[]> {
    return this.http.get<Enseignant[]>(`${AppComponent.API_URL}${this.apiUrl}`);
  }

  // Get enseignant by ID
  getEnseignantById(id: number): Observable<Enseignant> {
    return this.http.get<Enseignant>(`${AppComponent.API_URL}${this.apiUrl}/${id}`);
  }

  // Add a new enseignant
  addEnseignant(enseignant: Enseignant): Observable<Enseignant> {
    return this.http.post<Enseignant>(`${AppComponent.API_URL}${this.apiUrl}`, enseignant);
  }

  // Update an existing enseignant
  updateEnseignant(id: number, enseignant:Enseignant): Observable<Enseignant> {
    return this.http.put<Enseignant>(`${AppComponent.API_URL}${this.apiUrl}/${id}`, enseignant);
  }

  // Delete an enseignant
  deleteEnseignant(id: number): Observable<void> {
    return this.http.delete<void>(`${AppComponent.API_URL}${this.apiUrl}/${id}`);
  }
}
