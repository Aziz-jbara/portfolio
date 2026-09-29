import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TransactionCategorie } from '../models/transaction-categorie.model';

@Injectable({
  providedIn: 'root'
})
export class TransactionCategorieService {

  private apiUrl = 'http://localhost:8080/api/categories';

  constructor(private http: HttpClient) {}

  getAllCategories(): Observable<TransactionCategorie[]> {
    return this.http.get<TransactionCategorie[]>(this.apiUrl);
  }

  getCategorieById(id: number): Observable<TransactionCategorie> {
    return this.http.get<TransactionCategorie>(`${this.apiUrl}/${id}`);
  }

  getCategoriesByNature(natureId: number): Observable<TransactionCategorie[]> {
    return this.http.get<TransactionCategorie[]>(`${this.apiUrl}/nature/${natureId}`);
  }

  createCategorie(categorie: TransactionCategorie): Observable<TransactionCategorie> {
    return this.http.post<TransactionCategorie>(this.apiUrl, categorie);
  }

  updateCategorie(id: number, categorie: TransactionCategorie): Observable<TransactionCategorie> {
    return this.http.put<TransactionCategorie>(`${this.apiUrl}/${id}`, categorie);
  }

  deleteCategorie(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}