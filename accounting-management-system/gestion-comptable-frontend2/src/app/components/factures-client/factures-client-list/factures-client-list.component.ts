import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';

import { FactureClientService } from '../../../services/facture-client.service';
import { FactureClient } from '../../../models/facture-client.model';

@Component({
  selector: 'app-factures-client-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './factures-client-list.component.html',
  styleUrls: ['./factures-client-list.component.scss']
})
export class FacturesClientListComponent implements OnInit {

  factures: FactureClient[] = [];

  constructor(
    private factureClientService: FactureClientService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.loadFactures();
  }

  loadFactures(): void {
    this.factureClientService.getAllFacturesClient().subscribe({
      next: (data) => {
        console.log('Factures client:', data);
        this.factures = data;
      },
      error: err => {
        console.error('Erreur chargement factures client', err);
      }
    });
  }

  deleteFacture(id: number): void {
    if (confirm('Voulez-vous vraiment supprimer cette facture ?')) {
      this.factureClientService.deleteFactureClient(id).subscribe({
        next: () => this.loadFactures(),
        error: err => console.error('Erreur suppression facture client', err)
      });
    }
  }

  downloadPdf(id: number): void {
    this.http.get(
      `http://localhost:8080/api/factures-client/${id}/pdf`,
      { responseType: 'blob' }
    ).subscribe({
      next: (blob: Blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');

        a.href = url;
        a.download = `facture_${id}.pdf`;
        a.click();

        window.URL.revokeObjectURL(url);
      },
      error: (err) => {
        console.error('Erreur téléchargement PDF', err);
      }
    });
  }
} 