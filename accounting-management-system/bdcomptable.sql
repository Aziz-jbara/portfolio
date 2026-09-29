-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le : mar. 29 sep. 2026 à 16:43
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bdcomptable`
--

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `clients_reference` int(11) NOT NULL,
  `clients_adresse` varchar(255) DEFAULT NULL,
  `clients_code_postal` varchar(255) DEFAULT NULL,
  `clients_codetva` varchar(255) DEFAULT NULL,
  `clients_fax` varchar(255) DEFAULT NULL,
  `clients_gouvernorat` varchar(255) DEFAULT NULL,
  `clients_pays` varchar(255) DEFAULT NULL,
  `clients_raison_social` varchar(255) DEFAULT NULL,
  `clients_telephone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`clients_reference`, `clients_adresse`, `clients_code_postal`, `clients_codetva`, `clients_fax`, `clients_gouvernorat`, `clients_pays`, `clients_raison_social`, `clients_telephone`) VALUES
(1, 'Avenue Habib Bourguiba, Immeuble Jasmin', '1001', '1728394/A/M/000', '+216 71 200 101', 'Tunis', 'Tunisie', 'Tunisie Digital Services', '+216 71 200 100'),
(2, 'Route de Gremda Km 4', '3027', '2837465/B/M/000', '+216 74 300 201', 'Sfax', 'Tunisie', 'Sfax Tech Solutions', '+216 74 300 200'),
(3, 'Rue Ibn Khaldoun, Centre Medina', '4000', '3948576/C/M/000', '+216 73 410 301', 'Sousse', 'Tunisie', 'Medina Consulting', '+216 73 410 300'),
(4, 'Zone Industrielle Mghira', '2082', '4859671/D/M/000', '+216 71 500 401', 'Ben Arous', 'Tunisie', 'Carthage Export', '+216 71 500 400'),
(5, 'Avenue de l’Environnement', '7000', '5960712/E/M/000', '+216 72 610 501', 'Bizerte', 'Tunisie', 'Nord Afrique Distribution', '+216 72 610 500'),
(6, 'Technopole El Ghazala', '2088', '6071823/F/M/000', '+216 70 720 601', 'Ariana', 'Tunisie', 'Smart ERP Tunisia', '+216 70 720 600');

-- --------------------------------------------------------

--
-- Structure de la table `devise`
--

