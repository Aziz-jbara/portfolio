import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { FournisseurService } from '../../../services/fournisseur.service';
import { Fournisseur } from '../../../models/fournisseur.model';

@Component({
  selector: 'app-fournisseur-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './fournisseur-form.component.html',
  styleUrls: ['./fournisseur-form.component.scss']
})
export class FournisseurFormComponent implements OnInit {
  fournisseur: Fournisseur = {
    fournisseurReference: 0,
    fournisseurRaisonSocial: '',
    fournisseurAdresse: '',
    fournisseurCodePostal: '',
    fournisseurTelephone: '',
    fournisseurCodeTVA: '',
    fournisseursGouvernorat: '',
    fournisseursPays: '',
    fournisseursFax: ''
  };
  isEdit = false;
  id: number = 0;

  constructor(
    private fournisseurService: FournisseurService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    if (this.id) {
      this.isEdit = true;
      this.fournisseurService.getFournisseurById(this.id).subscribe({
        next: (data) => this.fournisseur = data
      });
    }
  }

  save(): void {
    if (this.isEdit) {
      this.fournisseurService.updateFournisseur(this.id, this.fournisseur).subscribe({
        next: () => this.router.navigate(['/fournisseurs'])
      });
    } else {
      this.fournisseurService.createFournisseur(this.fournisseur).subscribe({
        next: () => this.router.navigate(['/fournisseurs'])
      });
    }
  }
}