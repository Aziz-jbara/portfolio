import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

import { AuthService } from '../../services/auth.service';
import { LoginRequest } from '../../models/auth.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  request: LoginRequest = {
    email: '',
    motDePasse: ''
  };

  error = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  login(): void {
    this.error = '';

    if (!this.request.email || !this.request.motDePasse) {
      this.error = 'Veuillez saisir votre email et mot de passe.';
      return;
    }

    this.authService.login(this.request).subscribe({
      next: (token: string) => {
        if (!token) {
          this.error = 'Token invalide reçu du serveur.';
          return;
        }

        this.authService.saveToken(token);
        this.router.navigateByUrl('/dashboard');
      },
      error: (err) => {
        console.error('Login error:', err);
        this.error = 'Email ou mot de passe incorrect';
      }
    });
  }
}