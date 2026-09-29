import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TransactionSens } from '../models/transaction-sens.model';

@Injectable({
  providedIn: 'root'
})
export class TransactionSensService {

  private apiUrl = 'http://localhost:8080/api/sens';

  constructor(private http: HttpClient) {}

  getAllSens(): Observable<TransactionSens[]> {
    return this.http.get<TransactionSens[]>(this.apiUrl);
  }

  getSensById(id: number): Observable<TransactionSens> {
    return this.http.get<TransactionSens>(`${this.apiUrl}/${id}`);
  }

  createSens(sens: TransactionSens): Observable<TransactionSens> {
    return this.http.post<TransactionSens>(this.apiUrl, sens);
  }

  updateSens(id: number, sens: TransactionSens): Observable<TransactionSens> {
    return this.http.put<TransactionSens>(`${this.apiUrl}/${id}`, sens);
  }

  deleteSens(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}