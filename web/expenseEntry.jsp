<%@ include file="/includes/usernameCookie.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Expense Entry Form</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
    </head>
    <body>
        <h1><center>Expense Entry Form</h1></center>
        <form action="EntryProcessingServlet">
            <br>Expense Title:<%@ include file="/includes/userInputFields.jsp" %>
            <br>Type:<select name="type">
                <option value="grocery">Grocery</option>
                <option value="bill">Bills</option>
                <option value="entertainment">Entertainment</option>
            </select>
            <br>Form Of Payment<select name="payment">
                <option value="cash">Cash</option>
                <option value="check">Check</option>
                <option value="card">Debit/Credit Card</option>
            </select>
            <br><input type="submit" name="action" value="Submit Expense Entry">
            
        </form>
    </body>
</html>
