import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RsAttestationRecuperee } from '../models/rs-attestation-recuperee.model';

@Injectable({
  providedIn: 'root'
})
export class RsAttestationRecupereeService {

  private apiUrl = 'http://localhost:8080/api/rs-attestations';

  constructor(private http: HttpClient) {}

  getAllRsAttestations(): Observable<RsAttestationRecuperee[]> {
    return this.http.get<RsAttestationRecuperee[]>(this.apiUrl);
  }

  getRsAttestationById(id: number): Observable<RsAttestationRecuperee> {
    return this.http.get<RsAttestationRecuperee>(`${this.apiUrl}/${id}`);
  }

  createRsAttestation(rs: RsAttestationRecuperee): Observable<RsAttestationRecuperee> {
    return this.http.post<RsAttestationRecuperee>(this.apiUrl, rs);
  }

  updateRsAttestation(id: number, rs: RsAttestationRecuperee): Observable<RsAttestationRecuperee> {
    return this.http.put<RsAttestationRecuperee>(`${this.apiUrl}/${id}`, rs);
  }

  deleteRsAttestation(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}