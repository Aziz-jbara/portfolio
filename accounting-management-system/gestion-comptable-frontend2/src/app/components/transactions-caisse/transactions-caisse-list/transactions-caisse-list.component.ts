import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { TransactionCaisseService } from '../../../services/transaction-caisse.service';
import { TransactionCaisse } from '../../../models/transaction-caisse.model';

@Component({
  selector: 'app-transactions-caisse-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './transactions-caisse-list.component.html',
  styleUrls: ['./transactions-caisse-list.component.scss']
})
export class TransactionsCaisseListComponent implements OnInit {

  transactions: TransactionCaisse[] = [];

  constructor(
    private transactionCaisseService: TransactionCaisseService
  ) {}

  ngOnInit(): void {
    this.loadTransactions();
  }

  loadTransactions(): void {
    this.transactionCaisseService.getAllTransactions().subscribe({
      next: (data) => {
        console.log('Transactions caisse:', data);
        this.transactions = data;
      },
      error: err => {
        console.error('Erreur chargement transactions caisse:', err);
      }
    });
  }

  deleteTransaction(id: number): void {
    if (confirm('Voulez-vous vraiment supprimer cette transaction ?')) {
      this.transactionCaisseService.deleteTransaction(id).subscribe({
        next: () => this.loadTransactions(),
        error: err => {
          console.error('Erreur suppression transaction caisse:', err);
        }
      });
    }
  }
}