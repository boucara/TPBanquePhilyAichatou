/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.CompteBancaire;
import entities.OperationBancaire;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Tom Phily
 * @author Aichatou Traore
 */
@Stateless
@LocalBean
public class GestionnaireDeCompteBancaire {

    @PersistenceContext
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void creerCompte(CompteBancaire c) {
        this.persist(c);
    }

    public Collection<CompteBancaire> getAllComptes() {
        Query query = em.createNamedQuery("CompteBancaire.findAll");
        return query.getResultList();
    }
    
    public CompteBancaire getCompteById(long id) {
        return (CompteBancaire) em.find(CompteBancaire.class, id);
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public void creerComptesTest() {
        creerCompte(new CompteBancaire("John Lennon", 150000));
        creerCompte(new CompteBancaire("Cartney", 950000));
        creerCompte(new CompteBancaire("Ringo Starr", 20000));
        creerCompte(new CompteBancaire("Georges Harrisson", 100000));
        creerCompte(new CompteBancaire("Phily tom", 100000));
        creerCompte(new CompteBancaire("Georges", 100000));
        creerCompte(new CompteBancaire("Aichatou", 100000));
        creerCompte(new CompteBancaire("Traore", 100000));
        creerCompte(new CompteBancaire("Abdoul", 100000));
        creerCompte(new CompteBancaire("Marine", 100000));
        creerCompte(new CompteBancaire("Mami", 100000));
        creerCompte(new CompteBancaire("Malouk", 100000));
        creerCompte(new CompteBancaire("Matt", 100000));
    }
    
    public void transferer(CompteBancaire c1, CompteBancaire c2, float montant){
        c1.retirerArgent(montant);
        OperationBancaire op = new OperationBancaire("Transfert en provenance de " + c1.getNomProprio()+" à "+c2.getNomProprio(), montant);
        c1.addOperationBancaire(op);
        c2.addOperationBancaire(op);
        c2.ajouterArgent(montant);
        em.merge(c2);
        em.merge(c1);
    }
    
    public void depot(CompteBancaire c1, float montant){
        c1.ajouterArgent(montant);
        OperationBancaire op = new OperationBancaire("Dépot d'argent.", montant);
        c1.addOperationBancaire(op);
        em.merge(c1);
    }
    
    public void retrait(CompteBancaire c1, float montant){
        c1.retirerArgent(montant);
        OperationBancaire op = new OperationBancaire("Retrait d'argent.", montant);
        c1.addOperationBancaire(op);
        em.merge(c1);
    }
    
    public void supprimerCompte(CompteBancaire c1){
        em.remove(em.merge(c1));
    }

    public Collection<OperationBancaire> getOperations(CompteBancaire c) {
        System.out.println("#### AFFICHAGE DETAILS DU COMPTE : "+c.toString()+ " ####");
        Query query = em.createNamedQuery("OperationBancaire.findByCompte").setParameter("id", c.getId());
        return query.getResultList();
    }
}
