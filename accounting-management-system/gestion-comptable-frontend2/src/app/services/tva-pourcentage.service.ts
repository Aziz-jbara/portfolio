import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TvaPourcentage } from '../models/tva-pourcentage.model';

@Injectable({
  providedIn: 'root'
})
export class TvaPourcentageService {

  private apiUrl = 'http://localhost:8080/api/tva';

  constructor(private http: HttpClient) {}

  getAllTva(): Observable<TvaPourcentage[]> {
    return this.http.get<TvaPourcentage[]>(this.apiUrl);
  }

  getTvaById(id: number): Observable<TvaPourcentage> {
    return this.http.get<TvaPourcentage>(`${this.apiUrl}/${id}`);
  }

  createTva(tva: TvaPourcentage): Observable<TvaPourcentage> {
    return this.http.post<TvaPourcentage>(this.apiUrl, tva);
  }

  updateTva(id: number, tva: TvaPourcentage): Observable<TvaPourcentage> {
    return this.http.put<TvaPourcentage>(`${this.apiUrl}/${id}`, tva);
  }

  deleteTva(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}