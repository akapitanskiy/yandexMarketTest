package ru.aplana.kapitanskiyYandexTest.helpers;

/**
 *  Created by AKapitanskiy on 18.10.2016 18:57.
 */

import java.sql.*;

public class JDBCExample {

    public static String[] sqlRequest(String product) {

        Connection connection = null;
        PreparedStatement preparedStatement;
        String[] dbRequest = {"", ""};
        String selectSQL = "SELECT price2, brand3 FROM yandex WHERE product = '" + product + "'";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
            e.printStackTrace();
            //return;
        }

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/yandex_testdb", "postgres", "0987654321");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            //return;
        }

        try {
            preparedStatement = connection.prepareStatement(selectSQL);
            //preparedStatement.setInt(1, 1001);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                dbRequest[0] = rs.getString("price2");
                dbRequest[1] = rs.getString("brand3");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbRequest;
    }
}
