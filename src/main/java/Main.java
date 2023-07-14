import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Db_Functions dbFunctions = new Db_Functions();
        Connection conn = dbFunctions.connectDb("db_initial","postgres", "postgresql");
        //dbFunctions.createTable(conn,"test");
        //dbFunctions.insertTable(conn,"employee","Elvin", "Mingechaur",2675);
        //dbFunctions.searchByIndex(conn,"employee",2);
        //dbFunctions.updateName(conn,"employee", "Vugar","Vuqar");
        //dbFunctions.searchByIndex(conn,"employee",2);
        //dbFunctions.searchByName(conn,"employee","Azar");
        //dbFunctions.printAll(conn,"employee");
        //dbFunctions.deleteData(conn,"employee", "Mingechaur");
        //dbFunctions.deleteTable(conn,"test");
        //dbFunctions.searchLike(conn,"employee","r");
        //dbFunctions.countTable(conn,"employee");
        //dbFunctions.sumSalary(conn,"employee");
        //dbFunctions.averageSalary(conn,"employee");
        //dbFunctions.minSalary(conn,"employee");
        //dbFunctions.maxSalary(conn,"employee");
        //dbFunctions.maxMinSalaryDifference(conn,"employee");
        //dbFunctions.groupByAddress(conn,"employee");
        dbFunctions.groupBySalary(conn,"employee");

    }
}
