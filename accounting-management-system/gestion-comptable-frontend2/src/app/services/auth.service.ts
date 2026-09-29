import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router } from '@angular/router';

import { LoginRequest, RegisterRequest } from '../models/auth.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/auth';

  private loggedInSubject = new BehaviorSubject<boolean>(this.hasToken());
  loggedIn$ = this.loggedInSubject.asObservable();

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  login(request: LoginRequest): Observable<string> {
    return this.http.post(
      `${this.apiUrl}/login`,
      request,
      {
        responseType: 'text'
      }
    );
  }

  register(request: RegisterRequest): Observable<string> {
    return this.http.post(
      `${this.apiUrl}/register`,
      request,
      {
        responseType: 'text'
      }
    );
  }

  saveToken(token: string): void {
    localStorage.setItem('token', token);
    this.loggedInSubject.next(true);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return this.hasToken();
  }

  logout(): void {
    localStorage.removeItem('token');
    this.loggedInSubject.next(false);
    this.router.navigateByUrl('/login');
  }

  private hasToken(): boolean {
    return !!localStorage.getItem('token');
  }
}