import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Db_Functions dbFunctions = new Db_Functions();
        Connection conn = dbFunctions.connectDb("db_initial","postgres", "postgresql");
        //dbFunctions.createTable(conn,"test");
        //dbFunctions.insertTable(conn,"employee","Azar", "Fort Wayne");
        //dbFunctions.searchByIndex(conn,"employee",2);
        //dbFunctions.updateName(conn,"employee", "Vugar","Vuqar");
        //dbFunctions.searchByIndex(conn,"employee",2);
        //dbFunctions.searchByName(conn,"employee","Azar");
        //dbFunctions.printAll(conn,"employee");
        //dbFunctions.deleteData(conn,"employee", "Fort Wayne");
        //dbFunctions.deleteTable(conn,"test");
    }
}
