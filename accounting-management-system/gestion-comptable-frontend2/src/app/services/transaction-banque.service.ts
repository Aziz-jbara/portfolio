import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { TransactionBanque } from '../models/transaction-banque.model';

@Injectable({
  providedIn: 'root'
})
export class TransactionBanqueService {

  private apiUrl = 'http://localhost:8080/api/transactions-banque';

  constructor(private http: HttpClient) {}

  getAllTransactions(): Observable<TransactionBanque[]> {
    return this.http.get<TransactionBanque[]>(this.apiUrl);
  }

  getTransactionById(id: number): Observable<TransactionBanque> {
    return this.http.get<TransactionBanque>(`${this.apiUrl}/${id}`);
  }

  getByFactureClient(factureClientId: number): Observable<TransactionBanque[]> {
    return this.http.get<TransactionBanque[]>(`${this.apiUrl}/facture-client/${factureClientId}`);
  }

  getByFactureFrs(factureFrsId: number): Observable<TransactionBanque[]> {
    return this.http.get<TransactionBanque[]>(`${this.apiUrl}/facture-frs/${factureFrsId}`);
  }

  getByReleve(releveId: number): Observable<TransactionBanque[]> {
    return this.http.get<TransactionBanque[]>(`${this.apiUrl}/releve/${releveId}`);
  }

  createTransaction(transaction: TransactionBanque): Observable<TransactionBanque> {
    return this.http.post<TransactionBanque>(this.apiUrl, transaction);
  }

  updateTransaction(id: number, transaction: TransactionBanque): Observable<TransactionBanque> {
    return this.http.put<TransactionBanque>(`${this.apiUrl}/${id}`, transaction);
  }

  deleteTransaction(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}