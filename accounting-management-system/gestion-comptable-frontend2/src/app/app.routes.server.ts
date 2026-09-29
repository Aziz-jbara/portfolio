import { RenderMode, ServerRoute } from '@angular/ssr';

export const serverRoutes: ServerRoute[] = [
  { path: 'login', renderMode: RenderMode.Client },
  { path: 'register', renderMode: RenderMode.Client },
  { path: 'dashboard', renderMode: RenderMode.Client },
  { path: 'clients', renderMode: RenderMode.Client },
  { path: 'clients/new', renderMode: RenderMode.Client },
  { path: 'clients/edit/:id', renderMode: RenderMode.Client },
  { path: 'fournisseurs', renderMode: RenderMode.Client },
  { path: 'fournisseurs/new', renderMode: RenderMode.Client },
  { path: 'fournisseurs/edit/:id', renderMode: RenderMode.Client },
  { path: 'factures-client', renderMode: RenderMode.Client },
  { path: 'factures-client/new', renderMode: RenderMode.Client },
  { path: 'factures-client/edit/:id', renderMode: RenderMode.Client },
  { path: 'factures-frs', renderMode: RenderMode.Client },
  { path: 'factures-frs/new', renderMode: RenderMode.Client },
  { path: 'factures-frs/edit/:id', renderMode: RenderMode.Client },
  { path: 'transactions-banque', renderMode: RenderMode.Client },
  { path: 'transactions-banque/new', renderMode: RenderMode.Client },
  { path: 'transactions-banque/edit/:id', renderMode: RenderMode.Client },
  { path: 'transactions-caisse', renderMode: RenderMode.Client },
  { path: 'transactions-caisse/new', renderMode: RenderMode.Client },
  { path: 'transactions-caisse/edit/:id', renderMode: RenderMode.Client },
  { path: '**', renderMode: RenderMode.Client }
];