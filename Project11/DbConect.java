import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConect {
    public void addToDb(StudentModel student) throws SQLException {
        Connection connection = null;
        Statement stmt = null;

        try {
            // Get Connection and Statement Object
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "rootroot");
            stmt = connection.createStatement();

            String value = "VALUES (" + student.getId() + ",'" + student.getName() + "','" + student.getEmail() + "','" + student.getAddress()
                    + "'," + student.getEnrYear() + ",'" + student.getProgram() + "')";
            // Execute JDBC commands
            stmt.executeUpdate("INSERT INTO student (id, name, email, address, enrolYear, program)"
                    + value);
        } catch(SQLException ex) {
            throw ex;
        } finally {
            try{
                // Close connection and statement
                if(stmt != null)
                    stmt.close();
                if(connection != null)
                    connection.close();
            } catch (Exception ex) {
                throw ex;
            }
        }
    }
    public StudentModel search(int id) throws SQLException {
        StudentModel aux = new StudentModel();
        Connection connection = null;
        Statement stmt = null;

        try {
            // Get Connection and Statement Object
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "rootroot");
            stmt = connection.createStatement();
            String query = "SELECT * FROM student WHERE id = " + id;
            // Execute JDBC commands
            ResultSet resultSet = stmt.executeQuery(query);
            if(resultSet.next()) {
                aux.setStudent(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6));
            }else{
                aux.setStudent(-1, "","","",0,"");
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try {
                // Close connection and statement
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
                return aux;
            } catch (Exception ex) {
                throw ex;
            }
        }
    }
    public boolean update(int id, StudentModel student) throws SQLException {
        Connection connection = null;
        Statement stmt = null;
        StudentModel aux = new StudentModel();
        aux = search(id);
        if(aux.getId() != -1) {
            try {
                // Get Connection and Statement Object
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "rootroot");
                stmt = connection.createStatement();
                String query = "UPDATE student SET name = '" + student.getName() + "',email = '" + student.getEmail() +
                        "', address = '" + student.getAddress() + "', enrolYear = " + student.getEnrYear() + ", program = '" + student.getProgram() +
                        "' WHERE id = " + id;
                // Execute JDBC commands
                stmt.executeUpdate(query);

            } catch (SQLException ex) {
                throw ex;
            } finally {
                try {
                    // Close connection and statement
                    if (stmt != null)
                        stmt.close();
                    if (connection != null)
                        connection.close();
                    return true;
                } catch (Exception ex) {
                    throw ex;
                }
            }
        }else{
            return false;
        }
    }
    public boolean delete(int id) throws SQLException {
        Connection connection = null;
        Statement stmt = null;
        StudentModel aux = new StudentModel();
        aux = search(id);
        if(aux.getId() != -1) {
            try {
                // Get Connection and Statement Object
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "rootroot");
                stmt = connection.createStatement();
                String query = "DELETE FROM student WHERE id = " + id;
                // Execute JDBC commands
                stmt.executeUpdate(query);

            } catch (SQLException ex) {
                throw ex;
            } finally {
                try {
                    // Close connection and statement
                    if (stmt != null)
                        stmt.close();
                    if (connection != null)
                        connection.close();
                    return true;
                } catch (Exception ex) {
                    throw ex;
                }
            }
        }else{
            return false;
        }
    }
    public List<StudentModel> totalStudents() throws SQLException {
        Connection connection = null;
        Statement stmt = null;
        List<StudentModel> students = new ArrayList<StudentModel>();
        StudentModel aux;
            try {
                // Get Connection and Statement Object
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "rootroot");
                stmt = connection.createStatement();
                String query = "SELECT * FROM student";
                // Execute JDBC commands
                ResultSet resultSet = stmt.executeQuery(query);
                while ( resultSet.next() )
                {
                    aux = new StudentModel();
                    aux.setStudent(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                            resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6));
                    students.add(aux);
                }
            } catch (SQLException ex) {
                throw ex;
            } finally {
                try {
                    // Close connection and statement
                    if (stmt != null)
                        stmt.close();
                    if (connection != null)
                        connection.close();
                    return students;
                } catch (Exception ex) {
                    throw ex;
                }
            }
    }
}
