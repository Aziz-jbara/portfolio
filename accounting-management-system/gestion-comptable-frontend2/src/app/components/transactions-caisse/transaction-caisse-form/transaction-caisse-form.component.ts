import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';

import { TransactionCaisseService } from '../../../services/transaction-caisse.service';
import { FactureClientService } from '../../../services/facture-client.service';
import { FactureFrsService } from '../../../services/facture-frs.service';
import { TransactionEtatService } from '../../../services/transaction-etat.service';
import { TransactionCategorieService } from '../../../services/transaction-categorie.service';
import { TransactionSensService } from '../../../services/transaction-sens.service';

import { TransactionCaisse } from '../../../models/transaction-caisse.model';
import { FactureClient } from '../../../models/facture-client.model';
import { FactureFrs } from '../../../models/facture-frs.model';
import { TransactionEtat } from '../../../models/transaction-etat.model';
import { TransactionCategorie } from '../../../models/transaction-categorie.model';
import { TransactionSens } from '../../../models/transaction-sens.model';

@Component({
  selector: 'app-transaction-caisse-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './transaction-caisse-form.component.html',
  styleUrls: ['./transaction-caisse-form.component.scss']
})
export class TransactionCaisseFormComponent implements OnInit {

  transaction: TransactionCaisse = {
    transactionscaisseReference: 0,
    transactionscaisseDate: this.todayAsString(),
    transactionscaisseLibelle: '',
    transactionscaisseNumPieceComptable: '',
    transactionscaisseMontant: 0,
    transactionscaisseCommentaire: '',
    transactionscaisseSoldeProgressif: 0,
    transactionscaisseCompteCourantAssocie: 0,
    factureClientId: 0,
    factureFrsId: 0,
    etatId: 0,
    categorieId: 0,
    sensId: 0,
    factureClient: null,
    factureFrs: null,
    transactionEtat: null,
    transactionCategorie: null,
    transactionSens: null
  };

  isEdit = false;
  id = 0;

  facturesClient: FactureClient[] = [];
  facturesFrs: FactureFrs[] = [];
  etats: TransactionEtat[] = [];
  categories: TransactionCategorie[] = [];
  sensList: TransactionSens[] = [];

  constructor(
    private caisseService: TransactionCaisseService,
    private factureClientService: FactureClientService,
    private factureFrsService: FactureFrsService,
    private etatService: TransactionEtatService,
    private categorieService: TransactionCategorieService,
    private sensService: TransactionSensService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.loadDropdowns();

    this.id = Number(this.route.snapshot.params['id']);

    if (this.id) {
      this.isEdit = true;

      this.caisseService.getTransactionById(this.id).subscribe({
        next: (res) => {
          this.transaction = {
            ...res,
            transactionscaisseDate: this.formatDateForInput(res.transactionscaisseDate),
            factureClientId: res.factureClient?.facturesClientReference ?? 0,
            factureFrsId: res.factureFrs?.facturesFRSReference ?? 0,
            etatId: res.transactionEtat?.transactionsetatReference ?? 0,
            categorieId: res.transactionCategorie?.transactionscategorieReference ?? 0,
            sensId: res.transactionSens?.transactionsnatureReference ?? 0
          };

          console.log('Transaction caisse edit:', this.transaction);
        },
        error: err => {
          console.error('Erreur chargement transaction caisse:', err);
        }
      });
    }
  }

  loadDropdowns(): void {
    this.factureClientService.getAllFacturesClient().subscribe({
      next: res => {
        this.facturesClient = res;
        console.log('Factures client:', res);
      },
      error: err => console.error('Erreur factures client:', err)
    });

    this.factureFrsService.getAllFacturesFrs().subscribe({
      next: res => {
        this.facturesFrs = res;
        console.log('Factures fournisseur:', res);
      },
      error: err => console.error('Erreur factures fournisseur:', err)
    });

    this.etatService.getAllEtats().subscribe({
      next: res => this.etats = res,
      error: err => console.error('Erreur états:', err)
    });

    this.categorieService.getAllCategories().subscribe({
      next: res => this.categories = res,
      error: err => console.error('Erreur catégories:', err)
    });

    this.sensService.getAllSens().subscribe({
      next: res => this.sensList = res,
      error: err => console.error('Erreur sens:', err)
    });
  }

  onFactureClientChange(): void {
    const factureClientId = Number(this.transaction.factureClientId) || 0;

    if (factureClientId > 0) {
      this.transaction.factureFrsId = 0;
      this.transaction.factureFrs = null;
    }
  }

  onFactureFrsChange(): void {
    const factureFrsId = Number(this.transaction.factureFrsId) || 0;

    if (factureFrsId > 0) {
      this.transaction.factureClientId = 0;
      this.transaction.factureClient = null;
    }
  }

  save(): void {
    const factureClientId = Number(this.transaction.factureClientId) || 0;
    const factureFrsId = Number(this.transaction.factureFrsId) || 0;
    const etatId = Number(this.transaction.etatId) || 0;
    const categorieId = Number(this.transaction.categorieId) || 0;
    const sensId = Number(this.transaction.sensId) || 0;

    const selectedFactureClient = this.facturesClient.find(
      f => f.facturesClientReference === factureClientId
    );

    const selectedFactureFrs = this.facturesFrs.find(
      f => f.facturesFRSReference === factureFrsId
    );

    const selectedEtat = this.etats.find(
      e => e.transactionsetatReference === etatId
    );

    const selectedCategorie = this.categories.find(
      c => c.transactionscategorieReference === categorieId
    );

    const selectedSens = this.sensList.find(
      s => s.transactionsnatureReference === sensId
    );

    const payload: TransactionCaisse = {
      ...this.transaction,

      factureClientId,
      factureFrsId,
      etatId,
      categorieId,
      sensId,

      factureClient: selectedFactureClient ?? null,
      factureFrs: selectedFactureFrs ?? null,
      transactionEtat: selectedEtat ?? null,
      transactionCategorie: selectedCategorie ?? null,
      transactionSens: selectedSens ?? null
    };

    console.log('Payload transaction caisse envoyé:', payload);

    if (this.isEdit) {
      this.caisseService.updateTransaction(this.id, payload).subscribe({
        next: () => this.router.navigate(['/transactions-caisse']),
        error: err => console.error('Erreur modification transaction caisse:', err)
      });
    } else {
      this.caisseService.createTransaction(payload).subscribe({
        next: () => this.router.navigate(['/transactions-caisse']),
        error: err => console.error('Erreur création transaction caisse:', err)
      });
    }
  }

  private todayAsString(): string {
    return new Date().toISOString().substring(0, 10);
  }

  private formatDateForInput(value: string | Date): string {
    if (!value) {
      return this.todayAsString();
    }

    if (typeof value === 'string') {
      return value.substring(0, 10);
    }

    return new Date(value).toISOString().substring(0, 10);
  }
}