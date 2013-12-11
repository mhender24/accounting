<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    String userId = "";
    Cookie[] cookies = request.getCookies();
    
        if(cookies!=null)
        {
            for (int i=0; i < cookies.length; i++)
            {
                if ("User".equals(cookies[i].getName()))
                {
                    userId = cookies[i].getValue();
                    break;
                }
            }
        }
%>

<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h1>Login Page</h1>
        <form action ="LoginServlet">
            <br>User Name<input type="text" name="userId" value="<%=userId%>"/>
            <br>Password<input type="password" name="password"/>
            <br>Select Action:<select name="command">
                <option value="incomeEntry">Income Entry</option>
                <option value="expenseEntry">Expense Entry</option>
                <option value="reportEntry">Report</option>
            </select>
            <br><input type="submit" name="login"/>
        </form>
    </body>
</html>
