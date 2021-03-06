package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HRSearchEmpoyee {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      String name = sc.nextLine();
      Connection conn = null;
      Statement stmt = null;
      System.out.println("ehfdk");
      try {
         //1. 드라이버 로딩
    	  Class.forName("oracle.jdbc.driver.OracleDriver");
         
         //2. connection 생성
         conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
         
         //3 Statement 객체 생성
         stmt=conn.createStatement();
      
           String sql = " select first_name,"+
                        " last_name,"+ 
                      " email,"+
                        " phone_number,"+ 
                      " hire_date"+ 
                        " from employees"+
                      " where first_name = '"+ name+"'"+
                        " or last_name= '"+name+"'";
           
           ResultSet rs = stmt.executeQuery(sql);
         while(rs.next()){
            String firstName = rs.getString(1);
            String lastName = rs.getString(2);
            String email = rs.getString(3);
            String phoneNumber = rs.getString(4);
            String hireDate = rs.getString(5);
            
            System.out.println(firstName+":"+lastName+":"+email+":"+phoneNumber+":"+hireDate);
         }
         System.out.println("연결");                    
      } catch (ClassNotFoundException e) {
         System.out.println("JDBC 파일을 찾을수 없습니다.");
      } catch (SQLException s) {
         System.out.println("db오류" + s);
      } finally {
         try {
            if (stmt != null) {
               stmt.close();
            }
            if (conn != null) {
               conn.close();
            }
         } catch (SQLException s) {

         }
      }
   }
}