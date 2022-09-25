package jdbc;

import java.sql.Connection;

public class DBWork_Main {
    public static void main(String[] args) {
        // DBWork objesini olustur
        DBWork db = new DBWork();

        // Connection fonksiyonunu cagir
        Connection con =db.connect_to_db("techproed", "postgres", "Istanbul123");

        // Yeni table olusturma methodunu cagir
        db.createTable(con, "emplyees");


    }

}
