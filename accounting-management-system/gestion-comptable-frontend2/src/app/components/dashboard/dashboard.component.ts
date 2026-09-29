import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  ElementRef,
  Inject,
  OnDestroy,
  OnInit,
  PLATFORM_ID,
  ViewChild
} from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { RouterModule } from '@angular/router';
import { forkJoin } from 'rxjs';

import { Chart } from 'chart.js/auto';

import { DashboardService } from '../../services/dashboard.service';
import { FactureClientService } from '../../services/facture-client.service';
import { FactureFrsService } from '../../services/facture-frs.service';
import { TransactionBanqueService } from '../../services/transaction-banque.service';
import { TransactionCaisseService } from '../../services/transaction-caisse.service';

import { Dashboard } from '../../models/dashboard.model';
import { FactureClient } from '../../models/facture-client.model';
import { FactureFrs } from '../../models/facture-frs.model';
import { TransactionBanque } from '../../models/transaction-banque.model';
import { TransactionCaisse } from '../../models/transaction-caisse.model';

interface DashboardMetrics {
  totalClients: number;
  totalFournisseurs: number;
  totalFacturesClient: number;
  totalFacturesFrs: number;
  totalTransactionsBanque: number;
  totalTransactionsCaisse: number;

  chiffreAffaires: number;
  totalAchats: number;
  totalARecevoir: number;
  totalAPayer: number;
  totalEncaisse: number;
  totalDecaisse: number;
  soldeNet: number;
  resultatFacturation: number;

  facturesClientPayees: number;
  facturesClientNonPayees: number;
  facturesFrsPayees: number;
  facturesFrsNonPayees: number;

  tauxEncaissement: number;
  tauxPaiement: number;
}

interface LatestTransaction {
  id: number;
  source: 'Banque' | 'Caisse';
  libelle: string;
  date: string;
  montant: number;
  tiers: string;
  facture: string;
  type: 'Recette' | 'Dépense' | 'Autre';
}

