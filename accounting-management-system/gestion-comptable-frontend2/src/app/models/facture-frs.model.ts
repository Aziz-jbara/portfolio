export interface FactureFrs {
  facturesFRSReference: number;
  facturesFRSNumero: string;
  facturesFRSMontantDevise: number;
  facturesFRSTauxChange: number;
  facturesFRSHT: number;
  facturesFRSTVAPourcentage: number;
  facturesFRSTVA: number;
  facturesFRSTTC: number;
  facturesFRSTimbre: number;
  facturesFRSTotalFacture: number;
  facturesFRSRSPourcentage: number;
  facturesFRSRSTotal: number;
  facturesFRSMontantAPayer: number;
  facturesFRSMontantPayee: number;

  fournisseurId?: number;
  deviseId?: number;
  rsAttestationId?: number;

  fournisseur?: Fournisseur | null;
  devise?: Devise | null;
  rsAttestationRecuperee?: RsAttestationRecuperee | null;
}

export interface Fournisseur {
  fournisseurReference: number;
  fournisseurRaisonSocial: string;
  fournisseurAdresse?: string;
  fournisseurCodePostal?: string;
  fournisseurTelephone?: string;
  fournisseurCodeTVA?: string;
  fournisseursGouvernorat?: string;
  fournisseursPays?: string;
  fournisseursFax?: string;
}

export interface Devise {
  deviseReference: number;
  deviseLibelle: string;
  deviseLibelleISO?: string;
  deviseSymbole: string;
}

export interface RsAttestationRecuperee {
  rsattestationrecupereReference: number;
  rsattestationrecupereLibelle: string;
}