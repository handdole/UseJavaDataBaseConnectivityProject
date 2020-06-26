package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class DBconnect {
	String url = "jdbc:mysql://localhost:3708/virus";
	String user = "root";
	String password = "1234";



	public void insert(String id , String pw , String name , String tel) {    
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("1. 커넥터 설정 ok...");
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("2. db연결 ok...");
			
			
			
			String sql = "insert into member values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,id);
			ps.setString(2,pw);
			ps.setString(3,name);
			ps.setString(4,tel);
			System.out.println("3. sql 문 결정 ok...");
			
			ps.executeLargeUpdate();
			System.out.println("4.sql문 전송 ok...");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("1. 커넥터 설정 ok...");
			DriverManager.getConnection(url, user, password);
			System.out.println("2. db연결 ok...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("1. 커넥터 설정 ok...");
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("2. db연결 ok...");
			String id = JOptionPane.showInputDialog("삭제하고 싶은 아이디");
			String sql = "delete from member where id = ? "; // 물음표 뭐 지울지 모르겠다 (충격스럽게 문법임)
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);  // (몇번째 물음표에 , 어떤걸 넣을꺼니) ?
			System.out.println("3. sql 문 결정 ok...");
			
			ps.executeLargeUpdate();
			System.out.println("4.sql문 전송 ok...");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

}
