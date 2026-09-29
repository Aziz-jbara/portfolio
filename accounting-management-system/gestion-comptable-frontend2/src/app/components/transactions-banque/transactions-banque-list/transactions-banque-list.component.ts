import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { TransactionBanqueService } from '../../../services/transaction-banque.service';
import { TransactionBanque } from '../../../models/transaction-banque.model';

@Component({
  selector: 'app-transactions-banque-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './transactions-banque-list.component.html',
  styleUrls: ['./transactions-banque-list.component.scss']
})
export class TransactionsBanqueListComponent implements OnInit {

  transactions: TransactionBanque[] = [];

  constructor(
    private transactionBanqueService: TransactionBanqueService
  ) {}

  ngOnInit(): void {
    this.loadTransactions();
  }

  loadTransactions(): void {
    this.transactionBanqueService.getAllTransactions().subscribe({
      next: (data) => {
        console.log('Transactions banque:', data);
        this.transactions = data;
      },
      error: err => {
        console.error('Erreur chargement transactions banque:', err);
      }
    });
  }

  deleteTransaction(id: number): void {
    if (confirm('Voulez-vous vraiment supprimer cette transaction ?')) {
      this.transactionBanqueService.deleteTransaction(id).subscribe({
        next: () => this.loadTransactions(),
        error: err => {
          console.error('Erreur suppression transaction banque:', err);
        }
      });
    }
  }
}