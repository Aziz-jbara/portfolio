import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';

import { TransactionBanqueService } from '../../../services/transaction-banque.service';
import { TransactionsReleveService } from '../../../services/transactions-releve.service';
import { TransactionEtatService } from '../../../services/transaction-etat.service';
import { TransactionCategorieService } from '../../../services/transaction-categorie.service';
import { TransactionSensService } from '../../../services/transaction-sens.service';
import { TransactionBanqueTypeService } from '../../../services/transaction-banque-type.service';
import { FactureClientService } from '../../../services/facture-client.service';
import { FactureFrsService } from '../../../services/facture-frs.service';

import { TransactionBanque } from '../../../models/transaction-banque.model';
import { TransactionsReleve } from '../../../models/transactions-releve.model';
import { TransactionEtat } from '../../../models/transaction-etat.model';
import { TransactionCategorie } from '../../../models/transaction-categorie.model';
import { TransactionSens } from '../../../models/transaction-sens.model';
import { TransactionBanqueType } from '../../../models/transaction-banque-type.model';
import { FactureClient } from '../../../models/facture-client.model';
import { FactureFrs } from '../../../models/facture-frs.model';

@Component({
  selector: 'app-transaction-banque-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './transaction-banque-form.component.html',
  styleUrls: ['./transaction-banque-form.component.scss']
})
export class TransactionBanqueFormComponent implements OnInit {

  transaction: TransactionBanque = {
    transactionsbanqueReference: 0,
    transactionsbanqueDateReelle: this.todayAsString(),
    transactionsbanqueDateOperation: this.todayAsString(),
    transactionsbanqueLibelle: '',
    transactionsbanqueMontant: 0,
    transactionsbanqueCommentaire: '',
    transactionsbanqueNumeroDeType: '',
    releveId: 0,
    factureClientId: 0,
    factureFrsId: 0,
    etatId: 0,
    categorieId: 0,
    sensId: 0,
    typeId: 0,
    transactionsReleve: null,
    factureClient: null,
    factureFrs: null,
    transactionEtat: null,
    transactionCategorie: null,
    transactionSens: null,
    transactionBanqueType: null
  };

  releves: TransactionsReleve[] = [];
  etats: TransactionEtat[] = [];
  categories: TransactionCategorie[] = [];
  sensList: TransactionSens[] = [];
  types: TransactionBanqueType[] = [];
  facturesClient: FactureClient[] = [];
  facturesFrs: FactureFrs[] = [];

  isEdit = false;
  id = 0;

  constructor(
    private transactionBanqueService: TransactionBanqueService,
    private releveService: TransactionsReleveService,
    private etatService: TransactionEtatService,
    private categorieService: TransactionCategorieService,
    private sensService: TransactionSensService,
    private typeService: TransactionBanqueTypeService,
    private factureClientService: FactureClientService,
    private factureFrsService: FactureFrsService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.loadDropdowns();

    this.id = Number(this.route.snapshot.params['id']);

    if (this.id) {
      this.isEdit = true;

      this.transactionBanqueService.getTransactionById(this.id).subscribe({
        next: (data) => {
          this.transaction = {
            ...data,

            transactionsbanqueDateReelle: this.formatDateForInput(data.transactionsbanqueDateReelle),
            transactionsbanqueDateOperation: this.formatDateForInput(data.transactionsbanqueDateOperation),

            releveId: data.transactionsReleve?.transactionsreleveReference ?? 0,
            factureClientId: data.factureClient?.facturesClientReference ?? 0,
            factureFrsId: data.factureFrs?.facturesFRSReference ?? 0,
            etatId: data.transactionEtat?.transactionsetatReference ?? 0,
            categorieId: data.transactionCategorie?.transactionscategorieReference ?? 0,
            sensId: data.transactionSens?.transactionsnatureReference ?? 0,
            typeId: data.transactionBanqueType?.transactionbanquetypesReference ?? 0
          };

          console.log('Transaction banque edit:', this.transaction);
        },
        error: (err) => {
          console.error('Erreur chargement transaction :', err);
        }
      });
    }
  }