interface TopItem {
  label: string;
  value: number;
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, AfterViewInit, OnDestroy {

  @ViewChild('cashflowChart') cashflowChartRef?: ElementRef<HTMLCanvasElement>;
  @ViewChild('invoiceChart') invoiceChartRef?: ElementRef<HTMLCanvasElement>;
  @ViewChild('transactionChart') transactionChartRef?: ElementRef<HTMLCanvasElement>;
  @ViewChild('topClientsChart') topClientsChartRef?: ElementRef<HTMLCanvasElement>;

  stats: Dashboard | null = null;

  facturesClient: FactureClient[] = [];
  facturesFrs: FactureFrs[] = [];
  transactionsBanque: TransactionBanque[] = [];
  transactionsCaisse: TransactionCaisse[] = [];

  isLoading = true;
  hasError = false;

  metrics: DashboardMetrics = {
    totalClients: 0,
    totalFournisseurs: 0,
    totalFacturesClient: 0,
    totalFacturesFrs: 0,
    totalTransactionsBanque: 0,
    totalTransactionsCaisse: 0,

    chiffreAffaires: 0,
    totalAchats: 0,
    totalARecevoir: 0,
    totalAPayer: 0,
    totalEncaisse: 0,
    totalDecaisse: 0,
    soldeNet: 0,
    resultatFacturation: 0,

    facturesClientPayees: 0,
    facturesClientNonPayees: 0,
    facturesFrsPayees: 0,
    facturesFrsNonPayees: 0,

    tauxEncaissement: 0,
    tauxPaiement: 0
  };

  latestTransactions: LatestTransaction[] = [];
  topClients: TopItem[] = [];
  topFournisseurs: TopItem[] = [];

  private charts: Chart[] = [];
  private readonly isBrowser: boolean;
  private chartRetryCount = 0;
  private readonly maxChartRetries = 10;

  private readonly monthLabels = [
    'Jan', 'Fév', 'Mar', 'Avr', 'Mai', 'Juin',
    'Juil', 'Août', 'Sep', 'Oct', 'Nov', 'Déc'
  ];

  constructor(
    private dashboardService: DashboardService,
    private factureClientService: FactureClientService,
    private factureFrsService: FactureFrsService,
    private transactionBanqueService: TransactionBanqueService,
    private transactionCaisseService: TransactionCaisseService,
    private cdr: ChangeDetectorRef,
    @Inject(PLATFORM_ID) platformId: object
  ) {
    this.isBrowser = isPlatformBrowser(platformId);
  }

  ngOnInit(): void {
    this.loadDashboard();
  }

  ngAfterViewInit(): void {
    this.scheduleChartRender();
  }

  ngOnDestroy(): void {
    this.destroyCharts();
  }

  loadDashboard(): void {
    this.isLoading = true;
    this.hasError = false;
    this.chartRetryCount = 0;
    this.destroyCharts();

    forkJoin({
      stats: this.dashboardService.getDashboardStats(),
      facturesClient: this.factureClientService.getAllFacturesClient(),
      facturesFrs: this.factureFrsService.getAllFacturesFrs(),
      transactionsBanque: this.transactionBanqueService.getAllTransactions(),
      transactionsCaisse: this.transactionCaisseService.getAllTransactions()
    }).subscribe({
      next: (data) => {
        this.stats = data.stats;
        this.facturesClient = data.facturesClient || [];
        this.facturesFrs = data.facturesFrs || [];
        this.transactionsBanque = data.transactionsBanque || [];
        this.transactionsCaisse = data.transactionsCaisse || [];

        this.calculateMetrics();
        this.buildLatestTransactions();
        this.buildTopClients();
        this.buildTopFournisseurs();

        this.isLoading = false;
        this.hasError = false;

        this.cdr.detectChanges();
        this.scheduleChartRender();
      },
      error: (err) => {
        console.error('Dashboard error:', err);
        this.isLoading = false;
        this.hasError = true;
        this.cdr.detectChanges();
      }
    });
  }

  refresh(): void {
    this.loadDashboard();
  }

  private scheduleChartRender(): void {
    if (!this.isBrowser) {
      return;
    }

    setTimeout(() => {
      this.renderCharts();
    }, 100);
  }

  private renderCharts(): void {
    if (!this.isBrowser || this.isLoading || this.hasError) {
      return;
    }

    const allCanvasReady =
      !!this.cashflowChartRef?.nativeElement &&
      !!this.invoiceChartRef?.nativeElement &&
      !!this.transactionChartRef?.nativeElement &&
      !!this.topClientsChartRef?.nativeElement;

    if (!allCanvasReady) {
      if (this.chartRetryCount < this.maxChartRetries) {
        this.chartRetryCount++;
        this.scheduleChartRender();
      }

      return;
    }

    this.destroyCharts();

    this.renderCashflowChart();
    this.renderInvoiceChart();
    this.renderTransactionChart();
    this.renderTopClientsChart();
  }

  private calculateMetrics(): void {
    const chiffreAffaires = this.facturesClient.reduce(
      (sum, f) => sum + this.toNumber(f.facturesClientTotalFacture),
      0
    );

    const totalAchats = this.facturesFrs.reduce(
      (sum, f) => sum + this.toNumber(f.facturesFRSTotalFacture),
      0
    );

    const totalARecevoir = this.facturesClient.reduce(
      (sum, f) => sum + this.toNumber(f.facturesClientMontantARecevoir),
      0
    );

    const totalAPayer = this.facturesFrs.reduce(
      (sum, f) => sum + this.toNumber(f.facturesFRSMontantAPayer),
      0
    );

    const totalEncaisseBanque = this.transactionsBanque
      .filter(t => this.isIncomeBanque(t))
      .reduce((sum, t) => sum + this.toNumber(t.transactionsbanqueMontant), 0);

    const totalDecaisseBanque = this.transactionsBanque
      .filter(t => this.isExpenseBanque(t))
      .reduce((sum, t) => sum + this.toNumber(t.transactionsbanqueMontant), 0);

    const totalEncaisseCaisse = this.transactionsCaisse
      .filter(t => this.isIncomeCaisse(t))
      .reduce((sum, t) => sum + this.toNumber(t.transactionscaisseMontant), 0);

    const totalDecaisseCaisse = this.transactionsCaisse
      .filter(t => this.isExpenseCaisse(t))
      .reduce((sum, t) => sum + this.toNumber(t.transactionscaisseMontant), 0);

    const totalEncaisse = totalEncaisseBanque + totalEncaisseCaisse;
    const totalDecaisse = totalDecaisseBanque + totalDecaisseCaisse;

    const facturesClientPayees = this.facturesClient.filter(
      f => this.toNumber(f.facturesClientMontantARecevoir) <= 0
    ).length;

    const facturesClientNonPayees = this.facturesClient.length - facturesClientPayees;

    const facturesFrsPayees = this.facturesFrs.filter(
      f => this.toNumber(f.facturesFRSMontantAPayer) <= 0
    ).length;

    const facturesFrsNonPayees = this.facturesFrs.length - facturesFrsPayees;

    this.metrics = {
      totalClients: this.stats?.totalClients ?? 0,
      totalFournisseurs: this.stats?.totalFournisseurs ?? 0,
      totalFacturesClient: this.stats?.totalFacturesClient ?? this.facturesClient.length,
      totalFacturesFrs: this.stats?.totalFacturesFrs ?? this.facturesFrs.length,
      totalTransactionsBanque: this.stats?.totalTransactionsBanque ?? this.transactionsBanque.length,
      totalTransactionsCaisse: this.stats?.totalTransactionsCaisse ?? this.transactionsCaisse.length,

      chiffreAffaires,
      totalAchats,
      totalARecevoir,
      totalAPayer,
      totalEncaisse,
      totalDecaisse,
      soldeNet: totalEncaisse - totalDecaisse,
      resultatFacturation: chiffreAffaires - totalAchats,

      facturesClientPayees,
      facturesClientNonPayees,
      facturesFrsPayees,
      facturesFrsNonPayees,

      tauxEncaissement: chiffreAffaires > 0
        ? Math.min(100, Math.round(((chiffreAffaires - totalARecevoir) / chiffreAffaires) * 100))
        : 0,

      tauxPaiement: totalAchats > 0
        ? Math.min(100, Math.round(((totalAchats - totalAPayer) / totalAchats) * 100))
        : 0
    };
  }

  private buildLatestTransactions(): void {
    const banqueItems: LatestTransaction[] = this.transactionsBanque.map(t => {
      const factureClient = t.factureClient;
      const factureFrs = t.factureFrs;

      return {
        id: t.transactionsbanqueReference,
        source: 'Banque',
        libelle: t.transactionsbanqueLibelle || '-',
        date: t.transactionsbanqueDateOperation || t.transactionsbanqueDateReelle,
        montant: this.toNumber(t.transactionsbanqueMontant),
        tiers: factureClient?.client?.clientsRaisonSocial
          || factureFrs?.fournisseur?.fournisseurRaisonSocial
          || '-',
        facture: factureClient
          ? `FC N° ${factureClient.facturesClientNumero}`
          : factureFrs
            ? `FF N° ${factureFrs.facturesFRSNumero}`
            : '-',
        type: this.isIncomeBanque(t)
          ? 'Recette'
          : this.isExpenseBanque(t)
            ? 'Dépense'
            : 'Autre'
      };
    });

    const caisseItems: LatestTransaction[] = this.transactionsCaisse.map(t => {
      const factureClient = t.factureClient;
      const factureFrs = t.factureFrs;

      return {
        id: t.transactionscaisseReference,
        source: 'Caisse',
        libelle: t.transactionscaisseLibelle || '-',
        date: t.transactionscaisseDate,
        montant: this.toNumber(t.transactionscaisseMontant),
        tiers: factureClient?.client?.clientsRaisonSocial
          || factureFrs?.fournisseur?.fournisseurRaisonSocial
          || '-',
        facture: factureClient
          ? `FC N° ${factureClient.facturesClientNumero}`
          : factureFrs
            ? `FF N° ${factureFrs.facturesFRSNumero}`
            : '-',
        type: this.isIncomeCaisse(t)
          ? 'Recette'
          : this.isExpenseCaisse(t)
            ? 'Dépense'
            : 'Autre'
      };
    });

    this.latestTransactions = [...banqueItems, ...caisseItems]
      .sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime())
      .slice(0, 8);
  }

