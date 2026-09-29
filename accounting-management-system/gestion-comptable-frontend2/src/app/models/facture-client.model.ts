export interface FactureClient {
  facturesClientReference: number;
  facturesClientNumero: number;
  facturesClientMontantDevise: number;
  facturesClientTauxChange: number;
  facturesClientHT: number;
  facturesClientTVA: number;
  facturesClientTTC: number;
  facturesClientTimbre: number;
  facturesClientTotalFacture: number;
  facturesClientRSTVAPourcentage: number;
  facturesClientRSTVA: number;
  facturesClientRSPourcentage: number;
  facturesClientRS: number;
  facturesClientRSTotal: number;
  facturesClientMontantARecevoir: number;
  facturesClientMontantVerse: number;
  facturesClientMontantEcart: number;

  // Used by the form (create/update) to send IDs to the backend
  clientId?: number;
  deviseId?: number;
  categorieId?: number;

  // Used by the list/detail to display nested data from the backend
  client?: Client;
  devise?: Devise;
  categorie?: FactureClientCategorie;
}

export interface Client {
  clientsReference: number;
  clientsRaisonSocial: string;
  clientsAdresse: string;
  clientsCodePostal: string;
  clientsTelephone: string;
  clientsCodeTVA: string;
  clientsFax: string;
  clientsGouvernorat: string;
  clientsPays: string;
}

export interface Devise {
  deviseReference: number;
  deviseLibelle: string;
  deviseLibelleISO: string;
  deviseSymbole: string;
}

export interface FactureClientCategorie {
  factureclientcategorieReference: number;
  factureclientcategorieLibelle: string;
  factureclientcategorieDescription: string;
}
