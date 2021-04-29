
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.Scanner;

import com.google.gson.*;

public class BO {

    static String boId;
    static Bo_graphique bographique;
    static DatabaseManagementService databaseManagementService;
    static RabbitMqManagementService rabbitMqManagementService;

    public static void main(String[] args) throws IOException, TimeoutException, SQLException, ClassNotFoundException {

        Scanner sc = new Scanner(System.in);

        rabbitMqManagementService = new RabbitMqManagementService("localhost");
        System.out.print("Branch office Id : ");
        boId=String.valueOf(sc.nextLine().charAt(0));

        rabbitMqManagementService.declareQueue("bo"+boId);

        databaseManagementService = new DatabaseManagementService("jdbc:postgresql://localhost:5432/bo"+boId, "postgres", "postgres");


        ActionListener saveToDB = (e) -> {
            saveToDB();
        };

        ActionListener syncWithHO = (e) -> {
            try {
                syncWithHo();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };

        ActionListener saveAndSync=(e)->{
            saveToDB();
            try {
                syncWithHo();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };

        bographique = new Bo_graphique(boId,saveToDB,syncWithHO,saveAndSync);
        setTableRows();
        syncWithHo();

    }
    static void saveToDB(){
        Sale sale = bographique.getSaleFromForm();
        try {
            databaseManagementService.insertSale(sale);
            setTableRows();
            bographique.clearFields();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    static void setTableRows() throws SQLException {

        List<Sale> sales = databaseManagementService.getAllSales();
        String[][] tableRows = new String[sales.size()][];


        for (int i = 0; i < sales.size(); i++) {
            tableRows[i] = sales.get(i).toStringArray();
        }

        bographique.setTableRows(tableRows);
    }

    static void syncWithHo() throws SQLException {
        Gson gson = new Gson();

        List<Sale> sales = databaseManagementService.getAllSales();
        for (Sale sale : sales) {
            if (sale.getSent() == 0) {
                try {
                    rabbitMqManagementService.publishBoToHo("bo" + boId, gson.toJson(sale));
                    databaseManagementService.setSent(sale.getId(), 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
