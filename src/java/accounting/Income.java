
package accounting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Income
{
    String title;
    String date;
    String type;
    String amount;
    String memo;
    String deposited;
    String account;
    int id_income;
    
    Income(String title, String date, String type, String amount, String memo, 
            String deposited, String account)
    {
        this.title = title;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.memo = memo;
        this.deposited = deposited;
        this.account = account;
    }
    
    Income(String title, String date, String type, String amount, String memo, 
            String deposited, String account, int id)
    {
        this.title = title;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.memo = memo;
        this.deposited = deposited;
        this.account = account;
        this.id_income = id;
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
    
    public String getdeposited()
    {
        return deposited;
    }
    
    public String getAccount()
    {
        return account;
    }
    
    public int getId_income()
    {
        return id_income;
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
            
    public void setDeposited(String deposited)
    {
        this.deposited = deposited;
    }
    
    public void setAccount(String account)
    {
        this.account = account;
    }
    
    private String quote_surround(String s) 
    {
        return "\'" + s + "\'";
    }
    
    public static void getIncome(Statement statement, ArrayList<Income> income)
    {
        try 
        {
            String sql = "select * from IncomeEntries";
            System.out.println("sql="+sql);
            ResultSet rs = statement.executeQuery(sql);
            income.clear();
            while (rs.next()) 
            {
                String ti = rs.getString("title");
                String da = rs.getString("date");
                String ty = rs.getString("type");
                String am = rs.getString("amount");
                String me = rs.getString("memo");
                String de = rs.getString("deposited");
                String ac = rs.getString("account");

                int id = rs.getInt("id_income");
                Income i = new Income(ti, da, ty, am, me, de, ac, id);
                income.add(i);
            }
        } 
        catch (SQLException ex) 
        {
            
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
        String sql = "update IncomeEntries set title=" + quote_surround(title)
                + ", date=" + quote_surround(date) + ", type=" + quote_surround(type)
                + ", amount=" + quote_surround(amount) + ", memo=" + quote_surround(memo)
                + ", deposited=" + quote_surround(deposited) + ", account=" + quote_surround(account)
                +" where id_income=" + index;
        executeUpdate(sql, statement);
    }
    
   public void insert(Statement statement) 
   {
        String sql = "insert into IncomeEntries values(" + quote_surround(title) + ","
                + quote_surround(date) + "," + quote_surround(type) + "," 
                + quote_surround(amount) + "," + quote_surround(memo) + "," 
                + quote_surround(deposited) + "," + quote_surround(account) + ", null)";
        executeUpdate(sql, statement);
    }

    public static void remove(int index, Statement statement) 
    {
        String sql = "delete from IncomeEntries ";
        if (index >= 0)
            sql += " where id_income=" + index;
        executeUpdate(sql, statement);
    }
    
}
