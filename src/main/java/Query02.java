import java.sql.*;

public class Query02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Statement st = Utility.makeConnection("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/okul?serverTimezone=UTC",
                "root", "123456" );

        // SORU01   :   OĞRENCİLER TABLOSUNDAKİ ERKEK ÖĞRENCİ BİLGİLERİNİ GETİRİN

        ResultSet veri = st.executeQuery("select * from ogrenciler where cinsiyet='E'");
        while (veri.next()) {
            System.out.println(veri.getInt(1) + // veri.getInt("okul_no")  --> ile de olur
                    veri.getString(2) + // veri.getString("ogrenci_ismi")  --> ile de olur
                    veri.getString(3) + // veri.getString("sinif")  --> ile de olur
                    veri.getString(4));// veri.getString("cinsiyet")  --> ile de olur
        }

        System.out.println("============================");
        // SORU02   :   ÖĞRENCİ TABLOSUNDAKİ 9.SINIF KIZ ÖĞRENCİLERİN LİSTELERİNİ GETİRİN
        ResultSet veri2 = st.executeQuery("select * from ogrenciler where cinsiyet='K' AND sinif=9");
        while (veri2.next()) {
            System.out.println(veri2.getInt(1) + // veri.getInt("okul_no")  --> ile de olur
                    veri2.getString(2) + // veri.getString("ogrenci_ismi")  --> ile de olur
                    veri2.getString(3) + // veri.getString("sinif")  --> ile de olur
                    veri2.getString(4));// veri.getString("cinsiyet")  --> ile de olur
        }


        Utility.con.close();
        st.close();
        veri.close();
    }
}
