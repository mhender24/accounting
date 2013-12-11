package accounting;

import java.sql.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdbc.MyConnection;

public class LoginServlet extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        RequestDispatcher dispatch;
        try
        {
            String command = request.getParameter("command");
            String[] args = {request.getParameter("userId"), request.getParameter("password")};
            String webpage = "/" + command;
            Connection connection = MyConnection.getConnection(args);
            
            if(connection!=null)
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("connection", connection);
                webpage += ".jsp";
                if(command.equals("reportEntry"))
                {
                    dispatch = getServletContext().getRequestDispatcher("/specificReport.jsp");
                    dispatch.forward(request, response);
                }
                else
                {
                    dispatch = getServletContext().getRequestDispatcher(webpage);
                    dispatch.forward(request, response);
                }
            }
            else
            {
                webpage = "/login.jsp";
                dispatch = getServletContext().getRequestDispatcher(webpage);
                dispatch.forward(request, response);
            }
        } 
        catch(ServletException | IOException e)
        {
            System.out.println("Login Servlet error: " + e);
        }
        finally 
        {
            out.close();
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
