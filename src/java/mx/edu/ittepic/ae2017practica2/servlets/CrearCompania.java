/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ae2017practica2.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.ittepic.ae2017practica2.EJBOperacionesCompany;
import mx.edu.ittepic.ae2017practica2.utils.Message;

/**
 *
 * @author Kervin
 */
@WebServlet(name = "CrearCompania", urlPatterns = {"/CrearCompania"})
public class CrearCompania extends HttpServlet {
@EJB
private EJBOperacionesCompany ejbC;
private Message msn=new Message();
private GsonBuilder builder=new GsonBuilder();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("application/json;charset=UTF-8");
        PrintWriter p=response.getWriter();                
        msn.setCode(401);
        msn.setDetail("Don't enter");
        msn.setMessage("Not autorized method");
        
        Gson gson=builder.create();
        p.print(gson.toJson(msn));        
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("applicatio/json,charset=UTF-8");
        response.setHeader("Cache-control", "no-store");        
        PrintWriter p=response.getWriter();
        Gson gson = builder.create();        
        String companyname=request.getParameter("companyname"); 
        String neighborhood=request.getParameter("neighborhood");
        String zipcode=request.getParameter("zipcode");  String city=request.getParameter("city"); 
        String country=request.getParameter("country"); String state=request.getParameter("state");
        String region=request.getParameter("region"); String phone=request.getParameter("phone");
        String RFC=request.getParameter("RFC"); String logo=request.getParameter("logo");        
            String street=request.getParameter("street");
        String streetnumber=request.getParameter("streetnumber"); 
        if(companyname==null){
            msn.setCode(HttpServletResponse.SC_BAD_REQUEST);
                msn.setMessage("El nombre de la compania es obligatoria");
                msn.setDetail("La operación no se puede realizar");
                p.print(gson.toJson(msn));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }else{            
            p.write(ejbC.createCompany( companyname,  neighborhood,  zipcode,
             city,  country,  state,  region,  street,  streetnumber,
             phone,  RFC,  logo));
        }        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}