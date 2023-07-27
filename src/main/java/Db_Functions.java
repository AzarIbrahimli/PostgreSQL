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

    public void insertTable(Connection conn, String tableName, String name, String address, int salary, int job) {
        Statement statement;
        try {
            String query = String.format("insert into %s(name, address, salary, job) values ('%s', '%s', %d, %d)", tableName, name, address, salary,job);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.printf("%s, %s, %d and %d inserted to %s", name, address,salary,job,tableName);
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

    public void innerJoin(Connection conn){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select employee.name,address,job.name as jobName from employee inner join job on job = id");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                System.out.print(resultSet.getString("name") + "  ");
                System.out.print(resultSet.getString("address") + "  ");
                System.out.println(resultSet.getString("jobName"));
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void countByDepartment(Connection conn){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select name, count(*) as count from program inner join department on program.program_dep = department.id group by name");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                System.out.print(resultSet.getString("name")+ "  ");
                System.out.println(resultSet.getString("count"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void leftJoin(Connection conn){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select program_id, program_name, name from program left join department on id=program_dep");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                System.out.print(resultSet.getInt("program_id") + "  ");
                System.out.print(resultSet.getString("program_name") + "  ");
                System.out.println(resultSet.getString("name"));

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void rightJoin(Connection conn){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select program_id, program_name, name from program right join department on id=program_dep");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                System.out.print(resultSet.getInt("program_id") + "  ");
                System.out.print(resultSet.getString("program_name") + "  ");
                System.out.println(resultSet.getString("name"));

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void fullJoin(Connection conn){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select employee.name, address, job.name as jn from employee full join job on employee.job = job.id");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                System.out.print(resultSet.getString("name") + "  ");
                System.out.print(resultSet.getString("address") + "  ");
                System.out.println(resultSet.getString("jn"));

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void crossJoin(Connection conn){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select employee.name, address, job.name as jn from employee cross join job ");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                System.out.print(resultSet.getString("name") + "  ");
                System.out.print(resultSet.getString("address") + "  ");
                System.out.println(resultSet.getString("jn"));

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void exceptTable(Connection conn,String tableNameOne, String tableNameTwo){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select * from %s except select * from %s",tableNameOne, tableNameTwo );
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getString("id") + "  ");
                System.out.println(resultSet.getString("name"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void intersectTable(Connection conn,String tableNameOne, String tableNameTwo){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select * from %s intersect select * from %s",tableNameOne, tableNameTwo );
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getString("id") + "  ");
                System.out.println(resultSet.getString("name"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void unionTable(Connection conn,String tableNameOne, String tableNameTwo){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select * from %s union select * from %s",tableNameOne, tableNameTwo );
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getString("id") + "  ");
                System.out.println(resultSet.getString("name"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void ascii(Connection conn,char sth){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select ASCII  ('%c') as result",sth);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getString("result") + "  ");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void concat(Connection conn,String word1, String word2){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select CONCAT('%s', '%s') as con",word1, word2 );
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getString("con") + "  ");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void concatWS(Connection conn,String sth, String word1, String word2){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select CONCAT_WS('%s','%s', '%s') as con",sth,word1, word2 );
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getString("con") + "  ");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void left(Connection conn,String word1, int c){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select left('%s', %d) as c",word1, c );
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getString("c") + "  ");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void right(Connection conn,String word1, int c){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select right('%s', %d) as c",word1, c );
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getString("c") + "  ");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void length(Connection conn,String word){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select length('%s') as c",word);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getInt("c") + "  ");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void replace(Connection conn,String actual, String replace,String tableName){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select id,replace(name, '%s', '%s') as name from %s",actual, replace, tableName );
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getInt("id") + "  ");
                System.out.println(resultSet.getString("name"));

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void reverseName(Connection conn,String tableName){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select id, reverse(name) as name from %s",tableName );
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getInt("id") + "  ");
                System.out.println(resultSet.getString("name"));

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void substring(Connection conn,String String, int from, int count){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select substring('%s', %d, %d) as result",String, from, count );
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println(resultSet.getString("result"));

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void lowerName(Connection conn,String tableName){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select lower(name) as name from %s", tableName );
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println(resultSet.getString("name"));

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void upperName(Connection conn,String tableName){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select upper(name) as name from %s", tableName );
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println(resultSet.getString("name"));

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void abs(Connection conn, int c){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select abs(%d) as abs",c);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("abs"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void ceil(Connection conn, double c){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select ceil(%f) as ceil",c);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("ceil"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void floor(Connection conn, double c){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select floor(%f) as floor",c);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("floor"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void pi(Connection conn){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select pi() as pi");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getDouble("pi"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void power(Connection conn, int a, int b){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select power(%d, %d) as result",a,b);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("result"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void random(Connection conn){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select Random() as random");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getDouble("random"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void round(Connection conn, double d, int c){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select Round(%f, %d) as round",d,c);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getDouble("round"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void sign(Connection conn, double d){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select sign(%f) as sign",d);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if(resultSet.getDouble("sign")>0){
                    System.out.println("Positive");
                } else if (resultSet.getDouble("sign")==0) {
                    System.out.println("Zero");
                }
                else System.out.println("Negative");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void sqrt(Connection conn, double d){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select Sqrt(%f) as result",d);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getDouble("result"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void log(Connection conn, double d){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select Log(%f) as result",d);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getDouble("result"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void currentDate(Connection conn){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select Current_Date as date");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println(resultSet.getDate("date"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void currentTime(Connection conn){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select current_time as time");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println(resultSet.getTime("time"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void getNow(Connection conn){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select now() as now");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println(resultSet.getString("now"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void getAge(Connection conn,String date){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select age(timestamp '%s') as age", date);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println(resultSet.getString("age"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void getRegistrationDate(Connection conn, String date){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select name, date, age(now(),date) as diff from books");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getString("name") + "  ");
                System.out.print(resultSet.getDate("date") + "  ");
                System.out.println(resultSet.getString("diff"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void getLessonsWithLength(Connection conn, int length){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("select id, lessonname, limits, programid from lessons where length(lessonname)>12",length);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getInt("id") + "  ");
                System.out.print(resultSet.getString("lessonname") + "  ");
                System.out.print(resultSet.getInt("limits") + "  ");
                System.out.println(resultSet.getInt("programid"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void makeViewWithCheckOption(Connection conn, int length){
        Statement statement;
        try {
            String query = String.format("create or replace view view2 as select id, lessonname, limits, programid from lessons where length(lessonname)>%d with check option",length);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Done");
        }catch (Exception e){
            System.out.println(e);
        }
    }







}

