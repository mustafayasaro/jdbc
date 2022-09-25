package jdbc;

import java.sql.*;

public class PreparedStatemen01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "Istanbul123");
        Statement st = con.createStatement();

        // 1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        // 1. Adim : Prepared statement query'sini olustur
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";


        // 2. Adim: PreparedStatement obkesini olustur
        PreparedStatement pst1 = con.prepareStatement(sql1);


        // 3. Adim: set....()  method'ari ile ? yerine deger atayalim
        pst1.setInt(1, 999);
        pst1.setString(2, "IBM");


        // 4. Adim: Execute query
        int updateRowSayisi = pst1.executeUpdate();
        System.out.println("updateRowSayisi = " + updateRowSayisi);


        String sql2 = "SELECT * FROM companies";

        ResultSet result1 = st.executeQuery(sql2);
        while (result1.next()) {
            System.out.println(result1.getInt("company_id") + " - " + result1.getString("company") + " - " + result1.getInt("number_of_employees"));
        }


        // GOOGLE icin degisiklik
        pst1.setInt(1, 15000);
        pst1.setString(2, "GOOGLE");


        // 4. Adim: Execute query
        int updateRowSayisi2 = pst1.executeUpdate();
        System.out.println("updateRowSayisi = " + updateRowSayisi);


        String sql3 = "SELECT * FROM companies";

        ResultSet result2 = st.executeQuery(sql3);
        while (result2.next()) {
            System.out.println(result2.getInt("company_id") + " - " + result2.getString("company") + " - " + result2.getInt("number_of_employees"));
        }


        //2. Örnek: "SELECT * FROM <table name>" query'sini prepared statement ile kullanın.
        /*String sql4 = "SELECT * FROM ?";
        PreparedStatement pst2 = con.prepareStatement(sql4);



        ResultSet result4 = pst2.executeQuery();
        while (result4.next()){
            System.out.println(result4.getInt("company_id") + " - " + result4.getString("company") + " - " + result4.getInt("number_of_employees"));        }
    */

        System.out.println("============================");
        readData(con, "companies");

        con.close();;
        st.close();

    }
    // Bir tablonun istenilen data'sini prepared stattement ile cagirmak icin kullanilan method
    public static void readData(Connection con, String tableName) {
        try {
            String query = String.format("SELECT * FROM %" + tableName);
            // format() methodu dinamik String olusturmak icindir

            // SQL query'yi calistir
            Statement statement = con.createStatement();
            ResultSet result4 = statement.executeQuery(query); // Data'yi cagirip ResultSet konteynirina koyuyoruz
            while (result4.next()){ // Tum data'yi cagiriyoruz
                System.out.println(result4.getInt("company_id") + " - " + result4.getString("company") + " - " + result4.getInt("number_of_employees"));        }
        } catch (Exception e){
            System.out.println(e);
        }


    }
}
