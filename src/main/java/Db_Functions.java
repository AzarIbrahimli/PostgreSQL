import java.lang.reflect.Type;
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
            String query = "create table " + tableName + "(empid SERIAL, name varchar(20), address varchar(60), salary integer, primary key(empid));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertTable(Connection conn, String tableName, String name, String address, int salary) {
        Statement statement;
        try {
            String query = String.format("insert into %s(name, address, salary) values ('%s', '%s', %d)", tableName, name, address, salary);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.printf("%s, %s and %d inserted to %s", name, address,salary,  tableName);
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
                System.out.print(resultSet.getString("address") + "  ");
                System.out.println(resultSet.getInt("salary"));
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
                System.out.print(resultSet.getString("address") + "  ");
                System.out.println(resultSet.getInt("salary"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void searchLike(Connection conn, String tableName, String input){
        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = String.format("select * from %s where name like '%%%s%%'", tableName,input);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                System.out.print(resultSet.getInt("empid") + "  ");
                System.out.print(resultSet.getString("name") + "  ");
                System.out.print(resultSet.getString("address") + "  ");
                System.out.println(resultSet.getInt("salary"));
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
                System.out.print(resultSet.getString("address") + "  ");
                System.out.print(resultSet.getInt("salary") + "  ");
                System.out.println(resultSet.getInt("job"));

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

    public void countTable(Connection conn, String tableName){
        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = String.format("select Count(*) as count from  %s",tableName);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                System.out.print(resultSet.getInt("count"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void sumSalary(Connection conn, String tableName){
        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = String.format("select sum(salary) as sum from  %s",tableName);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                System.out.print(resultSet.getInt("sum"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void averageSalary(Connection conn, String tableName){
        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = String.format("select avg(salary) as avg from  %s",tableName);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                Float result =resultSet.getFloat("avg");
                System.out.printf("Average salary: %.2f\n",result);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void minSalary(Connection conn, String tableName){
        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = String.format("select min(salary) as min from  %s",tableName);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                Float result =resultSet.getFloat("min");
                System.out.printf("Minimum salary: %.2f\n",result);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void maxSalary(Connection conn, String tableName){
        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = String.format("select max(salary) as max from  %s",tableName);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                Float result =resultSet.getFloat("max");
                System.out.printf("Maximum salary: %.2f\n",result);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void maxMinSalaryDifference(Connection conn, String tableName){
        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = String.format("select max(salary)- min(salary) as diff from  %s",tableName);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                Float result =resultSet.getFloat("diff");
                System.out.printf("Max and min salary difference: %.2f\n",result);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void groupByAddress(Connection conn, String tableName){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select address,count(*) as person from %s group by address order by count(*) desc", tableName);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getString("address") + "  ");
                System.out.println(resultSet.getString("person"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void groupBySalary(Connection conn, String tableName){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select address,sum(salary) as sum from %s group by address having sum(salary)>2000 order by sum(salary) ", tableName);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getString("address") + "  ");
                System.out.println(resultSet.getString("sum"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void highestPaidEmployee(Connection conn, String tableName){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select * from %s where salary = (select max(salary) from %s)",tableName,tableName);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                System.out.print(resultSet.getInt("empid") + "  ");
                System.out.print(resultSet.getString("name") + "  ");
                System.out.print(resultSet.getString("address") + "  ");
                System.out.println(resultSet.getFloat("salary"));}

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void highestPaidEmployee(Connection conn, String tableName, String address){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select * from %s where address = '%s' and salary = (select max(salary) from %s)",tableName, address, tableName);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                System.out.print(resultSet.getInt("empid") + "  ");
                System.out.print(resultSet.getString("name") + "  ");
                System.out.print(resultSet.getString("address") + "  ");
                System.out.println(resultSet.getFloat("salary"));}
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void updateSalaryByAddress(Connection conn, String tableName, String address, int salary){
        Statement statement;
        try {
            String query =String.format("update %s set salary = %d where address = '%s'", tableName, salary, address);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Updated");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void increaseSalaryByName(Connection conn, String tableName, String name, int salary){
        Statement statement;
        try {
            String query = String.format("update %s set salary = salary + salary*%d/100 where name = '%s'", tableName, salary, name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Updated");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void increaseSalaryByJob(Connection conn, String tableName, String jobName, int percentage){
        Statement statement;
        try {
            String query = String.format("update %s set salary =  salary + salary*%d/100 where job = (select id from job where name = '%s') ", tableName,percentage, jobName);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Salary updated");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void searchEmployeeByJob(Connection conn, String tableName, String job){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select * from %s where job = (select id from job where name = '%s')", tableName, job);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getInt("empid") + "  ");
                System.out.print(resultSet.getString("name") + "  ");
                System.out.print(resultSet.getString("address") + "  ");
                System.out.print(resultSet.getFloat("salary") + "  ");
                System.out.println(resultSet.getInt("job") + "  ");



            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void searchEmployeeByAddressByJob(Connection conn, String tableName, String address, String jobName){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select * from %s where address = '%s' and job = (select id from job where name = '%s')", tableName,address, jobName);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                System.out.print(resultSet.getInt("empid")+ "  ");
                System.out.print(resultSet.getString("name")+ "  ");
                System.out.print(resultSet.getString("address")+ "  ");
                System.out.print(resultSet.getFloat("salary")+ "  ");
                System.out.println(resultSet.getInt("job"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void truncateTable(Connection conn, String tableName){
        Statement statement;
        try {
            String query = String.format("truncate table %s", tableName);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Truncated");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    }