  private buildTopClients(): void {
    const map = new Map<string, number>();

    this.facturesClient.forEach(f => {
      const name = f.client?.clientsRaisonSocial || 'Client non défini';
      const amount = this.toNumber(f.facturesClientTotalFacture);

      map.set(name, (map.get(name) || 0) + amount);
    });

    this.topClients = Array.from(map.entries())
      .map(([label, value]) => ({ label, value }))
      .sort((a, b) => b.value - a.value)
      .slice(0, 5);
  }

  private buildTopFournisseurs(): void {
    const map = new Map<string, number>();

    this.facturesFrs.forEach(f => {
      const name = f.fournisseur?.fournisseurRaisonSocial || 'Fournisseur non défini';
      const amount = this.toNumber(f.facturesFRSTotalFacture);

      map.set(name, (map.get(name) || 0) + amount);
    });

    this.topFournisseurs = Array.from(map.entries())
      .map(([label, value]) => ({ label, value }))
      .sort((a, b) => b.value - a.value)
      .slice(0, 5);
  }

  private renderCashflowChart(): void {
    const canvas = this.cashflowChartRef?.nativeElement;

    if (!canvas) {
      return;
    }

    const recettes = new Array(12).fill(0);
    const depenses = new Array(12).fill(0);

    this.transactionsBanque.forEach(t => {
      const month = this.getMonthIndex(t.transactionsbanqueDateOperation || t.transactionsbanqueDateReelle);

      if (month === -1) {
        return;
      }

      if (this.isIncomeBanque(t)) {
        recettes[month] += this.toNumber(t.transactionsbanqueMontant);
      }

      if (this.isExpenseBanque(t)) {
        depenses[month] += this.toNumber(t.transactionsbanqueMontant);
      }
    });

    this.transactionsCaisse.forEach(t => {
      const month = this.getMonthIndex(t.transactionscaisseDate);

      if (month === -1) {
        return;
      }

      if (this.isIncomeCaisse(t)) {
        recettes[month] += this.toNumber(t.transactionscaisseMontant);
      }

      if (this.isExpenseCaisse(t)) {
        depenses[month] += this.toNumber(t.transactionscaisseMontant);
      }
    });

    this.charts.push(new Chart(canvas, {
      type: 'bar',
      data: {
        labels: this.monthLabels,
        datasets: [
          {
            label: 'Recettes',
            data: recettes,
            backgroundColor: 'rgba(34, 197, 94, 0.85)',
            borderRadius: 10
          },
          {
            label: 'Dépenses',
            data: depenses,
            backgroundColor: 'rgba(239, 68, 68, 0.85)',
            borderRadius: 10
          }
        ]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        animation: false,
        plugins: {
          legend: {
            position: 'bottom'
          },
          tooltip: {
            callbacks: {
              label: (context) => `${context.dataset.label}: ${this.formatMoney(context.parsed.y)}`
            }
          }
        },
        scales: {
          y: {
            beginAtZero: true,
            suggestedMax: Math.max(...recettes, ...depenses, 100),
            ticks: {
              callback: (value) => this.shortMoney(value)
            }
          },
          x: {
            grid: {
              display: false
            }
          }
        }
      }
    }));
  }

