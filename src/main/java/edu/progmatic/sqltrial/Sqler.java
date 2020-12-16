package edu.progmatic.sqltrial;


import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Sqler {

    public static void main(String[] args) throws SQLException {
        Sqler me = new Sqler();
        Connection connection = me.getConnection();
        System.out.println("connection ready");
        Scanner sc = new Scanner(System.in);
        System.out.println("Add meg, hogy mely ország adataira vagy kiváncsi!");
        String countryName = sc.nextLine();
        //do not do this, this is vulnerable to SQL injection!
        PreparedStatement preparedStatement = connection.prepareStatement("select NAME, POPULATION from country where code = '" + countryName  + "'");
        //Use this is instead!!
        //PreparedStatement preparedStatement = connection.prepareStatement("select NAME, POPULATION from country where code = ?");
        //preparedStatement.setString(1, countryName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            String name = resultSet.getString("name");
            int population = resultSet.getInt("population");
            System.out.println("name: " + name + " population: " + population);
        }
        connection.close();
    }

    public Connection getConnection() throws SQLException {
        String url      = "jdbc:mysql://localhost:3306/world";   //database specific url.

        Properties properties = new Properties( );
        properties.put( "user", "root" );
        properties.put( "password", "root" );

        Connection connection =
                DriverManager.getConnection(url, properties);
        return connection;
    }
}
