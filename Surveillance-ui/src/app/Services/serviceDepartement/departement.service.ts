import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {departement} from "../../Models/departement.model";
import {AppComponent} from "../../app.component";

@Injectable({
  providedIn: 'root'
})
export class DepartementService {
  private apiUrl = `/api/v1/departement`;

  constructor(private http: HttpClient) {}

   // Get all departements
  getAllDepartements(): Observable<departement[]> {
    return this.http.get<departement[]>(`${AppComponent.API_URL}${this.apiUrl}`);
  }

  // Get departement by ID
  getDepartementById(id: number): Observable<departement> {
    return this.http.get<departement>(`${AppComponent.API_URL}${this.apiUrl}/${id}`);
  }

  // Add a new departement
  addDepartement(departement:departement): Observable<departement> {
    return this.http.post<departement>(`${AppComponent.API_URL}${this.apiUrl}`, departement);
  }

  // Update an existing departement
  updateDepartement(id: number, departement:departement): Observable<departement> {
    return this.http.put<departement>(` ${AppComponent.API_URL}${this.apiUrl}/${id}`, departement);
  }

  // Delete a departement
  deleteDepartement(id: number): Observable<void> {
    return this.http.delete<void>(`${AppComponent.API_URL}${this.apiUrl}/${id}`);
  }

  // Get departements by Etablissement ID
  getDepartementsByEtablissementId(id: number): Observable<departement[]> {
    return this.http.get<departement[]>(`${AppComponent.API_URL}${this.apiUrl}/etablissement/${id}`);
  }

}
