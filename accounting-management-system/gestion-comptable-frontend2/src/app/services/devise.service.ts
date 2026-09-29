import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Devise } from '../models/devise.model';

@Injectable({
  providedIn: 'root'
})
export class DeviseService {

  private apiUrl = 'http://localhost:8080/api/devises';

  constructor(private http: HttpClient) {}

  getAllDevises(): Observable<Devise[]> {
    return this.http.get<Devise[]>(this.apiUrl);
  }

  getDeviseById(id: number): Observable<Devise> {
    return this.http.get<Devise>(`${this.apiUrl}/${id}`);
  }

  createDevise(devise: Devise): Observable<Devise> {
    return this.http.post<Devise>(this.apiUrl, devise);
  }

  updateDevise(id: number, devise: Devise): Observable<Devise> {
    return this.http.put<Devise>(`${this.apiUrl}/${id}`, devise);
  }

  deleteDevise(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}