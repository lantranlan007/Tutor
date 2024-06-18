----- File: Hello.java -----
/***********************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved
 * 
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 ***********************************************************/
package de.muellerbruehl.tinycalculator;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mmueller
 */
@WebServlet(name = "Hello", urlPatterns = {"/Hello"})
public class Hello extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Hello</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Hello at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        processRequest(request, response);
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


----- File: TinyCalculator.java -----
/***********************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved
 * 
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 ***********************************************************/
package de.muellerbruehl.tinycalculator;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author mmueller
 */
@Named
@RequestScoped
public class TinyCalculator implements Serializable{

    private static final Logger _logger = Logger.getLogger("TinyCalculator");

    public TinyCalculator() {
        _logger.log(Level.INFO, "ctor TinyCalculator");
    }
    private double _param1;
    private double _param2;
    private double _result;

    public double getParam1() {
        return _param1;
    }

    public void setParam1(double param1) {
        _param1 = param1;
    }

    public double getParam2() {
        return _param2;
    }

    public void setParam2(double param2) {
        _param2 = param2;
    }

    public double getResult() {
        return _result;
    }

    public void setResult(double result) {
        _result = result;
    }

    public String add() {
        _result = _param1 + _param2;
        return "";
    }

    public String subtract() {
        _result = _param1 - _param2;
        return "";
    }

    public String multiply() {
        _result = _param1 * _param2;
        return "";
    }

    public String divide() {
        _result = _param1 / _param2;
        return "";
    }

    public String divideInt(int p1, int p2) {
        _result = p1 / p2;
        return "";
    }
}


----- File: Util.java -----
/***********************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved
 * 
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 ***********************************************************/
package de.muellerbruehl.tinycalculator;

import com.sun.faces.facelets.compiler.UIInstructions;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author mmueller
 */
@Named
@RequestScoped
public class Util {

    private static final Logger _logger = Logger.getLogger("Util");

    /**
     * printTree might be used within an action of a button
     * As required for an action, it returns a String
     * @return ""
     */
    public String printTree() {
        UIViewRoot root = FacesContext.getCurrentInstance().getViewRoot();
        printTree(root, 0);
        return "";
    }

    private void printTree(UIComponent element, int level) {
        logElement(level, element);
        for (UIComponent child : element.getChildren()) {
            printTree(child, level + 1);
        }
    }

    private void logElement(int level, UIComponent element) {
        String out = "";
        for (int i = 0; i < level; i++) {
            out += "----";
        }
        out += element.getClass().getSimpleName()
                + " - " + element.getFamily()
                + " - " + element.getRendererType() + element;
        _logger.log(Level.INFO, out);
        if (element instanceof UIInstructions){
            UIInstructions instructions = (UIInstructions) element;
            int c = instructions.getChildCount();
        }
    }

}


----- File: HtmlFriendlyCalc.xhtml -----
<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:jsf="http://xmlns.jcp.org/jsf"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets">
    <head>
        <title>TinyCalculator</title>
    </head>
    <body>
        <h1>TinyCalculator</h1>
        <form jsf:id="calc">
            <div>
                Param1: <input type="text" jsf:value="#{tinyCalculator.param1}"/>
            </div>
            <div>
                Param2: <input type="text" jsf:value="#{tinyCalculator.param2}"/>
            </div>
            <div>
                <input type="submit" value="Add" 
                       jsf:action="#{tinyCalculator.add}"/>
                <input type="submit" value="Subtract" 
                       jsf:action="#{tinyCalculator.subtract}"/>
                <!-- or, using the button element: -->
                <button jsf:action="#{tinyCalculator.multiply()}">Multiply</button>
                <button jsf:action="#{tinyCalculator.divide()}">Divide</button>
            </div>
            <div>
                Result: #{tinyCalculator.result}
            </div>

            <hr/>
            <button jsf:action="index.xhtml">JSF Tag version</button>

            <hr/>
            Observe the component tree by clicking this button: 
            <button jsf:action="#{util.printTree}">Print</button>

            Or press Alt-Shift-t
            <ui:debug hotkey="t"/>

        </form>
    </body>
</html>


----- File: index.xhtml -----
<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:f="http://xmlns.jcp.org/jsf/core"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets">
    <h:head>
        <title>TinyCalculator</title>
    </h:head>
    <h:body>
        <h1>TinyCalculator</h1>
        <h:form id="form">
            <div>
                <h:outputLabel value="Param1: "/>
                <h:inputText id="param1" value="#{tinyCalculator.param1}"/>
                <h:message for="param1" errorClass="error"/>
            </div>
            <div>
                <h:outputLabel value="Param2: "/>
                <h:inputText id="param2"  value="#{tinyCalculator.param2}"/>
            </div>
            <div>
                <h:commandButton id="add" value="Add"
                                 action="#{tinyCalculator.add}"/>
                <h:commandButton id="sub" value="Subtract" 
                                 action="#{tinyCalculator.subtract}"/>
                <h:commandButton id="mul" value="Multiply" 
                                 action="#{tinyCalculator.multiply}"/>
                <h:commandButton id="div" value="Divide" 
                                 action="#{tinyCalculator.divide}"/>
            </div>
            <div>
                <h:outputLabel value="Result: "/>
                <h:outputText id="result" value="#{tinyCalculator.result}"/>
            </div>
            
            <hr/>
            <h:commandButton value="HTML friendly version" action="HtmlFriendlyCalc.xhtml"/>

            <hr/>
            Observe the component tree by clicking this button: 
            <h:commandButton value="Print" action="#{util.printTree}"/>
            
            Or press Ctrl-Shift-1
            <ui:debug hotkey="1"/>
        </h:form>
    </h:body>
</html>



----- File: web.xml -----
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="4.0" xmlns="http://xmlns.jcp.org/xml/ns/javaee" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
	http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd">
    
	<context-param>
        <param-name>javax.faces.PROJECT_STAGE</param-name>
        <param-value>Development</param-value>
    </context-param>
    
	<servlet>
        <servlet-name>Faces Servlet</servlet-name>
        <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    
	<servlet-mapping>
        <servlet-name>Faces Servlet</servlet-name>
        <url-pattern>*.xhtml</url-pattern>
    </servlet-mapping>
    
	<session-config>
        <session-timeout>
            30
        </session-timeout>
    </session-config>
    
	<welcome-file-list>
        <welcome-file>index.xhtml</welcome-file>
    </welcome-file-list>
</web-app>

