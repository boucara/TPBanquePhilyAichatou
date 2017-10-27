/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import bean.GestionnaireDeCompteBancaire;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author Mathieu
 */
@WebListener
public class InitApplication implements ServletContextListener {
    GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire = lookupGestionnaireBDBean();

 
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("##### REMPLISSAGE DE LA BD ! #####");
        gestionnaireDeCompteBancaire.creerComptesTest();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private GestionnaireDeCompteBancaire lookupGestionnaireBDBean() {
        try {
            Context c = new InitialContext();
            return (GestionnaireDeCompteBancaire) c.lookup("java:global/TPBanquePhilyAichatou/TPBanquePhilyAichatou-ejb/GestionnaireDeCompteBancaire!bean.GestionnaireDeCompteBancaire");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    } 
}
