package accounting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EntryProcessingServlet extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        Statement statement;
        RequestDispatcher dispatcher;
        
        HttpSession session = request.getSession(true);
        Connection connection = (Connection) session.getAttribute("connection");
        if(connection==null)
            System.out.println("connection is null");
            
        String error = "";
        
        try 
        {
            statement = connection.createStatement();
            
            if(statement!=null && error.length()==0)
            {
                EntryProcessing.update(statement, request);
            }
            
            if(statement!=null)
            {
                statement.close();
            }
            
            if(action.equals("Submit Report Request"))
            {
                ReportProcessing.update(statement, request);
                dispatcher = getServletContext().getRequestDispatcher("/specificReport.jsp");
                dispatcher.forward(request, response);
            }
            
            else if(action.equals("New Income Entry"))
            {
                dispatcher = getServletContext().getRequestDispatcher("/incomeEntry.jsp");
                dispatcher.forward(request, response);
            }
            
            else if(action.equals("New Expense Entry"))
            {
                dispatcher = getServletContext().getRequestDispatcher("/expenseEntry.jsp");
                dispatcher.forward(request, response);
            }
            
            else if(action.equals("Detailed Report Entry"))
            {
                dispatcher = getServletContext().getRequestDispatcher("/specificReport.jsp");
                dispatcher.forward(request, response);
            }
            
            else if(action.equals("Edit/Remove Entry"))
            {
                dispatcher = getServletContext().getRequestDispatcher("/recentEntryReport.jsp");
                dispatcher.forward(request, response);
            }
            
            else
            {
                dispatcher = getServletContext().getRequestDispatcher("/recentEntryReport.jsp");
                dispatcher.forward(request, response);
            }
        }
        catch(SQLException e)
        {
            System.out.println("sql except: " + e);
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
