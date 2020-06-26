package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {
	String url = "jdbc:mysql://localhost:3708/project?characterEncoding=utf8&serverTimezone=UTC";
	String user = "root";
	String password = "1234";

	// 회원가입시 적혀진 정보를 DB에 insert하기 위한 DAO
	// insert이기 때문에 반환값없음.
	public void insert(MemberDTO x) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);

			String sql = "insert into member values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, x.getId());
			ps.setString(2, x.getPw());
			ps.setString(3, x.getName());
			ps.setString(4, x.getTel());
			ps.setString(5, x.geteMail());
			ps.setString(6, x.getNickname());
			// UI에서 DTO로받아온 각각의 값들을 insert문을 통해 DB에 넣어줌.

			ps.executeLargeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원가입시 유저가 사용하려는 아이디가 DB에 있는지 없는지 판단하기위해 사용되는 DAO
	// 아이디가 중복되는지 안되는지 UI에서 확인하기 위해 id를 반환함.
	public String idcheck(MemberDTO dto) {
		// 매개변수 (파라메터 , parameter ) , 지역변수
		System.out.println("회원가입 처리하다.");
		String id = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
//		2) 디비 연결
			Connection con = DriverManager.getConnection(url, user, password);

//			3)sql문 결정

			String sql = "select id from member where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			// UI에서 받아온 아이디와 같은 아이디가 있는지 SQL문을 통해 DB에서 확인.

//			4)sql문 전송
			ResultSet rs = ps.executeQuery();
			if (rs.next()) { // 검색결과가 있는지 보여주는 클래스
				id = rs.getString(1);

			}

		} catch (Exception e) {
		}
		return id; // 값이 있으면 id값이 나오고 없으면 위에 선언해준 "" 값을 나옴.

	}

	// 로그인시 유저의 아이디와 비밀번호가 맞는지 판단하기 위한 DAO
	// in와 pw가 일치하는지 확인해야 하기 떄문에 id 그리고 pw를 반환함.
	public MemberDTO logincheck(MemberDTO dto) {
		// 매개변수 (파라메터 , parameter ) , 지역변수
		System.out.println("회원가입 처리하다.");
		MemberDTO dto2 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//		2) 디비 연결
			Connection con = DriverManager.getConnection(url, user, password);

//			3)sql문 결정

			String sql = "select id , pw from member where id = ? and pw = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId()); // UI에서 받아온 id값
			ps.setString(2, dto.getPw()); // UI에서 받아온 pw값
			// SQL문 해석 id가 dto.getid() 이고 비빌먼호가 dto.getpw()인 id와 pw를 member Table에서 불러와.

//			4)sql문 전송
			ResultSet rs = ps.executeQuery();
			if (rs.next()) { // 검색결과가 있는지 보여주는 클래스
								// 아이디와 비밀번호가 일치하는것은 무조건 1개가 나와야함 그래서 if 사용
								// (이미 회원가입에서 아이디 중복통제.)
				dto2 = new MemberDTO();
				String id = rs.getString(1);
				String pw = rs.getString(2);

				dto2.setId(id);
				dto2.setPw(pw);
			}

		} catch (Exception e) {
		}
		return dto2; // id와 pw가 맞으면 그에 맞는 id,pw가 dto2에 들어가고 아니면 null값 return

	}

	// 회원수정시 유저의 이름 ,닉네임, 이메일,전화번호 같은 정보들을 수정하기위한 DAO
	// update이기 때문에 반환값없음.
	public void myInfoUpdate(MemberDTO dto) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);

			String sql = "update member set name = ? , nickname = ? , tel = ? , e_mail = ? where id = ?";
			// SQL문 해석 : member 테이블에 id가 dto.getid()인 테이블 에서
			// name은 dto.getName(),nickname은 dto.getNickname(), 전화번호는 dto.getTel() , 이메일은
			// dto.geteMail()로 update.
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getNickname());
			ps.setString(3, dto.getTel());
			ps.setString(4, dto.geteMail());
			ps.setString(5, dto.getId());

			ps.executeLargeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원탈퇴시 해당 id에 해당되는 data를 지우기위한 DAO
	// delete이기 때문에 반환값없음.
	public void delete(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);

			String sql = "delete from member where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, id);

			ps.executeLargeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원탈퇴 및 비빌번호 변경을 위해 유저 아이디에 맞는 비밀번호 확인을 위한 DAO
	// 비밀번호가 일치하는지 UI에서 확인해야하기 때문에 반환값있음.
	public String pwcheck(String id) {
		String pw = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
//		2) 디비 연결
			Connection con = DriverManager.getConnection(url, user, password);

//			3)sql문 결정

			String sql = "select pw from member where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);

//			4)sql문 전송
			ResultSet rs = ps.executeQuery();
			if (rs.next()) { // 검색결과가 있는지 보여주는 클래스
				pw = rs.getString(1);

			}

		} catch (Exception e) {
		}
		return pw;

	}

	// 회원수정시 본인의 회원정보를 보기위해 DB에서 data를 불러오기 위한 DAO
	// 회원수정에서 본인의 수정전 회원정보를 모두 보여주어야 하기 때문에 memberDTO로 반환
	public MemberDTO myinfo(MemberDTO dto) {

		MemberDTO dto2 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//		2) 디비 연결
			Connection con = DriverManager.getConnection(url, user, password);

//			3)sql문 결정

			String sql = "select id , name , tel , nickname , e_mail , pw  from member where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());

//			4)sql문 전송
			ResultSet rs = ps.executeQuery();
			if (rs.next()) { // 검색결과가 있는지 보여주는 클래스
				dto2 = new MemberDTO();
				String id = rs.getString(1);
				String name = rs.getString(2);
				String tel = rs.getString(3);
				String nickname = rs.getString(4);
				String email = rs.getString(5);
				String pw = rs.getString(6);

				dto2.setId(id);
				dto2.setName(name);
				dto2.setNickname(nickname);
				dto2.seteMail(email);
				dto2.setTel(tel);
				dto2.setPw(pw);

			}

		} catch (Exception e) {
		}
		return dto2;
	}

	// 비밀번호 변경을 위한 DAO
	// update이기때문에 반환값없음.
	public void pwupdate(MemberDTO dto) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);

			String sql = "update member set pw = ? where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getPw());
			ps.setString(2, dto.getId());

			ps.executeLargeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// albuminner에서 작성자의 닉네임을 불러오기 위한 DAO
	// 앨범 작성자의 닉네임을 라벨에 표시해야되기 때문에 닉네임 반환.
	public String getNickname(String id) {
		String nickname = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
//		2) 디비 연결
			Connection con = DriverManager.getConnection(url, user, password);

//			3)sql문 결정

			String sql = "select nickname from member where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);

//			4)sql문 전송
			ResultSet rs = ps.executeQuery();
			if (rs.next()) { // 검색결과가 있는지 보여주는 클래스
				nickname = rs.getString(1);

			}

		} catch (Exception e) {
		}
		return nickname;
	}

}
