/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.csrfdoublesubc;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author KALINDU
 */
@WebServlet(name = "SessionDemo", urlPatterns = {"/SessionDemo"})
public class SessionDemo extends HttpServlet {
    
    String Cookie_Value = null;
    String Cookie_Name = null;
    String token = null;
    
    
    public static Cookie getCookie(HttpServletRequest request, String name) {
        
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) { 
                    return cookie;
                }    
            }
        }
        return null;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

        } finally {
            out.close();
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
        try{
            Cookie tokenCookie = getCookie(request,"CSRF_Token");
            Cookie_Name = tokenCookie.getName();
            Cookie_Value = tokenCookie.getValue();
            System.out.println(Cookie_Name+" : "+Cookie_Value);
            token = request.getParameter("token");
            System.out.println("Token in DOM : "+token);
            if(token != null){
                if(token.equals(Cookie_Value)){
                    out.println("Form submitted successfully");
                }else{
                    out.println("Error occur while validating the CSRF token");
                }
            }else{
                out.println("CSRF token absent or value is null/empty");
            }
            
        }finally{
            out.close();
        }

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
