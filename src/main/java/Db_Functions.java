import java.sql.*;

public class Db_Functions {
    public Connection connectDb(String dbName, String username, String password) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/" + dbName, username, password);
            if (conn != null) {
                System.out.println("Connected");
            } else System.out.println("Connection failed");
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public void createTable(Connection conn, String tableName) {
        Statement statement;
        try {
            String query = "create table " + tableName + "(empid SERIAL, name varchar(20), address varchar(60),primary key(empid));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertTable(Connection conn, String tableName, String name, String address) {
        Statement statement;
        try {
            String query = String.format("insert into %s(name, address) values ('%s', '%s')", tableName, name, address);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.printf("%s and %s inserted to %s", name, address, tableName);
            System.out.println("Index selected");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void searchByIndex(Connection conn, String tableName, int index){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select * from %s where empid = %d", tableName, index);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getInt("empid")+ "  ");
                System.out.print(resultSet.getString("name")+ "  ");
                System.out.print(resultSet.getString("address"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void searchByName(Connection conn, String tableName, String name){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select * from %s where name = '%s'", tableName, name);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getInt("empid")+ "  ");
                System.out.print(resultSet.getString("name")+ "  ");
                System.out.println(resultSet.getString("address"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void printAll(Connection conn, String tableName){
        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = String.format("select * from %s" ,tableName);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getString("empid") + "  ");
                System.out.print(resultSet.getString("name")+ "  ");
                System.out.println(resultSet.getString("address"));

            }
        }catch (Exception e) {
            System.out.println(e);
        }

    }

    public void updateName(Connection conn, String tableName, String actualName, String newName){
        Statement statement;
        try {
            String query = String.format("update %s set name = '%s' where name = '%s'",tableName, newName, actualName);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Updated");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteData(Connection conn, String tableName, String adress){
        Statement statement;
        try {
            String query = String.format("delete from %s where address ='%s'", tableName, adress);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Deleted");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteTable(Connection conn, String tableName){
        Statement statement;
        try {
            String query = String.format("drop table %s",tableName);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.printf("Table %s deleted", tableName);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    }

