import com.google.gson.Gson;
import com.rabbitmq.client.DeliverCallback;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class HO {

    private final static String QUEUE_NAME = "bo";
    private static DatabaseManagementService databaseManagementService;
    static Ho_graphique hographique;



    public static void main(String[] argv) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Number of branch offices : ");

        int total_of_branch_offices=sc.nextInt();

        hographique = new Ho_graphique();

        RabbitMqManagementService rabbitMqManagementService = new RabbitMqManagementService("localhost");
        databaseManagementService = new DatabaseManagementService("jdbc:postgresql://localhost:5432/ho", "postgres", "postgres");
        Gson gson = new Gson();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            Sale sale = gson.fromJson(message, Sale.class);

            try {
                databaseManagementService.insertSale(sale);
                setTableRows();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        };

        for(int i=1 ;i<=total_of_branch_offices;i++){
            rabbitMqManagementService.declareQueue("bo"+i);
            rabbitMqManagementService.basicConsume("bo"+i, deliverCallback);
        }
        setTableRows();
    }

    static void setTableRows() throws SQLException {

        List<Sale> sales = databaseManagementService.getAllSales();
        String[][] tableRows = new String[sales.size()][];

        for(int i=0;i<sales.size();i++){
            tableRows[i]=sales.get(i).toStringArray();
        }
        hographique.setTableRows(tableRows);
    }

}
