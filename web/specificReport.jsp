<%@ include file="/includes/usernameCookie.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Requested Report</title>
    </head>
    <body>
    <center><h1>Requested Report</h1></center>
            <form action ="EntryProcessingServlet">
                <h4>From Date:</h4>
                Month:<select name="fromMonth">
                    <option value="01">January</option><option value="02">February</option><option value="03">March</option>
                    <option value="04">April</option><option value="05">May</option><option value="06">June</option>
                    <option value="07">July</option> <option value="08">August</option> <option value="09">September</option>
                    <option value="10">October</option><option value="11">November</option><option value="12">December</option>
                </select>
                
                <br>Day:<select name ="fromDay">
                    <option value="01">1</option><option value="02">2</option><option value="03">3</option><option value="04">4</option><option value="05">5</option><option value="06">6</option>
                    <option value="07">7</option><option value="08">8</option><option value="09">9</option><option value="10">10</option><option value="11">11</option>
                    <option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option>
                    <option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option>
                    <option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option>
                    <option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option>
                </select>
                
                <br>Year:<select name="fromYear">
                    <option value="2010">2010</option><option value="2011">2011</option><option value="2012">2012</option><option value="2013">2013</option>
                    <option value="2014">2014</option><option value="2015">2015</option><option value="2016">2016</option><option value="2017">2017</option>
                </select>
                
                <br><br><h4>To Date:</h4>
                Month:<select name="toMonth">
                    <option value="1">January</option><option value="2">February</option><option value="3">March</option><option value="4">April</option>
                    <option value="5">May</option><option value="6">June</option><option value="7">July</option><option value="8">August</option>
                    <option value="9">September</option><option value="10">October</option><option value="11">November</option><option value="12">December</option>
                </select>
                
                <br>Day:<select name ="toDay">
                    <option value="01">1</option><option value="02">2</option><option value="03">3</option><option value="04">4</option><option value="05">5</option><option value="06">6</option>
                    <option value="07">7</option><option value="08">8</option><option value="09">9</option><option value="10">10</option><option value="11">11</option>
                    <option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option>
                    <option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option>
                    <option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option>
                    <option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option>
                </select>
                
                <br>Year:<select name="toYear">
                    <option value="2010">2010</option><option value="2011">2011</option><option value="2012">2012</option><option value="2013">2013</option>
                    <option value="2014">2014</option><option value="2015">2015</option><option value="2016">2016</option><option value="2017">2017</option>
                </select>
                
                <br><br>Type of Report:<select name="reportType">
                    <option value="totalIncome">All Incomes by selected dates</option>
                    <option value="totalExpense">All Expenses by selected dates</option>
                    <option value="allTipIncome">All Income from work</option>
                </select>
                
                <br><br><input type="submit" name="action" value="Submit Report Request">
            </form>
    
    <br><br><hr><br><br>
    
    <center><h1>Report Summary</h1></center>
    <p><table border ="5">
            <tr><th colspan="7">Requested Income Entries</th>
            <tr><th>Title</th><th>Date</th><th>Type</th><th>Amount</th><th>Memo</th><th>Deposited</th><th>Account</th></tr>
               
            <c:forEach var="income" items="${IncomeReport}" varStatus="loopStatus" >
            <tr>
            <form action="EntryProcessingServlet">
                <td><input type="text"  name="title" value="${income.title}"/></td>
                <td><input type="text"  name ="date" value="${income.date}"/></td>
                <td><input type="text"  name ="type" value="${income.type}"/></td>
                <td><input type="text"  name="amount" value="${income.amount}"/></td>
                <td><input type="text"  name="memo" value="${income.memo}"/></td>
                <td><input type="text"  name="payment" value="${income.deposited}"/></td>
                <td><input type="text"  name="payment" value="${income.account}"/></td>
                <input type ="hidden" name="index" value="${income.id_income}" />
            </form>
            </tr>
            </c:forEach> 
        </table>
        </p>
    
        <br><br><hr><br><br>
        
        <p><table border ="5">
            <tr><th colspan="6">Requested Expense Entries</th>
            <tr><th>Title</th><th>Date</th><th>Type</th><th>Amount</th><th>Memo</th><th>Payment</th></tr>
               
            <c:forEach var="expense" items="${ExpenseReport}" varStatus="loopStatus" >
            <tr>
            <form action="EntryProcessingServlet">
                <td><input type="text"  name="title" value="${expense.title}"/></td>
                <td><input type="text"  name ="date" value="${expense.date}"/></td>
                <td><input type="text"  name ="type" value="${expense.type}"/></td>
                <td><input type="text"  name="amount" value="${expense.amount}"/></td>
                <td><input type="text"  name="memo" value="${expense.memo}"/></td>
                <td><input type="text"  name="payment" value="${expense.payment}"/></td>
                <input type ="hidden" name="index" value="${expense.id_expense}" />
            </form>
            </tr>
            </c:forEach> 
        </table>
        </p>
        
        <br><br><hr><br><br>
        <form action="EntryProcessingServlet">
            <pre><input type="submit" name="action" value="New Income Entry">     <input type="submit" name="action" value="New Expense Entry">     <input type="submit" name="action" value="Edit/Remove Entry"></pre>
        </form>
        
    </body>
</html>
