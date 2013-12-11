package accounting;

import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class EntryProcessing
{
    public static void update(Statement statement, HttpServletRequest request)
    {
            String error = "";
            String action = request.getParameter("action");
            
            if(action!=null)
            {
                String title = request.getParameter("title");
                String month = request.getParameter("month");
                String day = request.getParameter("day");
                String year = request.getParameter("year");
                String date;
                String type = request.getParameter("type");
                String amount = request.getParameter("amount");
                String memo = request.getParameter("memo");
                String deposited = request.getParameter("deposited");
                String account = request.getParameter("account");
                String payment = request.getParameter("payment");
                Income income = null;
                Expense expense = null;
                String strIndex;
                int index;

                switch(action)
                {
                    case "Submit Income Entry":
                        date = month + "/" + day + "/" + year;
                        income = new Income(title, date, type, amount, memo, deposited, account);
                        income.insert(statement);
                        break;
                    case "Submit Expense Entry":
                        date = month + "/" + day + "/" + year;
                        expense = new Expense(title, date, type, amount, memo, payment);
                        expense.insert(statement);
                        break;
                    case "Remove Income":
                        strIndex = request.getParameter("index");
                        index = Integer.parseInt(strIndex);
                        Income.remove(index, statement);
                        break;
                    case "Update Income":
                        date = request.getParameter("date");
                        strIndex = request.getParameter("index");
                        index = Integer.parseInt(strIndex);
                        income = new Income(title, date, type, amount, memo, deposited, account);
                        income.update(index, statement);
                        break;
                    case "Remove Expense":
                        strIndex = request.getParameter("index");
                        index = Integer.parseInt(strIndex);
                        Expense.remove(index, statement);
                        break;
                    case "Update Expense":
                        date = request.getParameter("date");
                        strIndex = request.getParameter("index");
                        index = Integer.parseInt(strIndex);
                        expense = new Expense(title,date, type, amount, memo, payment);
                        expense.update(index, statement);
                        break;   
                }
                
                ArrayList<Income> incomeEntries = new ArrayList<>();
                ArrayList<Expense> expenseEntries = new ArrayList<>();
                Income.getIncome(statement, incomeEntries);
                Expense.getExpense(statement, expenseEntries);
                request.setAttribute("IncomeEntries", incomeEntries);
                request.setAttribute("ExpenseEntries", expenseEntries);
            }    
    }
}
