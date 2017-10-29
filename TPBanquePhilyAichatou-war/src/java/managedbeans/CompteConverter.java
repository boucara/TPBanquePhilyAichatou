package managedbeans;

import entities.CompteBancaire;
import bean.GestionnaireDeCompteBancaire;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Convertit de manière implicite un DiscountCode en String en utilisant la 
 * propriété discountCode. Les conversions seront faites automatiquement
 * dans les pages JSF ou dans les Backing beans, sans qu'on ait besoin de
 * spécifier un attribut converter = ou même d'instancier ce convertisseur
 * @author richard
 * @author Tom Phily
 * @version edited
 */
@FacesConverter(forClass = entities.CompteBancaire.class) 
public class CompteConverter implements Converter {
    GestionnaireDeCompteBancaire dcm = lookupGestionnaireDeCompteBancaireBean();
    // Note : on ne peut pas faire de @EJB ou @Inject dans un convertisseur. Ce
    // sera possible dans la prochaine version de JSF

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
            String id = value.substring(0, value.indexOf(" "));
            System.out.print(Long.getLong(id));
            return dcm.getCompteById(Long.parseLong(id));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((CompteBancaire) value).toString();
    }

    private GestionnaireDeCompteBancaire lookupGestionnaireDeCompteBancaireBean() {
        try {
            Context c = new InitialContext();
            return (GestionnaireDeCompteBancaire) c.lookup("java:global/TPBanquePhilyAichatou/TPBanquePhilyAichatou-ejb/GestionnaireDeCompteBancaire!bean.GestionnaireDeCompteBancaire");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
