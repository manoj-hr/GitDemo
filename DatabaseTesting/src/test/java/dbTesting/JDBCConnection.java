package dbTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;

public class JDBCConnection {
    static int id;
    static String deptName,loc;
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        JdbcConnection();
        loginTest(deptName,loc);
    }
    public static void loginTest(String username,String password){
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\selenium-java-3.141.59\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.get("https://login.salesforce.com/");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("pw")).sendKeys(password);

    }
    public static void JdbcConnection() throws ClassNotFoundException, SQLException {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?user=root&password=admin");

            st=con.createStatement();

            rs=st.executeQuery("select * from dept where loc='mysuru';");
            while(rs.next()){
                id=rs.getInt(1);
                deptName=rs.getString(2);
                loc=rs.getString(3);

            }
        }
         finally{
            rs.close();
            st.close();
            con.close();
        }

    }
}