CREATE TABLE `devise` (
  `devise_reference` int(11) NOT NULL,
  `devise_libelle` varchar(255) DEFAULT NULL,
  `devise_libelleiso` varchar(255) DEFAULT NULL,
  `devise_symbole` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `devise`
--

INSERT INTO `devise` (`devise_reference`, `devise_libelle`, `devise_libelleiso`, `devise_symbole`) VALUES
(1, 'Dinar Tunisien', 'TND', 'DT'),
(2, 'Euro', 'EUR', '€'),
(3, 'Dollar Américain', 'USD', '$');

-- --------------------------------------------------------

--
-- Structure de la table `factureclientcategorie`
--

CREATE TABLE `factureclientcategorie` (
  `factureclientcategorie_reference` int(11) NOT NULL,
  `factureclientcategorie_description` varchar(255) DEFAULT NULL,
  `factureclientcategorie_libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `factureclientcategorie`
--

INSERT INTO `factureclientcategorie` (`factureclientcategorie_reference`, `factureclientcategorie_description`, `factureclientcategorie_libelle`) VALUES
(1, 'Factures de vente de logiciels, licences et produits numériques', 'Vente'),
(2, 'Factures liées aux prestations de services', 'Services'),
(3, 'Contrats de maintenance et support technique', 'Maintenance'),
(4, 'Missions de conseil, audit et accompagnement', 'Conseil');

-- --------------------------------------------------------

--
-- Structure de la table `facturefrs`
--

CREATE TABLE `facturefrs` (
  `facturesfrsreference` int(11) NOT NULL,
  `facturesfrsht` double NOT NULL,
  `facturesfrsmontantapayer` double NOT NULL,
  `facturesfrsmontant_devise` double NOT NULL,
  `facturesfrsmontant_payee` double NOT NULL,
  `facturesfrsnumero` varchar(255) DEFAULT NULL,
  `facturesfrsrspourcentage` float NOT NULL,
  `facturesfrsrstotal` double NOT NULL,
  `facturesfrsttc` double NOT NULL,
  `facturesfrstva` float NOT NULL,
  `facturesfrstvapourcentage` float NOT NULL,
  `facturesfrstaux_change` float NOT NULL,
  `facturesfrstimbre` float NOT NULL,
  `facturesfrstotal_facture` double NOT NULL,
  `devise_id` int(11) DEFAULT NULL,
  `fournisseur_id` int(11) DEFAULT NULL,
  `rs_attestation_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `facturefrs`
--

INSERT INTO `facturefrs` (`facturesfrsreference`, `facturesfrsht`, `facturesfrsmontantapayer`, `facturesfrsmontant_devise`, `facturesfrsmontant_payee`, `facturesfrsnumero`, `facturesfrsrspourcentage`, `facturesfrsrstotal`, `facturesfrsttc`, `facturesfrstva`, `facturesfrstvapourcentage`, `facturesfrstaux_change`, `facturesfrstimbre`, `facturesfrstotal_facture`, `devise_id`, `fournisseur_id`, `rs_attestation_id`) VALUES
(1, 1800, 0, 1800, 2143, 'FF-2026-001', 0, 0, 2142, 342, 19, 1, 1, 2143, 1, 1, 4),
(2, 4200, 1998, 4200, 3000, 'FF-2026-002', 0, 0, 4997, 797, 19, 1, 1, 4998, 1, 2, 4),
(3, 1250, 0, 1250, 1488.5, 'FF-2026-003', 0, 0, 1487.5, 237.5, 19, 1, 1, 1488.5, 1, 3, 4),
(4, 2700, 3214, 820, 0, 'FF-2026-004', 0, 0, 3213, 513, 19, 3.29, 1, 3214, 2, 4, 4),
(5, 3600, 1285, 3600, 3000, 'FF-2026-005', 0, 0, 4284, 684, 19, 1, 1, 4285, 1, 5, 4),
(6, 980, 1167.2, 980, 0, 'FF-2026-006', 0, 0, 1166.2, 186.2, 19, 1, 1, 1167.2, 1, 1, 4);

-- --------------------------------------------------------

--
-- Structure de la table `facturesclient`
--

CREATE TABLE `facturesclient` (
  `factures_client_reference` int(11) NOT NULL,
  `factures_clientht` double NOT NULL,
  `factures_client_montantarecevoir` double NOT NULL,
  `factures_client_montant_devise` double NOT NULL,
  `factures_client_montant_ecart` double NOT NULL,
  `factures_client_montant_verse` double NOT NULL,
  `factures_client_numero` int(11) NOT NULL,
  `factures_clientrs` float NOT NULL,
  `factures_clientrspourcentage` float NOT NULL,
  `factures_clientrstva` float NOT NULL,
  `factures_clientrstvapourcentage` float NOT NULL,
  `factures_clientrstotal` double NOT NULL,
  `factures_clientttc` double NOT NULL,
  `factures_clienttva` float NOT NULL,
  `factures_client_taux_change` float NOT NULL,
  `factures_client_timbre` float NOT NULL,
  `factures_client_total_facture` double NOT NULL,
  `categorie_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `devise_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `facturesclient`
--

INSERT INTO `facturesclient` (`factures_client_reference`, `factures_clientht`, `factures_client_montantarecevoir`, `factures_client_montant_devise`, `factures_client_montant_ecart`, `factures_client_montant_verse`, `factures_client_numero`, `factures_clientrs`, `factures_clientrspourcentage`, `factures_clientrstva`, `factures_clientrstvapourcentage`, `factures_clientrstotal`, `factures_clientttc`, `factures_clienttva`, `factures_client_taux_change`, `factures_client_timbre`, `factures_client_total_facture`, `categorie_id`, `client_id`, `devise_id`) VALUES
(1, 5000, 0, 5000, 0, 5891.49, 2026001, 59.51, 1, 0, 0, 59.51, 5950, 950, 1, 1, 5951, 2, 1, 1),
(2, 3200, 1751.87, 3200, 1751.87, 2000, 2026002, 57.14, 1.5, 0, 0, 57.14, 3808, 608, 1, 1, 3809, 1, 2, 1),
(3, 7800, 9133.46, 7800, 9133.46, 0, 2026003, 148.22, 1.6, 0, 0, 148.22, 9282, 1482, 1, 1, 9283, 4, 3, 1),
(4, 4100, 0, 1250, 0, 4820.06, 2026004, 48.68, 1, 0, 0, 48.68, 4865, 765, 3.28, 1, 4866, 3, 4, 2),
(5, 2600, 1856.36, 2600, 1856.36, 1200, 2026005, 44.64, 1.5, 0, 0, 44.64, 3094, 494, 1, 1, 3095, 3, 5, 1),
(6, 6100, 3532.45, 1950, 3532.45, 3600, 2026006, 68.55, 1, 0, 0, 68.55, 7199, 1099, 3.13, 2, 7201, 1, 6, 3),
(7, 1500, 1776, 1500, 1776, 0, 2026007, 10, 0.56, 0, 0, 10, 1785, 285, 1, 1, 1786, 2, 1, 1),
(8, 950, 0, 950, 0, 1121.4, 2026008, 9.6, 0.85, 0, 0, 9.6, 1130, 180, 1, 1, 1131, 2, 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `fournisseur_reference` int(11) NOT NULL,
  `fournisseur_adresse` varchar(255) DEFAULT NULL,
  `fournisseur_code_postal` varchar(255) DEFAULT NULL,
  `fournisseur_codetva` varchar(255) DEFAULT NULL,
  `fournisseur_raison_social` varchar(255) DEFAULT NULL,
  `fournisseur_telephone` varchar(255) DEFAULT NULL,
  `fournisseurs_fax` varchar(255) DEFAULT NULL,
  `fournisseurs_gouvernorat` varchar(255) DEFAULT NULL,
  `fournisseurs_pays` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`fournisseur_reference`, `fournisseur_adresse`, `fournisseur_code_postal`, `fournisseur_codetva`, `fournisseur_raison_social`, `fournisseur_telephone`, `fournisseurs_fax`, `fournisseurs_gouvernorat`, `fournisseurs_pays`) VALUES
(1, 'Rue de l’Usine, Zone Charguia 1', '2035', '9988776/A/M/000', 'Global Office Supplies', '+216 71 800 100', '+216 71 800 101', 'Tunis', 'Tunisie'),
(2, 'Avenue de la République', '3000', '8877665/B/M/000', 'Tech Supplier Tunisia', '+216 74 810 200', '+216 74 810 201', 'Sfax', 'Tunisie'),
(3, 'Route de Monastir Km 2', '4000', '7766554/C/M/000', 'Transport Express', '+216 73 820 300', '+216 73 820 301', 'Sousse', 'Tunisie'),
(4, 'Lac 2, Rue du Cloud', '1053', '6655443/D/M/000', 'CloudPro Services', '+216 71 830 400', '+216 71 830 401', 'Tunis', 'Tunisie'),
(5, 'Zone Industrielle Kalaa Kebira', '4060', '5544332/E/M/000', 'Maintenance Plus', '+216 73 840 500', '+216 73 840 501', 'Sousse', 'Tunisie');

-- --------------------------------------------------------

--
-- Structure de la table `rsattestationrecuperee`
--

CREATE TABLE `rsattestationrecuperee` (
  `rsattestationrecupere_reference` int(11) NOT NULL,
  `rsattestationrecupere_libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `rsattestationrecuperee`
--

INSERT INTO `rsattestationrecuperee` (`rsattestationrecupere_reference`, `rsattestationrecupere_libelle`) VALUES
(1, 'RS 1%'),
(2, 'RS 1.5%'),
(3, 'RS 3%'),
(4, 'Sans retenue');

-- --------------------------------------------------------

--
-- Structure de la table `transactionbanquetypes`
--

CREATE TABLE `transactionbanquetypes` (
  `transactionbanquetypes_reference` int(11) NOT NULL,
  `transactionbanquetypes_description` varchar(255) DEFAULT NULL,
  `transactionbanquetypes_libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `transactionbanquetypes`
--

INSERT INTO `transactionbanquetypes` (`transactionbanquetypes_reference`, `transactionbanquetypes_description`, `transactionbanquetypes_libelle`) VALUES
(1, 'Virement bancaire national ou international', 'Virement'),
(2, 'Paiement ou encaissement par chèque', 'Chèque'),
(3, 'Versement ou retrait en espèces', 'Espèces'),
(4, 'Paiement par carte bancaire', 'Carte bancaire'),
(5, 'Prélèvement automatique', 'Prélèvement');

-- --------------------------------------------------------

--
-- Structure de la table `transactionsbanque`
--

CREATE TABLE `transactionsbanque` (
  `transactionsbanque_reference` int(11) NOT NULL,
  `transactionsbanque_commentaire` varchar(255) DEFAULT NULL,
  `transactionsbanque_date_operation` date DEFAULT NULL,
  `transactionsbanque_date_reelle` date DEFAULT NULL,
  `transactionsbanque_libelle` varchar(255) DEFAULT NULL,
  `transactionsbanque_montant` double NOT NULL,
  `transactionsbanque_numero_de_type` varchar(255) DEFAULT NULL,
  `facture_client_id` int(11) DEFAULT NULL,
  `facture_frs_id` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `categorie_id` int(11) DEFAULT NULL,
  `etat_id` int(11) DEFAULT NULL,
  `sens_id` int(11) DEFAULT NULL,
  `releve_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `transactionsbanque`
--

INSERT INTO `transactionsbanque` (`transactionsbanque_reference`, `transactionsbanque_commentaire`, `transactionsbanque_date_operation`, `transactionsbanque_date_reelle`, `transactionsbanque_libelle`, `transactionsbanque_montant`, `transactionsbanque_numero_de_type`, `facture_client_id`, `facture_frs_id`, `type_id`, `categorie_id`, `etat_id`, `sens_id`, `releve_id`) VALUES
(1, 'Encaissement total facture client 2026001', '2026-01-15', '2026-01-15', 'Virement client Tunisie Digital Services', 5891.49, 'VIR-2026-001', 1, NULL, 1, 1, 3, 1, 1),
(2, 'Paiement facture fournisseur FF-2026-001', '2026-01-20', '2026-01-20', 'Paiement Global Office Supplies', 2143, 'VIR-2026-002', NULL, 1, 1, 2, 3, 2, 1),
(3, 'Encaissement partiel facture client 2026002', '2026-02-10', '2026-02-10', 'Virement client Sfax Tech Solutions', 2000, 'VIR-2026-003', 2, NULL, 1, 1, 2, 1, 2),
(4, 'Paiement partiel facture fournisseur FF-2026-002', '2026-02-18', '2026-02-18', 'Paiement Tech Supplier Tunisia', 3000, 'CHQ-2026-001', NULL, 2, 2, 2, 2, 2, 2),
(5, 'Encaissement facture client en EUR convertie', '2026-03-07', '2026-03-07', 'Encaissement Carthage Export', 4820.06, 'VIR-2026-004', 4, NULL, 1, 1, 3, 1, 3),
(6, 'Paiement fournisseur Transport Express', '2026-03-25', '2026-03-25', 'Paiement Transport Express', 1488.5, 'VIR-2026-005', NULL, 3, 1, 2, 3, 2, 3),
(7, 'Encaissement partiel client Smart ERP Tunisia', '2026-04-12', '2026-04-12', 'Virement Smart ERP Tunisia', 2500, 'VIR-2026-006', 6, NULL, 1, 1, 2, 1, 4),
(8, 'Frais bancaires mensuels', '2026-04-30', '2026-04-30', 'Frais de tenue de compte', 45, 'FRAIS-2026-004', NULL, NULL, 5, 3, 3, 2, 4),
(9, 'Paiement partiel fournisseur Maintenance Plus', '2026-05-14', '2026-05-14', 'Paiement Maintenance Plus', 3000, 'VIR-2026-007', NULL, 5, 1, 2, 2, 2, 5),
(10, 'Encaissement facture client 2026008', '2026-05-21', '2026-05-21', 'Encaissement Sfax Tech Solutions', 1121.4, 'VIR-2026-008', 8, NULL, 1, 1, 3, 1, 5),
(11, 'Encaissement complémentaire client Smart ERP Tunisia', '2026-06-08', '2026-06-08', 'Virement Smart ERP complément', 1100, 'VIR-2026-009', 6, NULL, 1, 1, 2, 1, 6),
(12, 'Frais bancaires juin', '2026-06-30', '2026-06-30', 'Frais bancaires juin', 38, 'FRAIS-2026-006', NULL, NULL, 5, 3, 3, 2, 6);

-- --------------------------------------------------------

--
-- Structure de la table `transactionscaisse`
--

CREATE TABLE `transactionscaisse` (
  `transactionscaisse_reference` int(11) NOT NULL,
  `transactionscaisse_commentaire` varchar(255) DEFAULT NULL,
  `transactionscaisse_compte_courant_associe` int(11) NOT NULL,
  `transactionscaisse_date` date DEFAULT NULL,
  `transactionscaisse_libelle` varchar(255) DEFAULT NULL,
  `transactionscaisse_montant` double NOT NULL,
  `transactionscaisse_num_piece_comptable` varchar(255) DEFAULT NULL,
  `transactionscaisse_solde_progressif` double NOT NULL,
  `facture_client_id` int(11) DEFAULT NULL,
  `facture_frs_id` int(11) DEFAULT NULL,
  `categorie_id` int(11) DEFAULT NULL,
  `etat_id` int(11) DEFAULT NULL,
  `sens_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `transactionscaisse`
--

INSERT INTO `transactionscaisse` (`transactionscaisse_reference`, `transactionscaisse_commentaire`, `transactionscaisse_compte_courant_associe`, `transactionscaisse_date`, `transactionscaisse_libelle`, `transactionscaisse_montant`, `transactionscaisse_num_piece_comptable`, `transactionscaisse_solde_progressif`, `facture_client_id`, `facture_frs_id`, `categorie_id`, `etat_id`, `sens_id`) VALUES
(1, 'Apport initial caisse', 101, '2026-01-05', 'Alimentation caisse', 1000, 'CAI-2026-001', 1000, NULL, NULL, 4, 3, 1),
(2, 'Règlement espèces facture client 2026005', 101, '2026-02-14', 'Encaissement caisse Nord Afrique Distribution', 1200, 'CAI-2026-002', 2200, 5, NULL, 1, 3, 1),
(3, 'Petites fournitures bureau', 101, '2026-02-22', 'Achat fournitures caisse', 180, 'CAI-2026-003', 2020, NULL, NULL, 5, 2, 2),
(4, 'Règlement caisse fournisseur local', 101, '2026-03-18', 'Paiement espèces fournisseur', 350, 'CAI-2026-004', 1670, NULL, 6, 2, 2, 2),
(5, 'Frais déplacement commercial', 101, '2026-04-09', 'Frais déplacement', 260, 'CAI-2026-005', 1410, NULL, NULL, 5, 2, 2),
(6, 'Encaissement partiel client Smart ERP en espèces', 101, '2026-04-20', 'Encaissement espèces Smart ERP', 1000, 'CAI-2026-006', 2410, 6, NULL, 1, 3, 1),
(7, 'Règlement petits achats maintenance', 101, '2026-05-11', 'Achat maintenance caisse', 420, 'CAI-2026-007', 1990, NULL, NULL, 5, 2, 2),
(8, 'Retrait caisse pour dépôt banque', 101, '2026-06-15', 'Retrait caisse dépôt banque', 700, 'CAI-2026-008', 1290, NULL, NULL, 5, 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `transactionscategorie`
--

CREATE TABLE `transactionscategorie` (
  `transactionscategorie_reference` int(11) NOT NULL,
  `transactionscategorie_description` varchar(255) DEFAULT NULL,
  `transactionscategorie_libelle` varchar(255) DEFAULT NULL,
  `categorie_nature_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `transactionscategorie`
--

INSERT INTO `transactionscategorie` (`transactionscategorie_reference`, `transactionscategorie_description`, `transactionscategorie_libelle`, `categorie_nature_id`) VALUES
(1, 'Encaissement d’une facture client', 'Encaissement client', 1),
(2, 'Paiement d’une facture fournisseur', 'Paiement fournisseur', 2),
(3, 'Frais prélevés par la banque', 'Frais bancaires', 2),
(4, 'Apport ou alimentation de caisse', 'Apport caisse', 1),
(5, 'Sortie d’espèces depuis la caisse', 'Retrait caisse', 2),
(6, 'Correction ou ajustement comptable', 'Ajustement', 3);

-- --------------------------------------------------------

--
-- Structure de la table `transactionscategorienature`
--

CREATE TABLE `transactionscategorienature` (
  `transactionscategorienature_reference` int(11) NOT NULL,
  `transactionscategorienature_libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `transactionscategorienature`
--

INSERT INTO `transactionscategorienature` (`transactionscategorienature_reference`, `transactionscategorienature_libelle`) VALUES
(1, 'Recette'),
(2, 'Dépense'),
(3, 'Autre');

-- --------------------------------------------------------

--
-- Structure de la table `transactionsetat`
--

CREATE TABLE `transactionsetat` (
  `transactionsetat_reference` int(11) NOT NULL,
  `transactionsetat_description` varchar(255) DEFAULT NULL,
  `transactionsetat_libelle` varchar(255) DEFAULT NULL,
  `transactionsetat_ordre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `transactionsetat`
--

INSERT INTO `transactionsetat` (`transactionsetat_reference`, `transactionsetat_description`, `transactionsetat_libelle`, `transactionsetat_ordre`) VALUES
(1, 'Transaction créée mais non encore vérifiée', 'Non traitée', 1),
(2, 'Transaction validée comptablement', 'Validée', 2),
(3, 'Transaction rapprochée avec relevé bancaire ou caisse', 'Rapprochée', 3),
(4, 'Transaction annulée ou rejetée', 'Annulée', 4);

-- --------------------------------------------------------

--
-- Structure de la table `transactionsreleve`
--

CREATE TABLE `transactionsreleve` (
  `transactionsreleve_reference` int(11) NOT NULL,
  `transactionsreleve_annee` int(11) NOT NULL,
  `transactionsreleve_etat` int(11) NOT NULL,
  `transactionsreleve_mois` varchar(255) DEFAULT NULL,
  `transactionsreleve_montant` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `transactionsreleve`
--

INSERT INTO `transactionsreleve` (`transactionsreleve_reference`, `transactionsreleve_annee`, `transactionsreleve_etat`, `transactionsreleve_mois`, `transactionsreleve_montant`) VALUES
(1, 2026, 1, 'JANVIER', 18500),
(2, 2026, 1, 'FEVRIER', 22400),
(3, 2026, 1, 'MARS', 19850),
(4, 2026, 1, 'AVRIL', 26300),
(5, 2026, 1, 'MAI', 24120),
(6, 2026, 1, 'JUIN', 27890);

-- --------------------------------------------------------

--
-- Structure de la table `transactionssens`
--

CREATE TABLE `transactionssens` (
  `transactionssens_reference` int(11) NOT NULL,
  `transactionssens_libelle` varchar(255) DEFAULT NULL,
  `transactionssens_description` varchar(255) DEFAULT NULL,
  `transactionsnature_description` varchar(255) DEFAULT NULL,
  `transactionsnature_libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `transactionssens`
--

INSERT INTO `transactionssens` (`transactionssens_reference`, `transactionssens_libelle`, `transactionssens_description`, `transactionsnature_description`, `transactionsnature_libelle`) VALUES
(1, 'Entrée', 'Argent entrant dans l’entreprise', NULL, NULL),
(2, 'Sortie', 'Argent sortant de l’entreprise', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `tvapourcentage`
--

CREATE TABLE `tvapourcentage` (
  `tvapourcentage_reference` int(11) NOT NULL,
  `tvapourcentage_valeur` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `tvapourcentage`
--

INSERT INTO `tvapourcentage` (`tvapourcentage_reference`, `tvapourcentage_valeur`) VALUES
(1, 0),
(2, 7),
(3, 13),
(4, 19);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `utilisateur_reference` int(11) NOT NULL,
  `utilisateur_email` varchar(255) DEFAULT NULL,
  `utilisateur_mot_de_passe` varchar(255) DEFAULT NULL,
  `utilisateur_nom` varchar(255) DEFAULT NULL,
  `utilisateur_prenom` varchar(255) DEFAULT NULL,
  `utilisateur_role` enum('ADMIN','USER') DEFAULT NULL,
  `utilisateur_telephone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`utilisateur_reference`, `utilisateur_email`, `utilisateur_mot_de_passe`, `utilisateur_nom`, `utilisateur_prenom`, `utilisateur_role`, `utilisateur_telephone`) VALUES
(1, 'azizjbara1235@gmail.com', '$2a$10$KVyJyqBZp2Uy2JAr1tHJCe.OSdlJEp9o3N.Ap0Q9X..WVOK8wAQSS', 'aziz', 'jbara', 'USER', '52033418');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`clients_reference`);

--
-- Index pour la table `devise`
--
ALTER TABLE `devise`
  ADD PRIMARY KEY (`devise_reference`);

--
-- Index pour la table `factureclientcategorie`
--
ALTER TABLE `factureclientcategorie`
  ADD PRIMARY KEY (`factureclientcategorie_reference`);

--
-- Index pour la table `facturefrs`
--
ALTER TABLE `facturefrs`
  ADD PRIMARY KEY (`facturesfrsreference`),
  ADD KEY `FKguhmsg9js5jasgshgoft5yml0` (`devise_id`),
  ADD KEY `FKgdhi7mq9rsvrqtv9hetn6lvr2` (`fournisseur_id`),
  ADD KEY `FK4qk868ultptvdmtg1rwkryvmd` (`rs_attestation_id`);

--
-- Index pour la table `facturesclient`
--
ALTER TABLE `facturesclient`
  ADD PRIMARY KEY (`factures_client_reference`),
  ADD KEY `FK8seef370rve8urkt1ewlrkxf1` (`categorie_id`),
  ADD KEY `FK73swxyi89yoys7nwa6c4lxv62` (`client_id`),
  ADD KEY `FKq2yokswhmry3gwt0s30yfjqay` (`devise_id`);

--
-- Index pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`fournisseur_reference`);

--
-- Index pour la table `rsattestationrecuperee`
--
ALTER TABLE `rsattestationrecuperee`
  ADD PRIMARY KEY (`rsattestationrecupere_reference`);

--
-- Index pour la table `transactionbanquetypes`
--
ALTER TABLE `transactionbanquetypes`
  ADD PRIMARY KEY (`transactionbanquetypes_reference`);

--
-- Index pour la table `transactionsbanque`
--
ALTER TABLE `transactionsbanque`
  ADD PRIMARY KEY (`transactionsbanque_reference`),
  ADD KEY `FKoeuq57wcu0rcp0wvyhspbh9ig` (`facture_client_id`),
  ADD KEY `FKffaqp2a85ufdtidmtv808nkb0` (`facture_frs_id`),
  ADD KEY `FKgx2et9a8inwiebgcl383k2tum` (`type_id`),
  ADD KEY `FK814htrbcs1x2tfqlaheeyn31` (`categorie_id`),
  ADD KEY `FK81rpiqmgu8pisl89gfkje9v38` (`etat_id`),
  ADD KEY `FKt61obcrpq8pxlyhqb5vf5c88m` (`releve_id`);

--
-- Index pour la table `transactionscaisse`
--
ALTER TABLE `transactionscaisse`
  ADD PRIMARY KEY (`transactionscaisse_reference`),
  ADD KEY `FK3uxitlq4csbegitjknupphvsw` (`facture_client_id`),
  ADD KEY `FK65ogqd9qagoitvdpbfph3g059` (`facture_frs_id`),
  ADD KEY `FKk9a23s78tnglpkxgejg33fjxu` (`categorie_id`),
  ADD KEY `FK2mwtss4m4hbk6a87al685s5xa` (`etat_id`);

--
-- Index pour la table `transactionscategorie`
--
ALTER TABLE `transactionscategorie`
  ADD PRIMARY KEY (`transactionscategorie_reference`),
  ADD KEY `FKkya0vcdi38g30s2q556mdi19m` (`categorie_nature_id`);

--
-- Index pour la table `transactionscategorienature`
--
ALTER TABLE `transactionscategorienature`
  ADD PRIMARY KEY (`transactionscategorienature_reference`);

--
-- Index pour la table `transactionsetat`
--
ALTER TABLE `transactionsetat`
  ADD PRIMARY KEY (`transactionsetat_reference`);

--
-- Index pour la table `transactionsreleve`
--
ALTER TABLE `transactionsreleve`
  ADD PRIMARY KEY (`transactionsreleve_reference`);

--
-- Index pour la table `transactionssens`
--
ALTER TABLE `transactionssens`
  ADD PRIMARY KEY (`transactionssens_reference`);

--
-- Index pour la table `tvapourcentage`
--
ALTER TABLE `tvapourcentage`
  ADD PRIMARY KEY (`tvapourcentage_reference`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`utilisateur_reference`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `clients_reference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `devise`
--
ALTER TABLE `devise`
  MODIFY `devise_reference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `factureclientcategorie`
--
ALTER TABLE `factureclientcategorie`
  MODIFY `factureclientcategorie_reference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `facturefrs`
--
ALTER TABLE `facturefrs`
  MODIFY `facturesfrsreference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `facturesclient`
--
ALTER TABLE `facturesclient`
  MODIFY `factures_client_reference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `fournisseur_reference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `rsattestationrecuperee`
--
ALTER TABLE `rsattestationrecuperee`
  MODIFY `rsattestationrecupere_reference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `transactionbanquetypes`
--
ALTER TABLE `transactionbanquetypes`
  MODIFY `transactionbanquetypes_reference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `transactionsbanque`
--
ALTER TABLE `transactionsbanque`
  MODIFY `transactionsbanque_reference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `transactionscaisse`
--
ALTER TABLE `transactionscaisse`
  MODIFY `transactionscaisse_reference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `transactionscategorie`
--
ALTER TABLE `transactionscategorie`
  MODIFY `transactionscategorie_reference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `transactionscategorienature`
--
ALTER TABLE `transactionscategorienature`
  MODIFY `transactionscategorienature_reference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `transactionsetat`
--
ALTER TABLE `transactionsetat`
  MODIFY `transactionsetat_reference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `transactionsreleve`
--
ALTER TABLE `transactionsreleve`
  MODIFY `transactionsreleve_reference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `transactionssens`
--
ALTER TABLE `transactionssens`
  MODIFY `transactionssens_reference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `tvapourcentage`
--
ALTER TABLE `tvapourcentage`
  MODIFY `tvapourcentage_reference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `utilisateur_reference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `facturefrs`
--
ALTER TABLE `facturefrs`
  ADD CONSTRAINT `FK4qk868ultptvdmtg1rwkryvmd` FOREIGN KEY (`rs_attestation_id`) REFERENCES `rsattestationrecuperee` (`rsattestationrecupere_reference`),
  ADD CONSTRAINT `FKgdhi7mq9rsvrqtv9hetn6lvr2` FOREIGN KEY (`fournisseur_id`) REFERENCES `fournisseur` (`fournisseur_reference`),
  ADD CONSTRAINT `FKguhmsg9js5jasgshgoft5yml0` FOREIGN KEY (`devise_id`) REFERENCES `devise` (`devise_reference`);

--
-- Contraintes pour la table `facturesclient`
--
ALTER TABLE `facturesclient`
  ADD CONSTRAINT `FK73swxyi89yoys7nwa6c4lxv62` FOREIGN KEY (`client_id`) REFERENCES `clients` (`clients_reference`),
  ADD CONSTRAINT `FK8seef370rve8urkt1ewlrkxf1` FOREIGN KEY (`categorie_id`) REFERENCES `factureclientcategorie` (`factureclientcategorie_reference`),
  ADD CONSTRAINT `FKq2yokswhmry3gwt0s30yfjqay` FOREIGN KEY (`devise_id`) REFERENCES `devise` (`devise_reference`);

--
-- Contraintes pour la table `transactionsbanque`
--
ALTER TABLE `transactionsbanque`
  ADD CONSTRAINT `FK814htrbcs1x2tfqlaheeyn31` FOREIGN KEY (`categorie_id`) REFERENCES `transactionscategorie` (`transactionscategorie_reference`),
  ADD CONSTRAINT `FK81rpiqmgu8pisl89gfkje9v38` FOREIGN KEY (`etat_id`) REFERENCES `transactionsetat` (`transactionsetat_reference`),
  ADD CONSTRAINT `FKffaqp2a85ufdtidmtv808nkb0` FOREIGN KEY (`facture_frs_id`) REFERENCES `facturefrs` (`facturesfrsreference`),
  ADD CONSTRAINT `FKgx2et9a8inwiebgcl383k2tum` FOREIGN KEY (`type_id`) REFERENCES `transactionbanquetypes` (`transactionbanquetypes_reference`),
  ADD CONSTRAINT `FKoeuq57wcu0rcp0wvyhspbh9ig` FOREIGN KEY (`facture_client_id`) REFERENCES `facturesclient` (`factures_client_reference`),
  ADD CONSTRAINT `FKt61obcrpq8pxlyhqb5vf5c88m` FOREIGN KEY (`releve_id`) REFERENCES `transactionsreleve` (`transactionsreleve_reference`);

--
-- Contraintes pour la table `transactionscaisse`
--
ALTER TABLE `transactionscaisse`
  ADD CONSTRAINT `FK2mwtss4m4hbk6a87al685s5xa` FOREIGN KEY (`etat_id`) REFERENCES `transactionsetat` (`transactionsetat_reference`),
  ADD CONSTRAINT `FK3uxitlq4csbegitjknupphvsw` FOREIGN KEY (`facture_client_id`) REFERENCES `facturesclient` (`factures_client_reference`),
  ADD CONSTRAINT `FK65ogqd9qagoitvdpbfph3g059` FOREIGN KEY (`facture_frs_id`) REFERENCES `facturefrs` (`facturesfrsreference`),
  ADD CONSTRAINT `FKk9a23s78tnglpkxgejg33fjxu` FOREIGN KEY (`categorie_id`) REFERENCES `transactionscategorie` (`transactionscategorie_reference`);

--
-- Contraintes pour la table `transactionscategorie`
--
ALTER TABLE `transactionscategorie`
  ADD CONSTRAINT `FKkya0vcdi38g30s2q556mdi19m` FOREIGN KEY (`categorie_nature_id`) REFERENCES `transactionscategorienature` (`transactionscategorienature_reference`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
