import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FactureClientCategorie } from '../models/facture-client-categorie.model';

@Injectable({
  providedIn: 'root'
})
export class FactureClientCategorieService {

  private apiUrl = 'http://localhost:8080/api/facture-client-categories';

  constructor(private http: HttpClient) {}

  getAllCategories(): Observable<FactureClientCategorie[]> {
    return this.http.get<FactureClientCategorie[]>(this.apiUrl);
  }

  getCategorieById(id: number): Observable<FactureClientCategorie> {
    return this.http.get<FactureClientCategorie>(`${this.apiUrl}/${id}`);
  }

  createCategorie(categorie: FactureClientCategorie): Observable<FactureClientCategorie> {
    return this.http.post<FactureClientCategorie>(this.apiUrl, categorie);
  }

  updateCategorie(id: number, categorie: FactureClientCategorie): Observable<FactureClientCategorie> {
    return this.http.put<FactureClientCategorie>(`${this.apiUrl}/${id}`, categorie);
  }

  deleteCategorie(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}