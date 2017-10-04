/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ae2017practica2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.servlet.http.HttpServletResponse;
import mx.edu.ittepic.ae2017practica2.utils.Message;
import mx.edu.ittepic.ae2017practica2.entities.Company;

/**
 *
 * @author Kervin
 */
@Stateless
public class EJBOperacionesCompany {

    @PersistenceContext
    private EntityManager em;

    public String getCompanies() {
        //Objeto que permite controlar la ejecución de una consulta
        //Nota: Se observa que la cadena enviada, corresponde a un instrucción de la entidad
        Query q = em.createNamedQuery("Company.findAll");
        List<Company> Companias;
        Companias = q.getResultList();  //Se consulta en la bd, retornando las compañias
        //Las clase que permite trabajar con JSON
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String result = gson.toJson(Companias);
        return result;
    }

    public String createCompany(String companyname, String neighborhood, String zipcode,
            String city, String country, String State, String region, String street, String streetnumber,
            String phone, String RFC, String logo) {
        //Clases para el tratamiento del JSON y mensajes
        GsonBuilder builder = new GsonBuilder();
        Gson json = builder.create();
        Message m = new Message();
//Se crea un objeto de la entidad, el cual será registrado en la bd 
//Ademas de asociar los parametros recibidos con su respectiva entidad
        Company c = new Company();
        c.setCompanyname(companyname);
        c.setNeighborhood(neighborhood);
        c.setZipcode(zipcode);
        c.setCity(city);
        c.setCountry(country);
        c.setState(State);
        c.setRegion(region);
        c.setStreet(street);
        c.setStreetnumber(streetnumber);
        c.setPhone(phone);
        c.setRfc(RFC);
        c.setLogo(logo);
        try {
            em.persist(c);
            em.flush();//Obligar al contenedor a guardar en la bd
            m.setCode(HttpServletResponse.SC_OK);
            m.setMessage("La compañia se creo correctamente con la clave " + c.getCompanyid());
            m.setDetail(json.toJson(c));
        } //Manejo de Errores
        catch (EntityExistsException ex) {
            m.setCode(HttpServletResponse.SC_BAD_REQUEST);
            m.setMessage("No se pudo guardar la compania, intente nuevamente");
            m.setDetail(ex.toString());
        } catch (IllegalArgumentException iex) {
            m.setCode(HttpServletResponse.SC_BAD_REQUEST);
            m.setMessage("Error al procesar el registro");
            m.setDetail(iex.toString());
        } catch (TransactionRequiredException tex) {
            m.setCode(HttpServletResponse.SC_BAD_REQUEST);
            m.setMessage("La operación requiere de un manejo particular");
            m.setDetail(tex.toString());
        }
        return json.toJson(m);
    }

    
        public String UpdateRol(
            int companyid,String companyname, String neighborhood, String zipcode,
            String city, String country, String State, String region, String street, 
            String streetnumber,String phone, String RFC, String logo) {
        GsonBuilder builder = new GsonBuilder();
        Gson json = builder.create();
        Message m = new Message();
        Company c = new Company();
        c.setCompanyid(companyid);
        c.setCompanyname(companyname);
        c.setNeighborhood(neighborhood);
        c.setZipcode(zipcode);
        c.setCity(city);
        c.setCountry(country);
        c.setState(State);
        c.setRegion(region);
        c.setStreet(street);
        c.setStreetnumber(streetnumber);
        c.setPhone(phone);
        c.setRfc(RFC);
        c.setLogo(logo);
        try {
            em.merge(c);
            //Obligar al contenedor a guardar en la bd
            em.flush();
            m.setCode(HttpServletResponse.SC_OK);
            m.setMessage("La compañia se actualizo correctamente");
            m.setDetail(json.toJson(c));
        } catch (IllegalArgumentException iex) {
            m.setCode(HttpServletResponse.SC_BAD_REQUEST);
            m.setMessage("Error al procesar el registro");
            m.setDetail(iex.toString());
        } catch (TransactionRequiredException tex) {
            m.setCode(HttpServletResponse.SC_BAD_REQUEST);
            m.setMessage("La operación requiere de un manejo particular");
            m.setDetail(tex.toString());
        }
        return json.toJson(m);
    }

}
