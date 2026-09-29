import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { FactureFrs } from '../models/facture-frs.model';

@Injectable({
  providedIn: 'root'
})
export class FactureFrsService {

  private apiUrl = 'http://localhost:8080/api/factures-frs';

  constructor(private http: HttpClient) {}

  getAllFacturesFrs(): Observable<FactureFrs[]> {
    return this.http.get<FactureFrs[]>(this.apiUrl);
  }

  getFactureFrsById(id: number): Observable<FactureFrs> {
    return this.http.get<FactureFrs>(`${this.apiUrl}/${id}`);
  }

  getFacturesByFournisseur(fournisseurId: number): Observable<FactureFrs[]> {
    return this.http.get<FactureFrs[]>(`${this.apiUrl}/fournisseur/${fournisseurId}`);
  }

  createFactureFrs(facture: FactureFrs): Observable<FactureFrs> {
    return this.http.post<FactureFrs>(this.apiUrl, facture);
  }

  updateFactureFrs(id: number, facture: FactureFrs): Observable<FactureFrs> {
    return this.http.put<FactureFrs>(`${this.apiUrl}/${id}`, facture);
  }

  deleteFactureFrs(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}