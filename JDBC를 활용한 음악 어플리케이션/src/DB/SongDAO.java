
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SongDAO {
	String url = "jdbc:mysql://localhost:3708/project?characterEncoding=utf8&serverTimezone=UTC";
	String user = "root";
	String password = "1234";

	// MyAlbumwrite 검색기능에 들어가는 DAO 메소드 생성
	public ArrayList<SongDTO> list(String x) {
		// 매개변수 (파라메터 , parameter ) , 지역변수

		ArrayList<SongDTO> list = new ArrayList<SongDTO>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			2) 디비 연결
			Connection con = DriverManager.getConnection(url, user, password);

//				3)sql문 결정

			String sql = "select song_title , album_cover , artist , song_id from song where song_title like ? or artist like ? order by artist";
			// x라는 스트링값을 받오고 SQL 문은 like 문법을 사용하여 song_title이나 artist에 x를 포함하고 있는 모든 레코드를
			// 갖고옴.
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + x + "%"); // '%x%' 가 들어가야 하기 때문에 %를 붙혀서 넣어줌.
			ps.setString(2, "%" + x + "%");

//				4)sql문 전송
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// rs.next() 는 커서를 아래로 내리면서
//					row의 위치를 나타내는 커서를 아래로 옮기면서 
//					그 해당 로우가 있는지 처크해주는 메소드 
//					있으면 트루 없으면 폴스
				SongDTO dto = new SongDTO(); // DB에서 받아온 데이터를 UI로 보내기위해 DTO생성
				String songtitle = rs.getString(1);
				String albumcover = rs.getString(2);
				String artist = rs.getString(3);
				int songid = rs.getInt(4);
				dto.setAlbumcover(albumcover);
				dto.setSongtitle(songtitle);
				dto.setArtist(artist);
				dto.setSongid(songid);
				list.add(dto); // list 라는 arraylist에 dto 형식으로 추가해줌

			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (list.size() == 0) {
			JOptionPane.showMessageDialog(null, "검색결과가 없습니다.");
		}
		return list; // dao 실행하면 list를 반환

	}

	// myalbumwrite에서 만든 게시글에 수록곡들을 표시하기 위한 dto
	// 해당 songid에 맞는 노래 제목 ,앨범커버, 가수레코드를 불러옴.
	public SongDTO innersonglist(int x) {
		SongDTO dto = new SongDTO();
		try {
			Class.forName("com.mysql.jdbc.Driver");
//		2) 디비 연결
			Connection con = DriverManager.getConnection(url, user, password);

//			3)sql문 결정

			String sql = "select song_title , album_cover , artist from song where song_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, x);

//			4)sql문 전송
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// rs.next() 는 커서를 아래로 내리면서
//				row의 위치를 나타내는 커서를 아래로 옮기면서 
//				그 해당 로우가 있는지 처크해주는 메소드 
//				있으면 트루 없으면 폴스
				String songtitle = rs.getString(1);
				String albumcover = rs.getString(2);
				String artist = rs.getString(3);
				dto.setAlbumcover(albumcover);
				dto.setSongtitle(songtitle);
				dto.setArtist(artist);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;

	}

}