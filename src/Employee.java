/**
 * Created by adam on 2/21/16.
 */
import java.sql.*;


public class Employee
{
    private String id;
    private String name;
    private String email;
    private int age;

    public Employee(String id, String name, String email, int age)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Employee(ResultSet resultSet) throws SQLException
    {
        this.id = resultSet.getString("id");
        this.name = resultSet.getString("name");
        this.email = resultSet.getString("email");
        this.age = resultSet.getInt("age");
    }

    public String getInsertSql()
    {
        String sqlCommand = "INSERT INTO STAFF (ID, NAME, EMAIL, AGE) VALUES ";

        String values = "( '" + getId() + "', " +
                "'" + getName() + "', " +
                "'" + getEmail() + "', " +
                    getAge() + ");";

        return sqlCommand + values;
    }

    public String toString()
    {
        return "[" + getId() + "] " + getName();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
