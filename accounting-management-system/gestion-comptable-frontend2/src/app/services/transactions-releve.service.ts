import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TransactionsReleve } from '../models/transactions-releve.model';

@Injectable({
  providedIn: 'root'
})
export class TransactionsReleveService {

  private apiUrl = 'http://localhost:8080/api/releves';

  constructor(private http: HttpClient) {}

  getAllReleves(): Observable<TransactionsReleve[]> {
    return this.http.get<TransactionsReleve[]>(this.apiUrl);
  }

  getReleveById(id: number): Observable<TransactionsReleve> {
    return this.http.get<TransactionsReleve>(`${this.apiUrl}/${id}`);
  }

  getRelevesByAnnee(annee: number): Observable<TransactionsReleve[]> {
    return this.http.get<TransactionsReleve[]>(`${this.apiUrl}/annee/${annee}`);
  }

  getRelevesByMois(mois: string): Observable<TransactionsReleve[]> {
    return this.http.get<TransactionsReleve[]>(`${this.apiUrl}/mois/${mois}`);
  }

  createReleve(releve: TransactionsReleve): Observable<TransactionsReleve> {
    return this.http.post<TransactionsReleve>(this.apiUrl, releve);
  }

  updateReleve(id: number, releve: TransactionsReleve): Observable<TransactionsReleve> {
    return this.http.put<TransactionsReleve>(`${this.apiUrl}/${id}`, releve);
  }

  deleteReleve(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}