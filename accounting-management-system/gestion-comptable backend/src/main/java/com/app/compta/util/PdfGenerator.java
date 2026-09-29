package com.app.compta.util;

import com.app.compta.entity.FactureClient;
import com.app.compta.entity.FactureFrs;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class PdfGenerator {

    private static final String REPORT_PATH_CLIENT = "jasper/factureclient.jrxml";
    private static final String REPORT_PATH_FRS    = "jasper/facturefrs.jrxml";

    private volatile JasperReport compiledReportClient;
    private volatile JasperReport compiledReportFrs;

    // -----------------------------------------------------------------------
    // FactureClient
    // -----------------------------------------------------------------------

    public byte[] generateFactureClientPdf(FactureClient facture) {
        try {
            JasperReport report = getCompiledReport(REPORT_PATH_CLIENT, true);
            Map<String, Object> params = buildClientParameters(facture);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new RuntimeException("Error generating FactureClient PDF", e);
        }
    }

    // -----------------------------------------------------------------------
    // FactureFrs
    // -----------------------------------------------------------------------

    public byte[] generateFactureFrsPdf(FactureFrs facture) {
        try {
            JasperReport report = getCompiledReport(REPORT_PATH_FRS, false);
            Map<String, Object> params = buildFrsParameters(facture);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, new JREmptyDataSource(1));
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new RuntimeException("Error generating FactureFrs PDF", e);
        }
    }

    // -----------------------------------------------------------------------
    // Shared compile helper
    // -----------------------------------------------------------------------

    private JasperReport getCompiledReport(String path, boolean isClient) throws Exception {
        if (isClient) {
            if (compiledReportClient == null) {
                synchronized (this) {
                    if (compiledReportClient == null) {
                        compiledReportClient = compile(path);
                    }
                }
            }
            return compiledReportClient;
        } else {
            if (compiledReportFrs == null) {
                synchronized (this) {
                    if (compiledReportFrs == null) {
                        compiledReportFrs = compile(path);
                    }
                }
            }
            return compiledReportFrs;
        }
    }

    private JasperReport compile(String path) throws Exception {
        ClassPathResource resource = new ClassPathResource(path);
        if (!resource.exists()) {
            throw new IllegalStateException(
                    "Report not found on classpath: " + path
                            + " (place the .jrxml file under src/main/resources/jasper/)");
        }
        try (InputStream is = resource.getInputStream()) {
            String jrxml = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            return JasperCompileManager.compileReport(
                    new ByteArrayInputStream(jrxml.getBytes(StandardCharsets.UTF_8)));
        }
    }

    // -----------------------------------------------------------------------
    // Parameter builders
    // -----------------------------------------------------------------------

    private Map<String, Object> buildClientParameters(FactureClient f) {
        Map<String, Object> params = new HashMap<>();
        params.put("facturesClientReference", f.getFacturesClientReference());
        params.put("facturesClientNumero", f.getFacturesClientNumero());
        params.put("facturesClientMontantDevise", f.getFacturesClientMontantDevise());
        params.put("facturesClientTauxChange", f.getFacturesClientTauxChange());
        params.put("facturesClientHT", f.getFacturesClientHT());
        params.put("facturesClientTVA", f.getFacturesClientTVA());
        params.put("facturesClientTTC", f.getFacturesClientTTC());
        params.put("facturesClientTimbre", f.getFacturesClientTimbre());
        params.put("facturesClientTotalFacture", f.getFacturesClientTotalFacture());
        params.put("facturesClientRSTVAPourcentage", f.getFacturesClientRSTVAPourcentage());
        params.put("facturesClientRSTVA", f.getFacturesClientRSTVA());
        params.put("facturesClientRSPourcentage", f.getFacturesClientRSPourcentage());
        params.put("facturesClientRS", f.getFacturesClientRS());
        params.put("facturesClientRSTotal", f.getFacturesClientRSTotal());
        params.put("facturesClientMontantARecevoir", f.getFacturesClientMontantARecevoir());
        params.put("facturesClientMontantVerse", f.getFacturesClientMontantVerse());
        params.put("facturesClientMontantEcart", f.getFacturesClientMontantEcart());
        params.put("clientNom", clientNom(f));
        params.put("clientReference", clientReference(f));
        params.put("deviseCode", deviseCode(f));
        params.put("categorieNom", categorieNom(f));
        return params;
    }

    private Map<String, Object> buildFrsParameters(FactureFrs f) {
        Map<String, Object> params = new HashMap<>();
        params.put("facturesFRSReference", f.getFacturesFRSReference());
        params.put("facturesFRSNumero", f.getFacturesFRSNumero());
        params.put("facturesFRSMontantDevise", f.getFacturesFRSMontantDevise());
        params.put("facturesFRSTauxChange", f.getFacturesFRSTauxChange());
        params.put("facturesFRSHT", f.getFacturesFRSHT());
        params.put("facturesFRSTVAPourcentage", f.getFacturesFRSTVAPourcentage());
        params.put("facturesFRSTVA", f.getFacturesFRSTVA());
        params.put("facturesFRSTTC", f.getFacturesFRSTTC());
        params.put("facturesFRSTimbre", f.getFacturesFRSTimbre());
        params.put("facturesFRSTotalFacture", f.getFacturesFRSTotalFacture());
        params.put("facturesFRSRSPourcentage", f.getFacturesFRSRSPourcentage());
        params.put("facturesFRSRSTotal", f.getFacturesFRSRSTotal());
        params.put("facturesFRSMontantAPayer", f.getFacturesFRSMontantAPayer());
        params.put("facturesFRSMontantPayee", f.getFacturesFRSMontantPayee());
        params.put("fournisseurNom", fournisseurNom(f));
        params.put("fournisseurReference", fournisseurReference(f));
        params.put("deviseCode", frsDeviseCode(f));
        params.put("rsAttestationLibelle", rsAttestationLibelle(f));
        return params;
    }

    // -----------------------------------------------------------------------
    // FactureClient helpers
    // -----------------------------------------------------------------------

    private Integer clientReference(FactureClient f) {
        if (f.getClient() == null) return null;
        return f.getClient().getClientsReference();
    }

    private String clientNom(FactureClient f) {
        if (f.getClient() == null) return "-";
        String nom = f.getClient().getClientsRaisonSocial();
        return nom != null && !nom.isBlank() ? nom : "-";
    }

    private String deviseCode(FactureClient f) {
        if (f.getDevise() == null) return "-";
        String code = f.getDevise().getDeviseLibelleISO();
        return code != null && !code.isBlank() ? code : "-";
    }

    private String categorieNom(FactureClient f) {
        if (f.getCategorie() == null) return "-";
        String nom = f.getCategorie().getFactureclientcategorieLibelle();
        return nom != null && !nom.isBlank() ? nom : "-";
    }

    // -----------------------------------------------------------------------
    // FactureFrs helpers
    // -----------------------------------------------------------------------

    private Integer fournisseurReference(FactureFrs f) {
        if (f.getFournisseur() == null) return null;
        return f.getFournisseur().getFournisseurReference();
    }

    private String fournisseurNom(FactureFrs f) {
        if (f.getFournisseur() == null) return "-";
        String nom = f.getFournisseur().getFournisseurRaisonSocial();
        return nom != null && !nom.isBlank() ? nom : "-";
    }

    private String frsDeviseCode(FactureFrs f) {
        if (f.getDevise() == null) return "-";
        String code = f.getDevise().getDeviseLibelleISO();
        return code != null && !code.isBlank() ? code : "-";
    }

    private String rsAttestationLibelle(FactureFrs f) {
        if (f.getRsAttestationRecuperee() == null) return "-";
        String lib = f.getRsAttestationRecuperee().getRsattestationrecupereLibelle();
        return lib != null && !lib.isBlank() ? lib : "-";
    }
}