import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';

import { FactureFrsService } from '../../../services/facture-frs.service';
import { FactureFrs } from '../../../models/facture-frs.model';

import { FournisseurService } from '../../../services/fournisseur.service';
import { DeviseService } from '../../../services/devise.service';
import { RsAttestationRecupereeService } from '../../../services/rs-attestation-recuperee.service';

@Component({
  selector: 'app-facture-frs-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './facture-frs-form.component.html',
  styleUrls: ['./facture-frs-form.component.scss']
})
export class FactureFrsFormComponent implements OnInit {

  facture: FactureFrs = {
    facturesFRSReference: 0,
    facturesFRSNumero: '',
    facturesFRSMontantDevise: 0,
    facturesFRSTauxChange: 0,
    facturesFRSHT: 0,
    facturesFRSTVAPourcentage: 0,
    facturesFRSTVA: 0,
    facturesFRSTTC: 0,
    facturesFRSTimbre: 0,
    facturesFRSTotalFacture: 0,
    facturesFRSRSPourcentage: 0,
    facturesFRSRSTotal: 0,
    facturesFRSMontantAPayer: 0,
    facturesFRSMontantPayee: 0,
    fournisseurId: 0,
    deviseId: 0,
    rsAttestationId: 0
  };

  isEdit = false;
  id = 0;

  fournisseurs: any[] = [];
  devises: any[] = [];
  rsList: any[] = [];

  constructor(
    private factureFrsService: FactureFrsService,
    private fournisseurService: FournisseurService,
    private deviseService: DeviseService,
    private rsService: RsAttestationRecupereeService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.loadDropdowns();

    const param = this.route.snapshot.params['id'];

    if (param) {
      this.id = Number(param);
      this.isEdit = true;

      this.factureFrsService.getFactureFrsById(this.id).subscribe({
        next: (data) => {
          this.facture = {
            ...data,
            fournisseurId: data.fournisseur?.fournisseurReference ?? 0,
            deviseId: data.devise?.deviseReference ?? 0,
            rsAttestationId: data.rsAttestationRecuperee?.rsattestationrecupereReference ?? 0
          };

          console.log('Facture FRS edit:', this.facture);
        },
        error: (err) => console.error('Erreur chargement facture FRS', err)
      });
    }
  }

  loadDropdowns(): void {
    this.fournisseurService.getAllFournisseurs().subscribe({
      next: (data) => {
        console.log('FOURNISSEURS:', data);
        this.fournisseurs = data;
      },
      error: (err) => console.log('Fournisseur error', err)
    });

    this.deviseService.getAllDevises().subscribe({
      next: (data) => {
        console.log('DEVISES:', data);
        this.devises = data;
      },
      error: (err) => console.log('Devise error', err)
    });

    this.rsService.getAllRsAttestations().subscribe({
      next: (data) => {
        console.log('RS:', data);
        this.rsList = data;
      },
      error: (err) => console.log('RS error', err)
    });
  }

  save(): void {
    const fournisseurId = Number(this.facture.fournisseurId);
    const deviseId = Number(this.facture.deviseId);
    const rsAttestationId = Number(this.facture.rsAttestationId);

    if (!fournisseurId) {
      alert('Veuillez sélectionner un fournisseur.');
      return;
    }

    if (!deviseId) {
      alert('Veuillez sélectionner une devise.');
      return;
    }

    const payload: FactureFrs = {
      ...this.facture,

      fournisseurId,
      deviseId,
      rsAttestationId,

      fournisseur: fournisseurId
        ? { fournisseurReference: fournisseurId, fournisseurRaisonSocial: '' }
        : null,

      devise: deviseId
        ? { deviseReference: deviseId, deviseLibelle: '', deviseSymbole: '' }
        : null,

      rsAttestationRecuperee: rsAttestationId
        ? { rsattestationrecupereReference: rsAttestationId, rsattestationrecupereLibelle: '' }
        : null
    };

    console.log('Payload facture FRS envoyé:', payload);

    if (this.isEdit) {
      this.factureFrsService.updateFactureFrs(this.id, payload).subscribe({
        next: () => this.router.navigate(['/factures-frs']),
        error: (err) => console.error('Erreur modification facture FRS', err)
      });
    } else {
      this.factureFrsService.createFactureFrs(payload).subscribe({
        next: () => this.router.navigate(['/factures-frs']),
        error: (err) => console.error('Erreur création facture FRS', err)
      });
    }
  }
}