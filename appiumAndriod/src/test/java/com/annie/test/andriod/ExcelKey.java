package com.annie.test.andriod;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ExcelKey {
private final String DRIVERCLASSNAME="sun.jdbc.odbc.JdbcOdbcDriver";
//private final String DRIVERCLASSNAME="net.ucanaccess.jdbc.UcanaccessDriver";
private Connection con=null;
private String url="jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=D:/workspace/appiumAndriod/src/KeyWordDriver/AndroidTest.xls;READONLY=FALSE";
//private String url="jdbc:ucanaccess://D:\\workspace\\appiumAndriod\\src\\KeyWordDriver\\AndroidTest.xls"; //jdk8 need use ucanaccess for odbc brige

public ExcelKey() throws Exception{
Class.forName(DRIVERCLASSNAME);
con=DriverManager.getConnection(url);
}
public void read() throws SQLException{
	WebDriver driver=new FirefoxDriver();
	driver.navigate().to("http://10.0.0.88:8080/bkredcoil/");
	Statement statement=con.createStatement();
	ResultSet result=statement.executeQuery("select * from [ContactManager$]");
	while(result.next()){
		System.out.println("Step="+result.getInt(1));
		System.out.println("ObjectKeyword="+result.getString("ObjectKeyword"));
		System.out.println("TestData="+result.getString("TestData"));
		System.out.println("LocationManner="+result.getString("LocationManner"));
		System.out.println("Comment="+result.getString("Comment"));
		String Step=Integer.toString(result.getInt("Step")); 
		String KeyWord =result.getString("KeyWord");
		String Data=result.getString("Data");
		String Object=result.getString("Object");
		String Object_by=Object.split(";")[0].toString();
		String Object_Desc=Object.split(";")[1].toString();
		String Comment=result.getString("Comment");
		System.out.println("Step:"+Step);
		if(KeyWord.equals("nput")){
			if (Object_by.equals("id")){
				
				driver.findElement(By.id(Object_Desc)).sendKeys(Data);
			}
			else if(Object_by.equals("xpath")){
				driver.findElement(By.xpath(Object_Desc)).sendKeys(Data);
			}
			System.out.println("Input data"+Data+"in:"+Object_Desc);
		}
		else if (KeyWord.equals("Click"))
		{
			
			if(Object_by.equals("id"))
			{
			  driver.findElement(By.id(Object_Desc)).click();
			}
			else if(Object_by.equals("xpath")){
				driver.findElement(By.xpath(Object_Desc)).click();
			}
			System.out.println("Click On"+Object_Desc);
		}
		else{
			System.out.println("No Match Keyword Found!");
		}	
	}
	result.close();
	//driver.close();
	
}
public void close() throws SQLException{
	if(con !=null){
		con.close();
	}
}

public static void main(String[] args) throws Exception
 {
   ExcelKey KeyWordDriver=new ExcelKey();
   KeyWordDriver.read();
   KeyWordDriver.close();
   }

}
