/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author Mathieu
 * @author Tom Phily
 * @version edited
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "OperationBancaire.findByCompte", query = "SELECT o FROM CompteBancaire c join c.operations o where c.id = :id")
})
public class OperationBancaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    private float montant;

    public OperationBancaire() { }

    public OperationBancaire(String description, float montant) {
        this.description = description;
        this.montant = montant;
        this.date = new Date();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String today = formatter.format(date);     
        return today;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof OperationBancaire)) {
            return false;
        }
        OperationBancaire other = (OperationBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.OperationBancaire[ id=" + id + " ]";
    }
    
}
