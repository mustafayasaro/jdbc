package jdbc;

import java.sql.*;

public class Execute_02 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");

        //2. Adım: Database'e bağlan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "Istanbul123");
        Statement st = con.createStatement();

        //1. Example:  region id'si 1 olan "country name" değerlerini çağırın.
        String sql1 = "SELECT country_name from countries WHERE region_id = 1";
        boolean result1 = st.execute(sql1);
        System.out.println(result1); // data cagirdigimiz icin true verir


        // Record'lari gormek icin executeQuery() method'unu kullanmaliyiz
        ResultSet r1 = st.executeQuery(sql1);

        while (r1.next()) {
            System.out.println(r1.getString("country_name"));
        }


        System.out.println("=====================================");

        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.

        String sql2 = "SELECT country_name, country_id from countries WHERE region_id > 2";
        boolean result2 = st.execute(sql2);
        System.out.println(result2); // data cagirdigimiz icin true verir


        // Record'lari gormek icin executeQuery() method'unu kullanmaliyiz
        ResultSet r2 = st.executeQuery(sql2);

        while (r2.next()) {
            System.out.println(r2.getString("country_id") + "-->" + r2.getString("country_name"));
        }

        System.out.println("=========================");

        //3.Example: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.

        String sql3 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet result3 = st.executeQuery(sql3);
        ResultSet r3 = st.executeQuery(sql3);

        while (r3.next()) {
            System.out.println(r3.getInt("company_id") + "--" + r3.getString("company") + "--" + r3.getInt("number_of_employees"));
        }

        con.close();
        st.close();

    }
}
