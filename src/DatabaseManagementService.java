import com.google.gson.Gson;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManagementService {

    Connection connection;

    public DatabaseManagementService(String url, String user, String password) throws ClassNotFoundException, SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public List<Sale> getAllSales() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from sales order by id desc");

        List<Sale> sales = new ArrayList<Sale>();

        while (rs.next()) {
            Sale sale = new Sale();
            sale.initFromResultSet(rs);
            sales.add(sale);
        }
        return sales;
    }

    public void insertSale(Sale sale) throws SQLException {
        String query = MessageFormat.format(
                "insert into sales(date,region,product,quantity,cost,amt,tax,total,sent)" +
                        " values ( ''{0}'', ''{1}'', ''{2}'',{3,number,#},{4,number,#.###},{5,number,#.###},{6,number,#.###},{7,number,#.###},{8,number,#});",
                sale.getDate(),
                sale.getRegion(),
                sale.getProduct(),
                sale.getQuantity(),
                sale.getCost(),
                sale.getAmt(),
                sale.getTax(),
                sale.getTotal(),
                sale.getSent()
        );
        Statement stmt = connection.createStatement();
         stmt.executeUpdate(query);

    }

    public void setSent(int saleId, int sentValue) throws SQLException {
        Statement stmt=connection.createStatement();
        stmt.executeUpdate("update sales set sent="+sentValue+" where id="+saleId+";");
    }


}