  loadDropdowns(): void {
    this.releveService.getAllReleves().subscribe({
      next: data => this.releves = data,
      error: err => console.error('Erreur relevés', err)
    });

    this.etatService.getAllEtats().subscribe({
      next: data => this.etats = data,
      error: err => console.error('Erreur états', err)
    });

    this.categorieService.getAllCategories().subscribe({
      next: data => this.categories = data,
      error: err => console.error('Erreur catégories', err)
    });

    this.sensService.getAllSens().subscribe({
      next: data => this.sensList = data,
      error: err => console.error('Erreur sens', err)
    });

    this.typeService.getAllTypes().subscribe({
      next: data => this.types = data,
      error: err => console.error('Erreur types', err)
    });

    this.factureClientService.getAllFacturesClient().subscribe({
      next: data => {
        this.facturesClient = data;
        console.log('Factures client:', data);
      },
      error: err => console.error('Erreur factures client', err)
    });

    this.factureFrsService.getAllFacturesFrs().subscribe({
      next: data => {
        this.facturesFrs = data;
        console.log('Factures fournisseur:', data);
      },
      error: err => console.error('Erreur factures fournisseur', err)
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
  const releveId = Number(this.transaction.releveId) || 0;
  const factureClientId = Number(this.transaction.factureClientId) || 0;
  const factureFrsId = Number(this.transaction.factureFrsId) || 0;
  const etatId = Number(this.transaction.etatId) || 0;
  const categorieId = Number(this.transaction.categorieId) || 0;
  const sensId = Number(this.transaction.sensId) || 0;
  const typeId = Number(this.transaction.typeId) || 0;

  const selectedReleve = this.releves.find(
    r => r.transactionsreleveReference === releveId
  );

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

  const selectedType = this.types.find(
    t => t.transactionbanquetypesReference === typeId
  );

  const payload: TransactionBanque = {
    ...this.transaction,

    releveId,
    factureClientId,
    factureFrsId,
    etatId,
    categorieId,
    sensId,
    typeId,

    transactionsReleve: selectedReleve ?? null,

    factureClient: selectedFactureClient
      ? {
          facturesClientReference: selectedFactureClient.facturesClientReference,
          facturesClientNumero: selectedFactureClient.facturesClientNumero,
          facturesClientMontantDevise: selectedFactureClient.facturesClientMontantDevise,
          facturesClientTauxChange: selectedFactureClient.facturesClientTauxChange,
          facturesClientHT: selectedFactureClient.facturesClientHT,
          facturesClientTVA: selectedFactureClient.facturesClientTVA,
          facturesClientTTC: selectedFactureClient.facturesClientTTC,
          facturesClientTimbre: selectedFactureClient.facturesClientTimbre,
          facturesClientTotalFacture: selectedFactureClient.facturesClientTotalFacture,
          facturesClientRSTVAPourcentage: selectedFactureClient.facturesClientRSTVAPourcentage,
          facturesClientRSTVA: selectedFactureClient.facturesClientRSTVA,
          facturesClientRSPourcentage: selectedFactureClient.facturesClientRSPourcentage,
          facturesClientRS: selectedFactureClient.facturesClientRS,
          facturesClientRSTotal: selectedFactureClient.facturesClientRSTotal,
          facturesClientMontantARecevoir: selectedFactureClient.facturesClientMontantARecevoir,
          facturesClientMontantVerse: selectedFactureClient.facturesClientMontantVerse,
          facturesClientMontantEcart: selectedFactureClient.facturesClientMontantEcart,
          client: selectedFactureClient.client,
          devise: selectedFactureClient.devise,
          categorie: selectedFactureClient.categorie
        }
      : null,

    factureFrs: selectedFactureFrs ?? null,

    transactionEtat: selectedEtat ?? null,
    transactionCategorie: selectedCategorie ?? null,
    transactionSens: selectedSens ?? null,
    transactionBanqueType: selectedType ?? null
  };

  console.log('Payload transaction banque envoyé:', payload);

  if (this.isEdit) {
    this.transactionBanqueService.updateTransaction(
      this.id,
      payload
    ).subscribe({
      next: () => this.router.navigate(['/transactions-banque']),
      error: err => console.error('Erreur modification transaction banque:', err)
    });
  } else {
    this.transactionBanqueService.createTransaction(
      payload
    ).subscribe({
      next: () => this.router.navigate(['/transactions-banque']),
      error: err => console.error('Erreur création transaction banque:', err)
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