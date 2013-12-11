<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recent Entry Report</title>
    </head>
    <body>
        <center><h1>Entry Report</h1></center>
        <p><table border ="5">
            <tr><th colspan="9">Income Entries</th>
            <tr><th>Title</th><th>Date</th><th>Type</th><th>Amount</th><th>Memo</th><th>Deposited</th><th>Account</th><th>Remove</th><th>Update</th></tr>
               
            <c:forEach var="income" items="${IncomeEntries}" varStatus="loopStatus" >
            <tr>
            <form action="EntryProcessingServlet">
                <td><input type="text"  name="title" value="${income.title}"/></td>
                <td><input type="text"  name ="date" value="${income.date}"/></td>
                <td><input type="text"  name ="type" value="${income.type}"/></td>
                <td><input type="text"  name="amount" value="${income.amount}"/></td>
                <td><input type="text"  name="memo" value="${income.memo}"/></td>
                <td><input type="text"  name="deposited" value="${income.deposited}"/></td>
                <td><input type="text"  name="account" value="${income.account}"/></td>
                <td><input type="submit" name="action" value="Remove Income"/></td>
                <td><input type="submit" name ="action" value="Update Income"/></td>
                <input type ="hidden" name="index" value="${income.id_income}" />
            </form>
            </tr>
            </c:forEach> 
        </table>
        </p>
        
        <br><br><hr><br><br>
        
        <p><table border ="5">
            <tr><th colspan="8">Expense Entries</th>
            <tr><th>Title</th><th>Date</th><th>Type</th><th>Amount</th><th>Memo</th><th>Payment</th><th>Remove</th><th>Update</th></tr>
               
            <c:forEach var="expense" items="${ExpenseEntries}" varStatus="loopStatus" >
            <tr>
            <form action="EntryProcessingServlet">
                <td><input type="text"  name="title" value="${expense.title}"/></td>
                <td><input type="text"  name ="date" value="${expense.date}"/></td>
                <td><input type="text"  name ="type" value="${expense.type}"/></td>
                <td><input type="text"  name="amount" value="${expense.amount}"/></td>
                <td><input type="text"  name="memo" value="${expense.memo}"/></td>
                <td><input type="text"  name="payment" value="${expense.payment}"/></td>
                <td><input type="submit" name="action" value="Remove Expense"/></td>
                <td><input type="submit" name ="action" value="Update Expense"/></td>
                <input type ="hidden" name="index" value="${expense.id_expense}" />
            </form>
            </tr>
            </c:forEach> 
        </table>
        </p>    
        
        <br><br><hr><br><br>
        <form action="EntryProcessingServlet">
            <pre><input type="submit" name="action" value="New Income Entry">     <input type="submit" name="action" value="New Expense Entry">     <input type="submit" name="action" value="Detailed Report Entry"></pre>
        </form>
        
    </body>
</html>
