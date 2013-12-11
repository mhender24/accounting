
package accounting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Expense
{
    String title;
    String date;
    String type;
    String amount;
    String memo;
    String payment;
    int id_expense;
    
    Expense(String title, String date, String type, String amount, String memo, String payment)
    {
        this.title = title;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.memo = memo;
        this.payment = payment;
    }
    
    Expense(String title, String date, String type, String amount, String memo, 
            String payment, int id)
    {
        this.title = title;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.memo = memo;
        this.payment = payment;
        this.id_expense = id;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public String getDate()
    {
        return date;
    }
    
    public String getType()
    {
        return type;
    }
    
    public String getAmount()
    {
        return amount;
    }
    
    public String getMemo()
    {
        return memo;
    }
    
    public String getPayment()
    {
        return payment;
    }
    
    public int getId_expense()
    {
        return id_expense;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public void setAmount(String amount)
    {
        this.amount = amount;
    }
    
    public void setMemo(String memo)
    {
        this.memo = memo;
    }
    
    public void setPayment(String payment)
    {
        this.payment = payment;
    }
    
    private String quote_surround(String s) 
    {
        return "\'" + s + "\'";
    }
    
    public static void getExpense(Statement statement, ArrayList<Expense> expense)
    {
        try 
        {
            String sql = "select * from ExpenseEntries";
            System.out.println("sql="+sql);
            ResultSet rs = statement.executeQuery(sql);
            expense.clear();
            while (rs.next()) 
            {
                String ti = rs.getString("title");
                String da = rs.getString("date");
                String ty = rs.getString("type");
                String am = rs.getString("amount");
                String me = rs.getString("memo");
                String pa = rs.getString("payment");

                int id = rs.getInt("id_expense");
                Expense ex = new Expense(ti, da, ty, am, me, pa, id);
                expense.add(ex);
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println("sqlException= " + ex);
        }
    }
    
    private static void executeUpdate(String sql, Statement statement) 
    {
        try 
        {
            System.out.println("sql=" + sql);
            statement.executeUpdate(sql);
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        }
    }
    
   public void update(int index, Statement statement) 
   {
        String sql = "update ExpenseEntries set title=" + quote_surround(title)
                + ", date=" + quote_surround(date) + ", type=" + quote_surround(type)
                + ", amount=" + quote_surround(amount) + ", memo=" + quote_surround(memo)
                +  ", payment=" + quote_surround(payment) +" where id_expense=" + index;
        executeUpdate(sql, statement);
    }
    
   public void insert(Statement statement) 
   {
        String sql = "insert into ExpenseEntries values(" + quote_surround(title) + ","
                + quote_surround(date) + "," + quote_surround(type) + "," 
                + quote_surround(amount) + "," + quote_surround(memo) + "," 
                 + quote_surround(payment) + ", null)";
        executeUpdate(sql, statement);
    }

    public static void remove(int index, Statement statement) 
    {
        String sql = "delete from ExpenseEntries ";
        if (index >= 0)
            sql += " where id_expense=" + index;
        executeUpdate(sql, statement);
    }
    
}
