import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Db_Functions dbFunctions = new Db_Functions();
        Connection conn = dbFunctions.connectDb("db_initial","postgres", "postgresql");
        //dbFunctions.createTable(conn,"job");
        //dbFunctions.insertTable(conn,"employee","Mahammad", "Praga",2975,6);
        //dbFunctions.searchByIndex(conn,"employee",2);
        //dbFunctions.updateName(conn,"employee", "Vugar","Vuqar");
        //dbFunctions.searchByIndex(conn,"employee",2);
        //dbFunctions.searchByName(conn,"employee","Azar");
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
        //dbFunctions.groupBySalary(conn,"employee");
        //dbFunctions.highestPaidEmployee(conn,"employee");
        //dbFunctions.highestPaidEmployee(conn,"employee","San Francisco");
        //dbFunctions.updateSalaryByAddress(conn,"employee", "San Francisco",4000);
        //dbFunctions.searchEmployeeByJob(conn, "employee", "engineer");
        //dbFunctions.increaseSalaryByJob(conn,"employee","engineer",10);
        //dbFunctions.increaseSalaryByName(conn,"employee","Vuqar",10);
        //dbFunctions.searchEmployeeByAddressByJob(conn,"employee", "Baku", "teacher");
        //dbFunctions.truncateTable(conn,"test");
        //dbFunctions.innerJoin(conn);
        //dbFunctions.countByDepartment(conn);
        //dbFunctions.leftJoin(conn);
        //dbFunctions.rightJoin(conn);
        //dbFunctions.fullJoin(conn);
        //dbFunctions.crossJoin(conn);
        //dbFunctions.ascii(conn,'A');
        //dbFunctions.concat(conn,"Azar ","Ibrahimli");
        //dbFunctions.concatWS(conn,"_","Azar","Ibrahimli");
        //dbFunctions.left(conn,"Azerbaijan",4);
        //dbFunctions.right(conn,"Azerbaijan",3);
        //dbFunctions.length(conn,"Azerbaijan");
        //dbFunctions.replace(conn,"v","d","job");
        //dbFunctions.reverseName(conn,"job");
        //dbFunctions.substring(conn,"Azar Ibrahimli", 2,3);
        //dbFunctions.lowerName(conn,"job");
        //dbFunctions.upperName(conn,"job");



        //dbFunctions.printAll(conn,"employee");

    }
}
