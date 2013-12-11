package accounting;

import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class ReportProcessing
{       
    public static void update(Statement statement, HttpServletRequest request)
    {
            String error = "";
            String reportType = request.getParameter("reportType");
            ArrayList<Income> incomeEntries = (ArrayList) request.getAttribute("IncomeEntries");
            ArrayList<Expense> expenseEntries = (ArrayList) request.getAttribute("ExpenseEntries");
            ArrayList<Income> incomeReport = new ArrayList<>();
            ArrayList<Expense> expenseReport = new ArrayList<>();
            
            if(reportType!=null)
            {
                switch(reportType)
                {
                    case "totalIncome":
                        incomeReport = getTotalIncome(incomeEntries, request);
                        break;
                    case "totalExpense":
                        expenseReport = getTotalExpense(expenseEntries, request);
                        break;
                    case "allTipIncome":
                        incomeReport = getTipIncome(incomeEntries, request);
                        break;
                }

            }
            request.setAttribute("IncomeReport", incomeReport);
            request.setAttribute("ExpenseReport", expenseReport);
    }
    
    private static ArrayList<Income> getTotalIncome(ArrayList<Income> tempArr, HttpServletRequest request)
    {
        ArrayList<Income> retArrList = new ArrayList<>();
        
        for(int i = 0; i<tempArr.size(); i++)
        {
            int month = Integer.parseInt(tempArr.get(i).date.substring(0,2));
            int day = Integer.parseInt(tempArr.get(i).date.substring(3,5));
            int year = Integer.parseInt(tempArr.get(i).date.substring(6));
            if(checkDate(month, day, year, request))
            {
                retArrList.add(tempArr.get(i));
            }
        }
        
        return retArrList;
    }
    
    private static ArrayList<Expense> getTotalExpense(ArrayList<Expense> tempArr, HttpServletRequest request)
    {
        ArrayList<Expense> retArrList = new ArrayList<>();
        
        for(int i = 0; i<tempArr.size(); i++)
        {
            int month = Integer.parseInt(tempArr.get(i).date.substring(0,2));
            int day = Integer.parseInt(tempArr.get(i).date.substring(3,5));
            int year = Integer.parseInt(tempArr.get(i).date.substring(6));
            if(checkDate(month, day, year, request))
            {
                retArrList.add(tempArr.get(i));
            }
        }
        
        return retArrList;
    }
    
    private static ArrayList<Income> getTipIncome(ArrayList<Income> tempArr, HttpServletRequest request)
    {
        ArrayList<Income> retArrList = new ArrayList<>();
        for(int i = 0; i<tempArr.size(); i++)
        {
            String type = tempArr.get(i).type;
            if(type.equals("work"))
                retArrList.add(tempArr.get(i));
        }
        return retArrList;
    }
    
    private static boolean checkDate(int month, int day, int year, HttpServletRequest request)
    {
        int fromMonth = Integer.parseInt(request.getParameter("fromMonth"));
        int fromDay = Integer.parseInt(request.getParameter("fromDay"));
        int fromYear = Integer.parseInt(request.getParameter("fromYear"));
        int toMonth = Integer.parseInt(request.getParameter("toMonth"));
        int toDay = Integer.parseInt(request.getParameter("toDay"));
        int toYear = Integer.parseInt(request.getParameter("toYear"));
        
        if(year < fromYear || year > toYear)
            return false;
        else if((year == fromYear && month < fromMonth) || (year == toYear && month > toMonth))
            return false;
        else if((year == fromYear && month == fromMonth && day < fromDay) || (year == toYear && month == toMonth && day > toDay))
            return false;
        else
            return true;
    }
}
