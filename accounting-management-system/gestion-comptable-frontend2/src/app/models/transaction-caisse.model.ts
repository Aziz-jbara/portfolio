import { FactureClient } from './facture-client.model';
import { FactureFrs } from './facture-frs.model';
import { TransactionEtat } from './transaction-etat.model';
import { TransactionCategorie } from './transaction-categorie.model';
import { TransactionSens } from './transaction-sens.model';

export interface TransactionCaisse {
  transactionscaisseReference: number;

  transactionscaisseDate: string;

  transactionscaisseLibelle: string;
  transactionscaisseNumPieceComptable: string;
  transactionscaisseMontant: number;
  transactionscaisseCommentaire: string;
  transactionscaisseSoldeProgressif: number;
  transactionscaisseCompteCourantAssocie: number;

  factureClientId?: number;
  factureFrsId?: number;
  etatId?: number;
  categorieId?: number;
  sensId?: number;

  factureClient?: FactureClient | null;
  factureFrs?: FactureFrs | null;
  transactionEtat?: TransactionEtat | null;
  transactionCategorie?: TransactionCategorie | null;
  transactionSens?: TransactionSens | null;
}