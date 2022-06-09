import java.sql.SQLException;
import java.sql.Statement;

public class Query05 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Statement st = Utility.makeConnection("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/batch60_61?serverTimezone=UTC",
                "root", "123456");

            // SORU01 : Bölümler tablosunda yeni bir kayıt (80, 'ARGE', 'ISTANBUL')

        // yapılan işlemler sonrası geribildirim almak istiyorsak bu method kullanıulır; kaydetti mi , sildi mi  gibi..
        // ınsert / update / delete  GİBİ METHODLAR İÇİN KULLANILIR. Dönüş değeri int oldugu için
        // 1 ise başarılı, 0 ise başarısız olmuştur
        st.executeUpdate("insert into bolumler values (80,'ARGE', 'ISTANBUL'");


        // SORU02: birden fazla kayıt ekleme

                // 1.YOL :
        String [] veri1 = { "insert into bolomler values(95,'YEMEKHANE', 'ISTANBUL')",
                    "insert into bolumler values(85,'OFIS1', 'ANKARA')",
                    "insert into bolumler values(75,'OFIS2'.'VAN')"};
        int count = 0;
        for (String each: veri1) {
            count += st.executeUpdate(each);  // executeUpdate metodu her çağrıldığında başarılı ise 1 döndürür,
            //                                count değişkeni ile toplam kaç adet veri girildiğini tutmuş oluyoruz
        }
        System.out.println(count + " DATA EKLENDİ.");

                // 2.YOL :     addBatch() : SQL sorduları grupluyor
                //             executeBatch() : addBatch ile grupğlanan sorguları tek seferde Database e gönderiyor
        String [] veri2 = { "insert into bolomler values(95,'YEMEKHANE', 'ISTANBUL')",
                "insert into bolumler values(85,'OFIS1', 'ANKARA')",
                "insert into bolumler values(75,'OFIS2'.'VAN')"};
        for (String each: veri2) {
            st.addBatch(each);
        }
        st.executeBatch();

        // executeBatch() faydaları : her bir insert işlemi için ayrı statement açıldıgını düşünürsek bu hem veri kaybı
        // hem de kaynakları gereksiz kullanilmasına sebeb olur, bunu önlemek için benzer bir şekilde hibernate de
        // commit fonksiyonu  kullanılıyor ( bekleyen tüm değişiklikleri kalıcı hale getirmek için )
        //





    }
}
