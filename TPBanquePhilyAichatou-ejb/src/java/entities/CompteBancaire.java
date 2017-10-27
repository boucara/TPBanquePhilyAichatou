/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Mathieu
 */
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT c FROM CompteBancaire c ORDER BY c.id"),
    @NamedQuery(name = "CompteBancaire.findById", query = "SELECT c FROM CompteBancaire c WHERE c.id = :id"),
})
@Entity
public class CompteBancaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomProprio;
    private float solde;
    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    private Collection<OperationBancaire> operations = new ArrayList();

    public CompteBancaire() {
    }
    
    public CompteBancaire(String nomProprio, float solde){
        this.nomProprio = nomProprio;
        this.solde = solde;
        OperationBancaire op = new OperationBancaire("Création du compte", solde);
        addOperationBancaire(op);
    }
    
    public Collection<OperationBancaire> getOperations() {
        System.out.println("11");
        return operations;
    }

    public void setOperations(Collection<OperationBancaire> operations) {
        this.operations = operations;
    }

    public void addOperationBancaire(OperationBancaire o) {
        operations.add(o);
    }

    public Long getId() {
        return id;
    }

    public String getNomProprio() {
        return nomProprio;
    }

    public void setNomProprio(String nomProprio) {
        this.nomProprio = nomProprio;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void retirerArgent(float solde){
        this.solde = this.solde - solde;
        OperationBancaire op = new OperationBancaire("Débit", solde);
        addOperationBancaire(op);
    }
    
    public void ajouterArgent(float solde){
        this.solde = this.solde + solde;
        OperationBancaire op = new OperationBancaire("Crédit", solde);
        addOperationBancaire(op);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + " - " + nomProprio;
    }
    
}
