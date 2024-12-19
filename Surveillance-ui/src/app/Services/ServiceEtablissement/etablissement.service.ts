import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {etablissement} from "../../Models/etablissement.model";
import {AppComponent} from "../../app.component";

@Injectable({
  providedIn: 'root'
})

export class EtablissementService {

  constructor(private http: HttpClient) {}
  private apiUrl = `/api/v1/etablissement`;  // Your API base URL

  // Get all etablissements
  getAllEtablissements(): Observable<etablissement[]> {
    return this.http.get<etablissement[]>(`${AppComponent.API_URL}${this.apiUrl}`).pipe(
      catchError(this.handleError)
    );

  }

  // Get etablissement by ID
  getEtablissementById(id: number): Observable<etablissement> {
    return this.http.get<etablissement>(`${AppComponent.API_URL}${this.apiUrl}/${id}`);
  }

  // Add a new etablissement
  addEtablissement(etablissement: etablissement): Observable<etablissement> {
    return this.http.post<etablissement>(`${AppComponent.API_URL}${this.apiUrl}`, etablissement);
  }

  // Update an existing etablissement
  updateEtablissement(id: number, etablissement: etablissement): Observable<etablissement> {
    return this.http.put<etablissement>(`${AppComponent.API_URL}${this.apiUrl}/${id}`, etablissement);
  }

  // Delete an etablissement
  deleteEtablissement(id: number): Observable<void> {
    return this.http.delete<void>(`${AppComponent.API_URL}${this.apiUrl}/${id}`);
  }

  private handleError(error: any): Observable<any> {
    console.error('An error occurred:', error);
    return throwError(error);
  }
}
