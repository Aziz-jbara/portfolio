import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TransactionEtat } from '../models/transaction-etat.model';

@Injectable({
  providedIn: 'root'
})
export class TransactionEtatService {

  private apiUrl = 'http://localhost:8080/api/etats';

  constructor(private http: HttpClient) {}

  getAllEtats(): Observable<TransactionEtat[]> {
    return this.http.get<TransactionEtat[]>(this.apiUrl);
  }

  getEtatById(id: number): Observable<TransactionEtat> {
    return this.http.get<TransactionEtat>(`${this.apiUrl}/${id}`);
  }

  createEtat(etat: TransactionEtat): Observable<TransactionEtat> {
    return this.http.post<TransactionEtat>(this.apiUrl, etat);
  }

  updateEtat(id: number, etat: TransactionEtat): Observable<TransactionEtat> {
    return this.http.put<TransactionEtat>(`${this.apiUrl}/${id}`, etat);
  }

  deleteEtat(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}