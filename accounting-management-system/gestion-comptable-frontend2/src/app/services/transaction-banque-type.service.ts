import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TransactionBanqueType } from '../models/transaction-banque-type.model';

@Injectable({
  providedIn: 'root'
})
export class TransactionBanqueTypeService {

  private apiUrl = 'http://localhost:8080/api/banque-types';

  constructor(private http: HttpClient) {}

  getAllTypes(): Observable<TransactionBanqueType[]> {
    return this.http.get<TransactionBanqueType[]>(this.apiUrl);
  }

  getTypeById(id: number): Observable<TransactionBanqueType> {
    return this.http.get<TransactionBanqueType>(`${this.apiUrl}/${id}`);
  }

  createType(type: TransactionBanqueType): Observable<TransactionBanqueType> {
    return this.http.post<TransactionBanqueType>(this.apiUrl, type);
  }

  updateType(id: number, type: TransactionBanqueType): Observable<TransactionBanqueType> {
    return this.http.put<TransactionBanqueType>(`${this.apiUrl}/${id}`, type);
  }

  deleteType(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}