  private renderInvoiceChart(): void {
    const canvas = this.invoiceChartRef?.nativeElement;

    if (!canvas) {
      return;
    }

    const alreadyReceived = Math.max(this.metrics.chiffreAffaires - this.metrics.totalARecevoir, 0);
    const alreadyPaid = Math.max(this.metrics.totalAchats - this.metrics.totalAPayer, 0);

    let values = [
      this.metrics.totalARecevoir,
      alreadyReceived,
      this.metrics.totalAPayer,
      alreadyPaid
    ];

    if (values.every(v => v === 0)) {
      values = [1, 1, 1, 1];
    }

    this.charts.push(new Chart(canvas, {
      type: 'doughnut',
      data: {
        labels: [
          'À recevoir',
          'Déjà encaissé',
          'À payer',
          'Déjà payé'
        ],
        datasets: [
          {
            data: values,
            backgroundColor: [
              '#6366f1',
              '#22c55e',
              '#f97316',
              '#0ea5e9'
            ],
            borderWidth: 0
          }
        ]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        animation: false,
        cutout: '68%',
        plugins: {
          legend: {
            position: 'bottom'
          },
          tooltip: {
            callbacks: {
              label: (context) => `${context.label}: ${this.formatMoney(context.parsed)}`
            }
          }
        }
      }
    }));
  }

  private renderTransactionChart(): void {
    const canvas = this.transactionChartRef?.nativeElement;

    if (!canvas) {
      return;
    }

    const banqueRecettes = this.transactionsBanque
      .filter(t => this.isIncomeBanque(t))
      .reduce((sum, t) => sum + this.toNumber(t.transactionsbanqueMontant), 0);

    const banqueDepenses = this.transactionsBanque
      .filter(t => this.isExpenseBanque(t))
      .reduce((sum, t) => sum + this.toNumber(t.transactionsbanqueMontant), 0);

    const caisseRecettes = this.transactionsCaisse
      .filter(t => this.isIncomeCaisse(t))
      .reduce((sum, t) => sum + this.toNumber(t.transactionscaisseMontant), 0);

    const caisseDepenses = this.transactionsCaisse
      .filter(t => this.isExpenseCaisse(t))
      .reduce((sum, t) => sum + this.toNumber(t.transactionscaisseMontant), 0);

    this.charts.push(new Chart(canvas, {
      type: 'bar',
      data: {
        labels: ['Banque', 'Caisse'],
        datasets: [
          {
            label: 'Recettes',
            data: [banqueRecettes, caisseRecettes],
            backgroundColor: '#22c55e',
            borderRadius: 10
          },
          {
            label: 'Dépenses',
            data: [banqueDepenses, caisseDepenses],
            backgroundColor: '#ef4444',
            borderRadius: 10
          }
        ]
      },
      options: {
        indexAxis: 'y',
        responsive: true,
        maintainAspectRatio: false,
        animation: false,
        plugins: {
          legend: {
            position: 'bottom'
          },
          tooltip: {
            callbacks: {
              label: (context) => `${context.dataset.label}: ${this.formatMoney(context.parsed.x)}`
            }
          }
        },
        scales: {
          x: {
            beginAtZero: true,
            suggestedMax: Math.max(banqueRecettes, banqueDepenses, caisseRecettes, caisseDepenses, 100),
            ticks: {
              callback: (value) => this.shortMoney(value)
            }
          },
          y: {
            grid: {
              display: false
            }
          }
        }
      }
    }));
  }

