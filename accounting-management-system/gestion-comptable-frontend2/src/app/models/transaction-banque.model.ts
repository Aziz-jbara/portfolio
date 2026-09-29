import { FactureClient } from './facture-client.model';
import { FactureFrs } from './facture-frs.model';
import { TransactionsReleve } from './transactions-releve.model';
import { TransactionEtat } from './transaction-etat.model';
import { TransactionCategorie } from './transaction-categorie.model';
import { TransactionSens } from './transaction-sens.model';
import { TransactionBanqueType } from './transaction-banque-type.model';

export interface TransactionBanque {
  transactionsbanqueReference: number;

  transactionsbanqueDateReelle: string;
  transactionsbanqueDateOperation: string;

  transactionsbanqueLibelle: string;
  transactionsbanqueMontant: number;
  transactionsbanqueCommentaire: string;
  transactionsbanqueNumeroDeType: string;

  releveId?: number;
  factureClientId?: number;
  factureFrsId?: number;
  etatId?: number;
  categorieId?: number;
  sensId?: number;
  typeId?: number;

  transactionsReleve?: TransactionsReleve | null;
  factureClient?: FactureClient | null;
  factureFrs?: FactureFrs | null;
  transactionEtat?: TransactionEtat | null;
  transactionCategorie?: TransactionCategorie | null;
  transactionSens?: TransactionSens | null;
  transactionBanqueType?: TransactionBanqueType | null;
}