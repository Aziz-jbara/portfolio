import { Routes } from '@angular/router';

import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [

  { path: '', redirectTo: '/login', pathMatch: 'full' },

  {
    path: 'login',
    loadComponent: () =>
      import('./components/login/login.component').then(m => m.LoginComponent)
  },

  {
    path: 'register',
    loadComponent: () =>
      import('./components/register/register.component').then(m => m.RegisterComponent)
  },

  {
    path: 'dashboard',
    loadComponent: () =>
      import('./components/dashboard/dashboard.component').then(m => m.DashboardComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'clients',
    loadComponent: () =>
      import('./components/clients/clients-list/clients-list.component').then(m => m.ClientsListComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'clients/new',
    loadComponent: () =>
      import('./components/clients/client-form/client-form.component').then(m => m.ClientFormComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'clients/edit/:id',
    loadComponent: () =>
      import('./components/clients/client-form/client-form.component').then(m => m.ClientFormComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'fournisseurs',
    loadComponent: () =>
      import('./components/fournisseurs/fournisseurs-list/fournisseurs-list.component').then(m => m.FournisseursListComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'fournisseurs/new',
    loadComponent: () =>
      import('./components/fournisseurs/fournisseur-form/fournisseur-form.component').then(m => m.FournisseurFormComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'fournisseurs/edit/:id',
    loadComponent: () =>
      import('./components/fournisseurs/fournisseur-form/fournisseur-form.component').then(m => m.FournisseurFormComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'factures-client',
    loadComponent: () =>
      import('./components/factures-client/factures-client-list/factures-client-list.component').then(m => m.FacturesClientListComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'factures-client/new',
    loadComponent: () =>
      import('./components/factures-client/facture-client-form/facture-client-form.component').then(m => m.FactureClientFormComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'factures-client/edit/:id',
    loadComponent: () =>
      import('./components/factures-client/facture-client-form/facture-client-form.component').then(m => m.FactureClientFormComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'factures-frs',
    loadComponent: () =>
      import('./components/factures-frs/factures-frs-list/factures-frs-list.component').then(m => m.FacturesFrsListComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'factures-frs/new',
    loadComponent: () =>
      import('./components/factures-frs/facture-frs-form/facture-frs-form.component').then(m => m.FactureFrsFormComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'factures-frs/edit/:id',
    loadComponent: () =>
      import('./components/factures-frs/facture-frs-form/facture-frs-form.component').then(m => m.FactureFrsFormComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'transactions-banque',
    loadComponent: () =>
      import('./components/transactions-banque/transactions-banque-list/transactions-banque-list.component').then(m => m.TransactionsBanqueListComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'transactions-banque/new',
    loadComponent: () =>
      import('./components/transactions-banque/transaction-banque-form/transaction-banque-form.component').then(m => m.TransactionBanqueFormComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'transactions-banque/edit/:id',
    loadComponent: () =>
      import('./components/transactions-banque/transaction-banque-form/transaction-banque-form.component').then(m => m.TransactionBanqueFormComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'transactions-caisse',
    loadComponent: () =>
      import('./components/transactions-caisse/transactions-caisse-list/transactions-caisse-list.component').then(m => m.TransactionsCaisseListComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'transactions-caisse/new',
    loadComponent: () =>
      import('./components/transactions-caisse/transaction-caisse-form/transaction-caisse-form.component').then(m => m.TransactionCaisseFormComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'transactions-caisse/edit/:id',
    loadComponent: () =>
      import('./components/transactions-caisse/transaction-caisse-form/transaction-caisse-form.component').then(m => m.TransactionCaisseFormComponent),
    canActivate: [AuthGuard]
  },

  { path: '**', redirectTo: '/login' }
];