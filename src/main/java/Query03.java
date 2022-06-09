import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query03 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Statement st = Utility.makeConnection("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/batch60_61?serverTimezone=UTC",
                "root", "123456");

        // SORU01 :  Bölümler tablosundan tüm kayıtları listeleyiniz.

        ResultSet tablo1 = st.executeQuery("select * from bolumler");

        while (tablo1.next()) {
            System.out.println(tablo1.getInt(1) + " " + tablo1.getString(2) + " " + tablo1.getString(3));
        }

        System.out.println("======SORU02=========================");

        // SORU02 : Satış ve muhasebe bölümlerinde çlışan personelin isimlerini ve maaslarını, maas ters sıralı listeleyiniz

        ResultSet tablo2 = st.executeQuery("select personel_isim, maas from personel where bolum_id in(10,30) order by maas desc");
        while (tablo2.next()) {
            System.out.println(tablo2.getString(1) + "  " + tablo2.getInt(2));
        }
        System.out.println("=====SORU03=========================");

        // Soru3: Tüm bölümlerde çalışan personel isimlerini, bölüm isimlerini ve maaşlarını,
        // bölüm ve maas sıralı listeleyiniz.
        // NOT: Çalışanı olamasa bile bölüm ismi gösterilmelidir.
        ResultSet tablo3 = st.executeQuery("select p.personel_isim, b.bolum_isim, p.maas from bolumler b " +
                "left join personel p " +
                "on p.bolum_id = b.bolum_id " +
                "order by b.bolum_isim, p.maas ");

        while(tablo3.next()){
            System.out.println(tablo3.getString(1)+ "  " + tablo3.getString(2) + "  " + tablo3.getInt(3));
        }
    }
}
