import java.sql.*;
import java.util.Scanner;

public class Query01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1) ---> driver yükle
        Class.forName("com.mysql.cj.jdbc.Driver");
       /*
        oracle.jdbc.driver.OracleDriver

        org.postgresql.Driver

        com.microsoft.sqlserver.jdbc.SQLServerDrive

        org.sqlite.JDBC
        */

        // 2) ---> Bağlantı oluştur
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/okul?serverTimezone=UTC", "root", "123456");
            // okul -> localdeki baglantı kuracagımız database'in adı

        // 3) ---> Statement    // Hibernate de session gibi düşünülebilir...
        Statement st = con.createStatement();

        //  4) ---> ResultSet  = JDBC kullanarak veri çekme işlemi sonrasında veri listelemek için
        //  ResultSet sınıfı kullanılır. Dönen değerler benzersiz olacağı için
        //   ResultSet arayüzü kullanılmış, Sınıf veriler üzerinde dolaşmak için next,
        //   first, last, previous, absolute gibi metotlara sahiptir.
        //
        //Veritabanı sütunlarında yer alan verileri almak için getVeriTipi metotları kullanılır.
        ResultSet veri = st.executeQuery("select * from ogrenciler");

        //  5) ---> sonuçları yazdır
        while (veri.next()) {   // iterator mantığı ile çalıştığı için next() kullanılıyor
            System.out.println(veri.getInt(1) + // veri.getInt("okul_no")  --> ile de olur
                    veri.getString(2) + // veri.getString("ogrenci_ismi")  --> ile de olur
                    veri.getString(3) + // veri.getString("sinif")  --> ile de olur
                    veri.getString(4));// veri.getString("cinsiyet")  --> ile de olur
        }

        Scanner scan = new Scanner(System.in);

        System.out.println("Araba Adı : ");
        String name = scan.nextLine();

        System.out.println("Araba Hızı : ");
        int speed = scan.nextInt();

        // Aşağıdaki kod çok hataya açık. bu yüzden prepared statement kullanılıyor
        String query02 ="INSERT INTO cars ( cars_name, cars_speed) VALUES ('"+ name + "'," + speed +")";
        int sonuc = st.executeUpdate(query02);
        System.out.println(sonuc);

        // Yukardaki kodun "prepared statement" ile kullanımı
        String query03 = "INSERT INTO cars (cars_name, cars_speed) VALUES (?,?)";
        PreparedStatement pt = con.prepareStatement(query03);
        pt.setString(1,name);
        pt.setInt(2,speed);
        pt.executeUpdate();



        //  6) Kapatma
        con.close();
        st.close();
        veri.close();
    }
}