import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TransactionCategorieNature } from '../models/transaction-categorie-nature.model';

@Injectable({
  providedIn: 'root'
})
export class TransactionCategorieNatureService {

  private apiUrl = 'http://localhost:8080/api/categorie-natures';

  constructor(private http: HttpClient) {}

  getAllNatures(): Observable<TransactionCategorieNature[]> {
    return this.http.get<TransactionCategorieNature[]>(this.apiUrl);
  }

  getNatureById(id: number): Observable<TransactionCategorieNature> {
    return this.http.get<TransactionCategorieNature>(`${this.apiUrl}/${id}`);
  }

  createNature(nature: TransactionCategorieNature): Observable<TransactionCategorieNature> {
    return this.http.post<TransactionCategorieNature>(this.apiUrl, nature);
  }

  updateNature(id: number, nature: TransactionCategorieNature): Observable<TransactionCategorieNature> {
    return this.http.put<TransactionCategorieNature>(`${this.apiUrl}/${id}`, nature);
  }

  deleteNature(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}