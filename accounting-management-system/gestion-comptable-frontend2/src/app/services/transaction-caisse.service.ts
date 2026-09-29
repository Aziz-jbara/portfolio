import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { TransactionCaisse } from '../models/transaction-caisse.model';

@Injectable({
  providedIn: 'root'
})
export class TransactionCaisseService {

  private apiUrl = 'http://localhost:8080/api/transactions-caisse';

  constructor(private http: HttpClient) {}

  getAllTransactions(): Observable<TransactionCaisse[]> {
    return this.http.get<TransactionCaisse[]>(this.apiUrl);
  }

  getTransactionById(id: number): Observable<TransactionCaisse> {
    return this.http.get<TransactionCaisse>(`${this.apiUrl}/${id}`);
  }

  getByFactureClient(factureClientId: number): Observable<TransactionCaisse[]> {
    return this.http.get<TransactionCaisse[]>(`${this.apiUrl}/facture-client/${factureClientId}`);
  }

  getByFactureFrs(factureFrsId: number): Observable<TransactionCaisse[]> {
    return this.http.get<TransactionCaisse[]>(`${this.apiUrl}/facture-frs/${factureFrsId}`);
  }

  createTransaction(transaction: TransactionCaisse): Observable<TransactionCaisse> {
    return this.http.post<TransactionCaisse>(this.apiUrl, transaction);
  }

  updateTransaction(id: number, transaction: TransactionCaisse): Observable<TransactionCaisse> {
    return this.http.put<TransactionCaisse>(`${this.apiUrl}/${id}`, transaction);
  }

  deleteTransaction(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}