package com.keunwon.chapter12;

import java.sql.*;

public class DBConnection {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@serverIP:1521:SID", "ID", "Password");
            ps = con.prepareStatement("SELECT  ... where id=?");

            ps.setString(1, "id");
            rs = ps.executeQuery();
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 load fail");
            throw e;
        } catch (SQLException e) {
            System.out.println("Connection Fail");
            throw e;
        } finally {
            if (rs != null) { rs.close(); }
            if (ps != null) { ps.close(); }
            if (ps != null) { con.close(); }
        }
    }
}
