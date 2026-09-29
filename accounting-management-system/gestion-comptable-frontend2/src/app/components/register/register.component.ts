import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { RegisterRequest } from '../../models/auth.model';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  request: RegisterRequest = { nom: '', prenom: '', email: '', motDePasse: '', telephone: '' };
  error: string = '';
  success: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  register(): void {
    this.authService.register(this.request).subscribe({
      next: () => {
        this.success = 'Compte créé avec succès!';
        this.router.navigate(['/login']);
      },
      error: (err) => {
  console.error('Register error:', err);
  this.error = JSON.stringify(err.error);
}
    });
  }
}