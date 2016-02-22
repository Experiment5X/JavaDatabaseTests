/**
 * Created by adam on 2/21/16.
 */
import java.io.File;
import java.sql.*;
import java.util.*;


public class EmployeeDatabase
{
    private Connection connection;

    public EmployeeDatabase()
    {
        boolean databaseExists = new File("employee.db").isFile();

        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:employee.db");

            // check if we need to create the table
            if (!databaseExists)
                initializeDatabase();
        }
        catch (SQLException except)
        {
            System.err.println("Could not connect to database: " + except.toString());
        }
        catch (ClassNotFoundException except)
        {
            System.err.println("Could not locate JDBC library.");
        }
    }

    public void addEmployee(Employee employee)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(employee.getInsertSql());
            statement.close();
        }
        catch (SQLException except)
        {
            System.err.println("Could not insert new employee into table: " + except.toString());
        }
    }

    public List<Employee> selectEmployees(String sqlQuery)
    {
        List<Employee> toReturn = new ArrayList<Employee>();
        try
        {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sqlQuery);

            while (result.next())
                toReturn.add(new Employee(result));
        }
        catch (SQLException except)
        {
            System.err.println("Error retrieving employees: " + except.toString());
        }

        return toReturn;
    }

    public void close()
    {
        try
        {
            connection.close();
        }
        catch (SQLException except)
        {
            System.err.println("Could not close database connection: " + except.toString());
        }
    }

    private void initializeDatabase() throws SQLException
    {
        String schema = "CREATE TABLE STAFF " +
                        "(ID        TEXT PRIMARY KEY    NOT NULL," +
                        " NAME      TEXT                NOT NULL," +
                        " EMAIL     TEXT                NOT NULL," +
                        " AGE       INT                 NOT NULL)";

        Statement statement = connection.createStatement();
        statement.executeUpdate(schema);

        statement.close();
    }
}
