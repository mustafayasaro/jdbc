package jdbc;

import java.sql.*;

public class CallableStatement01 {

    /*
    Java'da metod'lar return type sahibi olsa da, void olsa da return olarak adlandirilir
    SQL'de ise data return ediyorsa "functio" denir. Return yapiyorsa "procedure" diye adlandirilir
    */

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "Istanbul123");
        Statement st = con.createStatement();

        String sql1 = "CREATE OR REPLACE FUNCTION toplamaF(x NUMERIC, y NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$";

        //2. Adım: Fonksiyonu çalıştır.
        st.execute(sql1);

        //3. Adım: Fonksiyonu çağır.
        CallableStatement cst1 = con.prepareCall("{? = call toplamaF(?, ?)}");

        //4. Adım: Return için registerOutParameter() methodunu, parametreler için set() methodlarından uygun olanları kullan.
        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2,15);
        cst1.setInt(3,25);

        //5. Adım: Çalıştırmak için execute() methodunu kullan.
        cst1.execute();

        //6. Adım: Sonucu çağırmak için return data tipine göre "get" methodlarından uygun olanı kullan.
        System.out.println(cst1.getBigDecimal(1));


        // 2. Ornek: 2. Örnek: Koninin hacmini hesaplayan bir function yazın.

        String sql2 = "CREATE OR REPLACE FUNCTION koniHacim(r NUMERIC, h NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14*r*r*h/3 ;\n" +
                "\n" +
                "END\n" +
                "$$";

        //2. Adım: Fonksiyonu çalıştır.
        st.execute(sql2);

        //3. Adım: Fonksiyonu çağır.
        CallableStatement cst2 = con.prepareCall("{? = call koniHacim(?, ?)}");

        //4. Adım: Return için registerOutParameter() methodunu, parametreler için set() methodlarından uygun olanları kullan.
        cst2.registerOutParameter(1, Types.NUMERIC);
        cst2.setInt(2,3);
        cst2.setInt(3,5);

        //5. Adım: Çalıştırmak için execute() methodunu kullan.
        cst2.execute();

        //6. Adım: Sonucu çağırmak için return data tipine göre "get" methodlarından uygun olanı kullan.
        System.out.println(cst2.getBigDecimal(1));

    }
}
