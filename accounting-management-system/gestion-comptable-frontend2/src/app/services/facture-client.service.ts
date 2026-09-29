import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { FactureClient } from '../models/facture-client.model';

@Injectable({
  providedIn: 'root'
})
export class FactureClientService {

  private apiUrl = 'http://localhost:8080/api/factures-client';

  constructor(private http: HttpClient) {}

  getAllFacturesClient(): Observable<FactureClient[]> {
    return this.http.get<FactureClient[]>(this.apiUrl);
  }

  getFactureClientById(id: number): Observable<FactureClient> {
    return this.http.get<FactureClient>(`${this.apiUrl}/${id}`);
  }

  getFacturesByClient(clientId: number): Observable<FactureClient[]> {
    return this.http.get<FactureClient[]>(`${this.apiUrl}/client/${clientId}`);
  }

  createFactureClient(facture: FactureClient): Observable<FactureClient> {
    return this.http.post<FactureClient>(this.apiUrl, facture);
  }

  updateFactureClient(id: number, facture: FactureClient): Observable<FactureClient> {
    return this.http.put<FactureClient>(`${this.apiUrl}/${id}`, facture);
  }

  deleteFactureClient(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}