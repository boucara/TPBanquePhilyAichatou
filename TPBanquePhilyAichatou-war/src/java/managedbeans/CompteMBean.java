/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import bean.GestionnaireDeCompteBancaire;
import entities.OperationBancaire;
import entities.CompteBancaire;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

/**
 *
 * @author Mathieu
 * @author Tom Phily
 * @version edited
 */
@Named(value = "compteMBean")
@ViewScoped
public class CompteMBean implements Serializable {

    @EJB
    private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;
    private Collection<CompteBancaire> tousLesComptes;
    private CompteBancaire compte;
    private CompteBancaire compte1;
    private String nomProprio;
    private float solde;
    private float solde1;
    private float solde2;
    private CompteConverter compteConverter;
    private long idCompteBancaire;

    public long getIdCompteBancaire() {
        return idCompteBancaire;
    }

    public void setIdCompteBancaire(long idCompteBancaire) {
        this.idCompteBancaire = idCompteBancaire;
    }

    public CompteMBean() {
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public String getNomProprio() {
        return nomProprio;
    }

    public void setNomProprio(String nomProprio) {
        this.nomProprio = nomProprio;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public float getSolde1() {
        return solde1;
    }

    public void setSolde1(float solde1) {
        this.solde1 = solde1;
    }

    public float getSolde2() {
        return solde2;
    }

    public void setSolde2(float solde2) {
        this.solde2 = solde2;
    }

    public CompteBancaire getCompte1() {
        return compte1;
    }

    public Collection<CompteBancaire> getTousLesComptes() {
        return tousLesComptes;
    }

    public void setTousLesComptes(Collection<CompteBancaire> tousLesComptes) {
        this.tousLesComptes = tousLesComptes;
    }

    public void setCompte1(CompteBancaire compte1) {
        this.compte1 = compte1;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public Collection<OperationBancaire> getOperations() {
        System.out.println("### OBTENTION DE LA LISTE DES OPERATIONS ###");
        Collection<OperationBancaire> operations = gestionnaireDeCompteBancaire.getOperations(compte);
        return operations;
    }

    public String showOperation(CompteBancaire c) {
        return "details?idCompteBancaire="+c.getId()+"&faces-redirect=true";
    }

    public Collection<CompteBancaire> getComptes() {
        System.out.println("### OBTENTION DE LA LISTE DES COMPTES ###");
        //return gestionnaireDeCompteBancaire.getAllComptes();
        if (tousLesComptes == null || tousLesComptes.isEmpty()) {
            refresh();
        }
        return tousLesComptes;
    }

    public void refresh() {
        System.out.println("REFRESH");
        tousLesComptes = gestionnaireDeCompteBancaire.getAllComptes();
    }

    public String transferer() {
        System.out.println("### TRANSFERT ###");
        gestionnaireDeCompteBancaire.transferer(compte, compte1, solde);
        solde = 0;
       addFlashMessage( new FacesMessage(FacesMessage.SEVERITY_INFO, "Gestion des comptes", "Transfert effectué avec succès"));

        return "afficher?faces-redirect=true";
    }

    private void addFlashMessage(FacesMessage message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        flash.setRedirect(true);
        facesContext.addMessage(null, message);
    }

    public String list() {
        return "afficher?faces-redirect=true";
    }

    public String creer() {
        System.out.println("### CREATION COMPTE ###");
        gestionnaireDeCompteBancaire.creerCompte(new CompteBancaire(nomProprio, solde));
        solde = 0;
        nomProprio = "";
        addFlashMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Gestion des comptes", "Compte créé avec succès"));

        return "afficher";
    }

    public String depot() {
        System.out.println("### DEPOT ###");
        gestionnaireDeCompteBancaire.depot(compte, solde);
        solde = 0;
        addFlashMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Gestion des comptes", "Dépôt effectué avec succès"));

        return "afficher?faces-redirect=true";
    }

    public String depot1() {
        System.out.println("### DEPOT ###");
        gestionnaireDeCompteBancaire.depot(compte, solde2);
        solde2 = 0;
        addFlashMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Gestion des comptes", "Dépôt effectué avec succès"));

        return "afficher?faces-redirect=true";
    }

    public String retrait() {
        System.out.println("### RETRAIT ###");
        gestionnaireDeCompteBancaire.retrait(compte, solde);
        solde = 0;
        addFlashMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Gestion des comptes", "Retrait effectué avec succès"));

        return "afficher?faces-redirect=true";
    }

    public String retrait1() {
        System.out.println("### RETRAIT ###");
        gestionnaireDeCompteBancaire.retrait(compte, solde1);
        solde1 = 0;
        addFlashMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Gestion des comptes", "Retrait effectué avec succès"));

        return "afficher?faces-redirect=true";
    }

    public String supprimer(CompteBancaire c) {
        System.out.println("### SUPPRESSION D'UN COMPTE ###");
        gestionnaireDeCompteBancaire.supprimerCompte(c);
        addFlashMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Gestion des comptes", "Suppression effectuée avec succès"));

        return "afficher?faces-redirect=true";
    }
    
    public void loadCompteBancaire(){
        this.compte = gestionnaireDeCompteBancaire.getCompteById(idCompteBancaire);
    }
}
