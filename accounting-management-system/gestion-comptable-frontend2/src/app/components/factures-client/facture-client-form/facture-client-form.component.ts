import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';

import { FactureClientService } from '../../../services/facture-client.service';
import { ClientService } from '../../../services/client.service';
import { DeviseService } from '../../../services/devise.service';
import { FactureClientCategorieService } from '../../../services/facture-client-categorie.service';

import {
  FactureClient,
  Client,
  Devise,
  FactureClientCategorie
} from '../../../models/facture-client.model';

@Component({
  selector: 'app-facture-client-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './facture-client-form.component.html',
  styleUrls: ['./facture-client-form.component.scss']
})
export class FactureClientFormComponent implements OnInit {

  facture: FactureClient = {
    facturesClientReference: 0,
    facturesClientNumero: 0,
    facturesClientMontantDevise: 0,
    facturesClientTauxChange: 0,
    facturesClientHT: 0,
    facturesClientTVA: 0,
    facturesClientTTC: 0,
    facturesClientTimbre: 0,
    facturesClientTotalFacture: 0,
    facturesClientRSTVAPourcentage: 0,
    facturesClientRSTVA: 0,
    facturesClientRSPourcentage: 0,
    facturesClientRS: 0,
    facturesClientRSTotal: 0,
    facturesClientMontantARecevoir: 0,
    facturesClientMontantVerse: 0,
    facturesClientMontantEcart: 0,
    clientId: 0,
    deviseId: 0,
    categorieId: 0
  };

  clients: Client[] = [];
  devises: Devise[] = [];
  categories: FactureClientCategorie[] = [];

  isEdit = false;
  id!: number;

  constructor(
    private factureService: FactureClientService,
    private clientService: ClientService,
    private deviseService: DeviseService,
    private categorieService: FactureClientCategorieService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.loadDropdowns();

    this.id = Number(this.route.snapshot.params['id']);

    if (this.id) {
      this.isEdit = true;

      this.factureService.getFactureClientById(this.id).subscribe({
        next: (data: FactureClient) => {
          this.facture = {
            ...data,
            clientId: data.client?.clientsReference ?? 0,
            deviseId: data.devise?.deviseReference ?? 0,
            categorieId: data.categorie?.factureclientcategorieReference ?? 0
          };

          this.calculateMontants();
        },
        error: err => {
          console.error('Erreur chargement facture client', err);
        }
      });
    }
  }

  loadDropdowns(): void {
    this.clientService.getAllClients().subscribe({
      next: (data: Client[]) => {
        this.clients = data;
      },
      error: err => {
        console.error('Erreur chargement clients', err);
      }
    });

    this.deviseService.getAllDevises().subscribe({
      next: (data: Devise[]) => {
        this.devises = data;
      },
      error: err => {
        console.error('Erreur chargement devises', err);
      }
    });

    this.categorieService.getAllCategories().subscribe({
      next: (data: FactureClientCategorie[]) => {
        this.categories = data;
      },
      error: err => {
        console.error('Erreur chargement catégories', err);
      }
    });
  }

  calculateMontants(): void {
    const totalFacture = Number(this.facture.facturesClientTotalFacture) || 0;
    const tva = Number(this.facture.facturesClientTVA) || 0;

    const rsTvaPourcentage = Number(this.facture.facturesClientRSTVAPourcentage) || 0;
    const rsPourcentage = Number(this.facture.facturesClientRSPourcentage) || 0;

    const montantVerse = Number(this.facture.facturesClientMontantVerse) || 0;

    this.facture.facturesClientRSTVA = tva * rsTvaPourcentage / 100;

    this.facture.facturesClientRS = totalFacture * rsPourcentage / 100;

    this.facture.facturesClientRSTotal =
      this.facture.facturesClientRSTVA + this.facture.facturesClientRS;

    this.facture.facturesClientMontantARecevoir =
      totalFacture - this.facture.facturesClientRSTotal;

    this.facture.facturesClientMontantEcart =
      this.facture.facturesClientMontantARecevoir - montantVerse;
  }

  save(): void {
    this.calculateMontants();

    const clientId = Number(this.facture.clientId);
    const deviseId = Number(this.facture.deviseId);
    const categorieId = Number(this.facture.categorieId);

    const selectedClient = this.clients.find(c => c.clientsReference === clientId);
    const selectedDevise = this.devises.find(d => d.deviseReference === deviseId);
    const selectedCategorie = this.categories.find(c => c.factureclientcategorieReference === categorieId);

    if (!selectedClient) {
      alert('Veuillez sélectionner un client.');
      return;
    }

    if (!selectedDevise) {
      alert('Veuillez sélectionner une devise.');
      return;
    }

    if (!selectedCategorie) {
      alert('Veuillez sélectionner une catégorie.');
      return;
    }

    const payload: FactureClient = {
      ...this.facture,

      clientId,
      deviseId,
      categorieId,

      client: {
        clientsReference: clientId,
        clientsRaisonSocial: selectedClient.clientsRaisonSocial,
        clientsAdresse: selectedClient.clientsAdresse,
        clientsCodePostal: selectedClient.clientsCodePostal,
        clientsTelephone: selectedClient.clientsTelephone,
        clientsCodeTVA: selectedClient.clientsCodeTVA,
        clientsFax: selectedClient.clientsFax,
        clientsGouvernorat: selectedClient.clientsGouvernorat,
        clientsPays: selectedClient.clientsPays
      },

      devise: {
        deviseReference: deviseId,
        deviseLibelle: selectedDevise.deviseLibelle,
        deviseLibelleISO: selectedDevise.deviseLibelleISO,
        deviseSymbole: selectedDevise.deviseSymbole
      },

      categorie: {
        factureclientcategorieReference: categorieId,
        factureclientcategorieLibelle: selectedCategorie.factureclientcategorieLibelle,
        factureclientcategorieDescription: selectedCategorie.factureclientcategorieDescription
      }
    };

    console.log('Payload envoyé facture client:', payload);

    if (this.isEdit) {
      this.factureService.updateFactureClient(this.id, payload).subscribe({
        next: () => this.router.navigate(['/factures-client']),
        error: err => console.error('Erreur modification facture client', err)
      });
    } else {
      this.factureService.createFactureClient(payload).subscribe({
        next: () => this.router.navigate(['/factures-client']),
        error: err => console.error('Erreur création facture client', err)
      });
    }
  }
}