  private renderTopClientsChart(): void {
    const canvas = this.topClientsChartRef?.nativeElement;

    if (!canvas) {
      return;
    }

    const labels = this.topClients.length
      ? this.topClients.map(item => item.label)
      : ['Aucun client'];

    const values = this.topClients.length
      ? this.topClients.map(item => item.value)
      : [0];

    this.charts.push(new Chart(canvas, {
      type: 'bar',
      data: {
        labels,
        datasets: [
          {
            label: 'Chiffre d’affaires',
            data: values,
            backgroundColor: [
              '#6366f1',
              '#8b5cf6',
              '#0ea5e9',
              '#22c55e',
              '#f97316'
            ],
            borderRadius: 10
          }
        ]
      },
      options: {
        indexAxis: 'y',
        responsive: true,
        maintainAspectRatio: false,
        animation: false,
        plugins: {
          legend: {
            display: false
          },
          tooltip: {
            callbacks: {
              label: (context) => this.formatMoney(context.parsed.x)
            }
          }
        },
        scales: {
          x: {
            beginAtZero: true,
            suggestedMax: Math.max(...values, 100),
            ticks: {
              callback: (value) => this.shortMoney(value)
            }
          },
          y: {
            grid: {
              display: false
            }
          }
        }
      }
    }));
  }

  private destroyCharts(): void {
    this.charts.forEach(chart => chart.destroy());
    this.charts = [];
  }

  private isIncomeBanque(t: TransactionBanque): boolean {
    if (t.factureClient) {
      return true;
    }

    if (t.factureFrs) {
      return false;
    }

    const sens = this.normalizeText(t.transactionSens?.transactionsnatureLibelle || '');

    return sens.includes('entree') || sens.includes('recette');
  }

  private isExpenseBanque(t: TransactionBanque): boolean {
    if (t.factureFrs) {
      return true;
    }

    if (t.factureClient) {
      return false;
    }

    const sens = this.normalizeText(t.transactionSens?.transactionsnatureLibelle || '');

    return sens.includes('sortie') || sens.includes('depense');
  }

  private isIncomeCaisse(t: TransactionCaisse): boolean {
    if (t.factureClient) {
      return true;
    }

    if (t.factureFrs) {
      return false;
    }

    const sens = this.normalizeText(t.transactionSens?.transactionsnatureLibelle || '');

    return sens.includes('entree') || sens.includes('recette');
  }

  private isExpenseCaisse(t: TransactionCaisse): boolean {
    if (t.factureFrs) {
      return true;
    }

    if (t.factureClient) {
      return false;
    }

    const sens = this.normalizeText(t.transactionSens?.transactionsnatureLibelle || '');

    return sens.includes('sortie') || sens.includes('depense');
  }

  private getMonthIndex(dateValue: string | Date | undefined | null): number {
    if (!dateValue) {
      return -1;
    }

    const date = new Date(dateValue);

    if (Number.isNaN(date.getTime())) {
      return -1;
    }

    return date.getMonth();
  }

  private normalizeText(value: string): string {
    return value
      .toLowerCase()
      .normalize('NFD')
      .replace(/[\u0300-\u036f]/g, '');
  }

  private toNumber(value: unknown): number {
    const numberValue = Number(value);

    return Number.isFinite(numberValue) ? numberValue : 0;
  }

  formatMoney(value: number | string | null | undefined): string {
    return `${this.toNumber(value).toLocaleString('fr-FR', {
      minimumFractionDigits: 2,
      maximumFractionDigits: 2
    })} DT`;
  }

  shortMoney(value: number | string | null | undefined): string {
    const amount = this.toNumber(value);

    if (Math.abs(amount) >= 1_000_000) {
      return `${(amount / 1_000_000).toFixed(1)}M`;
    }

    if (Math.abs(amount) >= 1_000) {
      return `${(amount / 1_000).toFixed(1)}K`;
    }

    return `${amount.toFixed(0)}`;
  }

  trackByIndex(index: number): number {
    return index;
  }
}