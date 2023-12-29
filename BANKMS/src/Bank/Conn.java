package Bank;

import java.sql.*;

public class Conn {


    Connection c;
    Statement s;
    public Conn() {

        try {
            c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem","root","KiR@pri#122");
            s = c.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
