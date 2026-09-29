import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';

import { FactureFrsService } from '../../../services/facture-frs.service';
import { FactureFrs } from '../../../models/facture-frs.model';

@Component({
  selector: 'app-factures-frs-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './factures-frs-list.component.html',
  styleUrls: ['./factures-frs-list.component.scss']
})
export class FacturesFrsListComponent implements OnInit {
  factures: FactureFrs[] = [];

  constructor(
    private factureFrsService: FactureFrsService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.loadFactures();
  }

  loadFactures(): void {
    this.factureFrsService.getAllFacturesFrs().subscribe({
      next: (data) => this.factures = data
    });
  }

  deleteFacture(id: number): void {
    if (confirm('Voulez-vous vraiment supprimer cette facture ?')) {
      this.factureFrsService.deleteFactureFrs(id).subscribe({
        next: () => this.loadFactures()
      });
    }
  }

  // ✅ PDF DOWNLOAD (copied + adapted)
  downloadPdf(id: number): void {
    this.http.get(
      `http://localhost:8080/api/factures-frs/${id}/pdf`,
      { responseType: 'blob' }
    ).subscribe({
      next: (blob: Blob) => {
        const url = window.URL.createObjectURL(blob);

        const a = document.createElement('a');
        a.href = url;
        a.download = `facture_frs_${id}.pdf`;
        a.click();

        window.URL.revokeObjectURL(url);
      },
      error: (err) => {
        console.error('Erreur téléchargement PDF', err);
      }
    });
  }
}