import java.sql.SQLException;
import java.sql.Statement;

public class Query04 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Statement st = Utility.makeConnection("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/batch60_61?serverTimezone=UTC",
                "root", "123456");
        // SORU : işciler adında bir tablo oluşturunuz, id int, birim varchar(10), maas int
        String sorgu = "create table isciler (id int, birim varchar(10), maas int)";
        st.execute(sorgu);
        System.out.println("işciler tablosu oluşturuldu.");

        // SORU : isciler tablosunu siliniz
        st.execute("drop table isciler");
        System.out.println("isciler tablosu silindi");



    }
}
