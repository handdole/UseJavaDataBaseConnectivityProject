package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class MyAlbumBbsDAO {
	String url = "jdbc:mysql://localhost:3708/project?characterEncoding=utf8&serverTimezone=UTC";
	String user = "root";
	String password = "1234";
	Connection con;
	PreparedStatement ps;
	
	
	//myalbumwrite에서 게시글을 작성하고 DB에 data를 넣을때 사용되는 DAO
	//insert이기 때문에 반환값이 없다.
	public void insert(MyAlbumBbsDTO x) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);

			String sql = "insert into my_album_bbs values(?,?,?,?,?,?,?,?,?)";
			// SQL문 해석 : my_album_bbs에 DTO로 받아온 각 변수들을 insrt.
			ps = con.prepareStatement(sql);
			ps.setInt(1, x.getBbsid());
			ps.setString(2, x.getTitle());
			ps.setString(3, x.getNickname());
			ps.setDate(4, null);
			ps.setInt(5, x.getLike());
			ps.setString(6, x.getTag());
			ps.setInt(7, x.getSongcount());
			ps.setString(8, x.getAlbumcover());
			ps.setString(9, x.getContent());

			ps.executeLargeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
			}
		}
	}

	//myalbumlist 목록에 들어가 있는 myalbum관련 정보들을 불러오기 위한 DAO
	// 반환값있음. myalbum 관련 instence 를 list 형식으로 봔환 
	public ArrayList<MyAlbumBbsDTO> infomyalbumlist() {
		ArrayList<MyAlbumBbsDTO> list = new ArrayList<MyAlbumBbsDTO>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
//		2) 디비 연결
			Connection con = DriverManager.getConnection(url, user, password);

//			3)sql문 결정

			String sql = "select * from my_album_bbs ";
			PreparedStatement ps = con.prepareStatement(sql);

//			4)sql문 전송
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// rs.next() 는 커서를 아래로 내리면서
//				row의 위치를 나타내는 커서를 아래로 옮기면서 
//				그 해당 로우가 있는지 처크해주는 메소드 
//				있으면 트루 없으면 폴스
				MyAlbumBbsDTO dto = new MyAlbumBbsDTO();
				int bbsid = rs.getInt(1);
				String title = rs.getString(2);
				String nickname = rs.getString(3);
				java.sql.Timestamp time = rs.getTimestamp(4);
				int like = rs.getInt(5);
				String tag = rs.getString(6);
				int songcount = rs.getInt(7);
				String albumcover = rs.getString(8);
				String content = rs.getString(9);
				dto.setBbsid(bbsid);
				dto.setTitle(title);
				dto.setNickname(nickname);
				dto.setTime(time);
				dto.setLike(like);
				dto.setTag(tag);
				dto.setSongcount(songcount);
				dto.setAlbumcover(albumcover);
				dto.setContent(content);
				list.add(dto);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	
	//albuminner에 들어갈 정보 제목 버튼을 누르면 해당 버튼의 bbsid를 불러와서 
	//해당 게시물의 정보들을 DB에서 가져옴. myalbumbbsdto로 반환
	public MyAlbumBbsDTO innerinfo(int x) {
		MyAlbumBbsDTO dto = new MyAlbumBbsDTO();
		try {

			Class.forName("com.mysql.jdbc.Driver");
//		2) 디비 연결
			Connection con = DriverManager.getConnection(url, user, password);

//			3)sql문 결정
			//albuminner에 들어갈 정보들 불러옴.
			String sql = "select * from my_album_bbs where bbs_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, x);

//			4)sql문 전송
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// rs.next() 는 커서를 아래로 내리면서
//				row의 위치를 나타내는 커서를 아래로 옮기면서 
//				그 해당 로우가 있는지 처크해주는 메소드 
//				있으면 트루 없으면 폴스

				int bbsid = rs.getInt(1);
				String title = rs.getString(2);
				String nickname = rs.getString(3);
				java.sql.Timestamp time = rs.getTimestamp(4);
				int like = rs.getInt(5);
				String tag = rs.getString(6);
				int songcount = rs.getInt(7);
				String albumcover = rs.getString(8);
				String content = rs.getString(9);
				dto.setBbsid(bbsid);
				dto.setTitle(title);
				dto.setNickname(nickname);
				dto.setTime(time);
				dto.setLike(like);
				dto.setTag(tag);
				dto.setSongcount(songcount);
				dto.setAlbumcover(albumcover);
				dto.setContent(content);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	
	//albuminner에서 좋아요 버튼 클릭시 DB에 좋아요 숫자 누적
	public void like(int myalbumlike, int myalbumid) {
		try {
			// 1) 커넥터 설정
			Class.forName("com.mysql.jdbc.Driver");

			// 2) DB연결
			con = DriverManager.getConnection(url, user, password);

			// 3) sql문 결정
			String sql = "update my_album_bbs set my_album_like = ? where bbs_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, myalbumlike);
			ps.setInt(2, myalbumid);

			// 4) sql문 전송
			int result = ps.executeUpdate();
			if (result == 0) { // 좋아요 반영 실패시
				JOptionPane.showMessageDialog(null, "좋아요 반영 실패");
			} else { // 좋아요 반영 성공시
				// 메시지 출력
				JOptionPane.showMessageDialog(null, "좋아요♥");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
