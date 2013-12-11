<%@ include file="/includes/usernameCookie.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Income Entry Form</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
    </head>
    <body>
        <h1><center>Income Entry Form</h1></center>
        <form action="EntryProcessingServlet">
            <br>Income Title:<%@ include file="/includes/userInputFields.jsp" %>
            <br>Type:<select name="type">
                <option value="work">Work</option>
                <option value="gift">Gift</option>
                <option value="other">Other</option>
            </select>
            <br>Deposited:
            <br><input type="radio" name="deposited" value="yes"/>Yes
            <input type="radio" name="deposited" value="no"/>No
            <br>Account<select name="account">
                <option value="checking">Checking</option>
                <option value="saving">Savings - Main</option>
                <option value="neither">Neither</option>
            </select>
            <br><input type="submit" name="action" value="Submit Income Entry">
            
        </form>
    </body>
</html>
