package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ui.Main;

public class ProjectDAO {
	String url = "jdbc:mysql://localhost:3708/project?characterEncoding=utf8&serverTimezone=UTC";
	String user = "root";
	String password = "1234";
	
	//1. cart에 있는 곡 불러오기
	public ArrayList<Cart_DTO> all() { //전체 검색하기, DTO에 담아내는 과정, 리스트가 반환됨
		ArrayList<Cart_DTO> list = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			String sql = "select * from cart"; //song_id, song_title, artist, album_cover
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(); 
			
			while (rs.next()) {
				Cart_DTO dto = new Cart_DTO();
				
				int song_id = rs.getInt(1);
				String song_title = rs.getString(2);
				String artist = rs.getString(3);
				String album_cover = rs.getString(4);
				
				dto.setSong_id(song_id);
				dto.setSong_title(song_title);
				dto.setArtist(artist);
				dto.setAlbum_cover(album_cover);
				
				list.add(dto);  //DTO에 데이터에 각 행을 담음
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//2. 결제정보 DB에 저장 (pay)
	public void insert_pay (Pay_DTO dto) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			String sql = "insert into pay values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setInt(2, dto.getPay_id());
			ps.setString(3, dto.getDate());
			ps.setInt(4, dto.getTrack_count());
			ps.setInt(5, dto.getTotal_price());
			ps.setString(6, dto.getPay_way());
			ps.setString(7, dto.getPay_company());
					
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	//3. cart에서 가져온 ArrayList를 DB의 my_song으로 옮기기
	public void insert_my_song (ArrayList<Cart_DTO> list) { //arraylist로 입력값(매개변수)을 받음. 
		//for문으로 각각의 list.get(i)에 해당하는 dto를 검색해줌
		for (int i = 0; i < list.size(); i++) {
			Cart_DTO dto = new Cart_DTO();
			dto = list.get(i); //cart에 담긴 1곡 씩 dto에 담아옴
			
			//dto의 song_id를 가지고, song테이블에서 검색
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, user, password);
				String sql = "select song_id, song_title, artist, genre, album_title, album_cover from song where song_id = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, dto.getSong_id());
				ResultSet rs = ps.executeQuery();
				
				Song_DTO dto2 = new Song_DTO(); //검색결과를 Song_dto(만들어야)에 담아야함
				if(rs.next()) { //검색 결과가 있는지 체크해주는 메서드
					
					int song_id = rs.getInt(1);
					String song_title = rs.getString(2);
					String artist = rs.getString(3);
					String genre = rs.getString(4);
					String album_title = rs.getString(5);
//					int song_like = rs.getInt(6);
//					int price = rs.getInt(7);
					String album_cover = rs.getString(6);
					
					dto2.setSong_id(song_id);
					dto2.setSong_title(song_title);
					dto2.setArtist(artist);
					dto2.setGenre(genre);
					dto2.setAlbum_title(album_title);
//					dto2.setSong_like(song_like);
//					dto2.setPrice(price);
					dto2.setAlbum_cover(album_cover);
				}
					//Song_DTO를 MySong으로 전송
				String sql2 = "insert into my_song values (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.setInt(1, dto2.getSong_id());
				ps2.setString(2, dto2.getSong_title()); 
				ps2.setString(3, dto2.getArtist());
				ps2.setString(4, dto2.getGenre());
				ps2.setString(5, dto2.getAlbum_title());
//				ps2.setInt(6, dto2.getSong_like());
//				ps2.setInt(7, dto2.getPrice());
				ps2.setString(6, dto2.getAlbum_cover());
				ps2.setString(7, Main.getLogId());

				ps2.executeUpdate(); //my_song에 insert시키기
				
			} catch (Exception e) {
			}
		}
	}
	
	
	//4. cart의 DB삭제하기
	public void delete(ArrayList<Cart_DTO> list) {
		int return2 = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			String sql = "delete from cart"; 
			PreparedStatement ps = con.prepareStatement(sql);
			return2 = ps.executeUpdate(); //update와 query의 차이?
		} catch (Exception e) {
		}
		System.out.println(return2);
	}
	
	
	//5. my_song에 있는 곡 불러오기
		public ArrayList<MySong_DTO> my_song() { //전체 검색하기, DTO에 담아내는 과정, 리스트가 반환됨
			ArrayList<MySong_DTO> list = new ArrayList<>();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, user, password);
				String sql = "select song_title, artist, genre, album_title, album_cover from my_song where id = ?";
				
				System.out.println(Main.getLogId());
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1,Main.getLogId());
				
				ResultSet rs = ps.executeQuery(); 
				
				while (rs.next()) { //검색 결과가 있는지 체크해주는 메서드
					MySong_DTO dto = new MySong_DTO();
					
//					int song_id = rs.getInt(1);
					String song_title = rs.getString(1);
					String artist = rs.getString(2);
					String genre = rs.getString(3);
					String album_title = rs.getString(4);
//					int song_like = rs.getInt(6);
//					int price = rs.getInt(7);
					String album_cover = rs.getString(5);
					
					
//					dto.setSong_id(song_id);
					dto.setSong_title(song_title);
					dto.setArtist(artist);
					dto.setGenre(genre);
					dto.setAlbum_title(album_title);
//					dto.setSong_like(song_like);
//					dto.setPrice(price);
					dto.setAlbum_cover(album_cover);
					dto.setId(Main.getLogId());
					
					list.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
	
	
			
		}
	
	

