package com.example.dictionary;

import java.sql.*;

public class DictionaryManager {

    //Đường dẫn database
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\src\\main\\java\\com\\example\\dictionary\\dict_hh.db";

    /** Kết nối Database. */
    public static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(DB_URL);
    }

    /** Tìm kiếm từ. */
    public static String wordLookUp(String word_target) {
        String c = "";

        try {
            Connection con = connect();
            String sql = "select * from av where word = '" + word_target + "'";
            //Truy vấn theo tham số sql
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()) {
                c = rs.getString("description");
            }

            con.close();
            rs.close();
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    /** Them tu vao database */
    public static boolean insertData(String word_target, String word_explain) throws SQLException, ClassNotFoundException {
        Connection con = connect();
        String kq = wordLookUp(word_target);
        if ("".equals(kq)) {
            try {
                String sql = "insert into av(word, description) values(?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, word_target);
                ps.setString(2, word_explain);
                int rowAffected = ps.executeUpdate();
                if (rowAffected >= 1) {
                    con.close();
                    ps.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /** Sửa từ. */
    public static boolean updateData(String word_target, String word_explain) throws SQLException, ClassNotFoundException {
        Connection con = connect();
        String kq = wordLookUp(word_target);
        if (!"".equals(kq)) {
            try {
                String sql = "UPDATE av SET description = ? WHERE word == ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, word_explain);
                ps.setString(2, word_target);
                int rowAffected = ps.executeUpdate();
                if (rowAffected >= 1) {
                    con.close();
                    ps.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /** Xóa từ. */
    public static boolean deleteData(String word_target) throws SQLException, ClassNotFoundException {
        Connection con = connect();
        String kq = wordLookUp(word_target);
        if (!"".equals(kq)) {
            try {
                String sql = "DELETE FROM av WHERE word == ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, word_target);
                int rowAffected = ps.executeUpdate();
                if (rowAffected >= 1) {
                    con.close();
                    ps.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}


