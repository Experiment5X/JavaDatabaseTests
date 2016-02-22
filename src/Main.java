/**
 * Created by adam on 2/21/16.
 */
public class Main
{
    public static void main(String[] args)
    {
        EmployeeDatabase database = new EmployeeDatabase();

        // Employee employee = new Employee("asd2", "Billy Bob", "bbob@gmail.com", 25);
        // database.addEmployee(employee);

        for (Employee e : database.selectEmployees("SELECT * FROM STAFF"))
            System.out.println(e);
        database.close();
    }
}
