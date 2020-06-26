package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DB.MemberDAO;
import DB.MemberDTO;

public class JoinMembership {

	JFrame f1 = new JFrame();
	private static JTextField tID;

	private static JTextField tNAME;
	private static JTextField tTEL;
	private static JTextField tEMAIL;
	private static JTextField tNICKNAME;
	private static JPasswordField pw;
	private static JPasswordField pwre;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void joinmembershipUI() {
		f1.getContentPane().setBackground(Color.GREEN);
		f1.setTitle("회원가입 화면");
		f1.setSize(1600, 860);
		f1.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("회원가입");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 50));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(429, 10, 729, 95);
		f1.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(181, 177, 226, 40);
		f1.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("비밀번호");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_1_1.setBounds(181, 252, 226, 40);
		f1.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("비밀번호확인");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_1_2.setBounds(181, 330, 226, 40);
		f1.getContentPane().add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("이름");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_1_3.setBounds(181, 404, 226, 40);
		f1.getContentPane().add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("번호");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_1_4.setBounds(181, 482, 226, 40);
		f1.getContentPane().add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("이메일");
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_5.setForeground(Color.WHITE);
		lblNewLabel_1_5.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_1_5.setBounds(181, 556, 226, 40);
		f1.getContentPane().add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_6 = new JLabel("닉네임");
		lblNewLabel_1_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_6.setForeground(Color.WHITE);
		lblNewLabel_1_6.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_1_6.setBounds(181, 632, 226, 40);
		f1.getContentPane().add(lblNewLabel_1_6);

		// sign_up 테이블에 들어갈 정보를 받아올 UI (텍스트 필드 )
		tID = new JTextField();
		tID.setBounds(604, 177, 737, 40);
		f1.getContentPane().add(tID);
		tID.setColumns(10);

		tNAME = new JTextField();
		tNAME.setColumns(10);
		tNAME.setBounds(604, 404, 737, 40);
		f1.getContentPane().add(tNAME);

		tTEL = new JTextField();
		tTEL.setColumns(10);
		tTEL.setBounds(604, 482, 737, 40);
		f1.getContentPane().add(tTEL);

		tEMAIL = new JTextField();
		tEMAIL.setColumns(10);
		tEMAIL.setBounds(604, 562, 737, 34);
		f1.getContentPane().add(tEMAIL);

		// 패스워드는 UI상에서 보이지 않게 처리.
		pw = new JPasswordField();
		pwre = new JPasswordField();
		pw.setEchoChar('*');
		pw.setBounds(604, 252, 737, 40);
		f1.getContentPane().add(pw);

		pwre.setEchoChar('*');
		pwre.setBounds(604, 330, 737, 40);
		f1.getContentPane().add(pwre);

		tNICKNAME = new JTextField();
		tNICKNAME.setColumns(10);
		tNICKNAME.setBounds(604, 632, 737, 40);
		f1.getContentPane().add(tNICKNAME);

		JLabel infopwre = new JLabel("");

		JButton btnNewButton = new JButton("가입완료");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//받아온 입력값들을 string 변수를 만들어서 각 변수에 넣어줌.
				String id = tID.getText();
				String name = tNAME.getText();
				String tel = tTEL.getText();
				String email = tEMAIL.getText();
				String nickname = tNICKNAME.getText();

				// 스트링 타입 변수선언. 추후 스트링으로 변환된 비밀번호를 받아올 변수.
				// now라는 패스워드 필드에 있는 값들을 char 배열로 불러옴.
				// char배열을 문자열로 변환하여 만들어준 스트링 타입 변수에 저장.
				String pw2 = "";
				char[] secret_pw = pw.getPassword();
				pw2 = String.valueOf(secret_pw);
				String pwre2 = "";
				char[] secret_pwre = pwre.getPassword();
				pwre2 = String.valueOf(secret_pwre);
				
				MemberDTO dto = new MemberDTO();   //dto 객체 생성
				dto.setId(id);  //받아온 아이디를 DTO에 넣어줌

				MemberDAO dao = new MemberDAO();  //dao 객체 생성
				//dao로 아이디가 중복인지 확인 있으면 idcheck는 값이 있음 없으면 null. 
				String idcheck = dao.idcheck(dto);   

				if (id.equals("") || name.equals("") || tel.equals("") || email.equals("") || nickname.equals("")
						|| pw2.equals("") || pwre2.equals("")) {
					//한개라도 정보 누락시 메세지 출력. ( 자연스럽게 모든 칼럼들이 not null 속성을 갖음.)
					JOptionPane.showMessageDialog(null, "모든 정보를 입력해주시길바랍니다.");  
				} else {// 모든 정보를 입력했을 경우
					if (id.equals(idcheck)) { // 불러온 아이디에 값이 쓰고자하는 아이디와 같을 경우
						JOptionPane.showMessageDialog(null, "이미 사용중인 아이디입니다.");
					} else if (!pw2.equals(pwre2)) {  //비밀번호 입력란과 비밀번호 확인란의 값이 같지 않을 경우
						infopwre.setText("비밀번호가 일치하지 않습니다.");   // 라벨에 정보 표시
					} else {   //중복 아이디가 없고 중복확인의 조건을 만족한 경우
						//정보들을 DTO로 보내고 
						MemberDTO dto2 = new MemberDTO();
						dto2.setId(id);
						dto2.setPw(pw2);
						dto2.setName(name);
						dto2.seteMail(email);
						dto2.setNickname(nickname);
						dto2.setTel(tel);

						MemberDAO dao2 = new MemberDAO();  
						dao2.insert(dto2);  // 정보들을 보낸 DTO를 입력값으로 insert메소드를 갖은 DAO실행
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
						f1.setVisible(false);  // 화면 내리고
						Login log = new Login();  //로그인 창 열기.
						log.loginUI();
					}

				} // end else
			}//


		});
		btnNewButton.setBounds(429, 745, 277, 49);
		f1.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.addActionListener(new ActionListener() {  //취소버튼을 눌렀을경우

			public void actionPerformed(ActionEvent e) {
				Login log = new Login();   //로그인 화면으로 돌아감.
				log.loginUI();
				f1.setVisible(false);
			}
		});

		btnNewButton_1.setBounds(968, 745, 277, 49);
		f1.getContentPane().add(btnNewButton_1);

		infopwre.setForeground(Color.RED);
		infopwre.setBounds(604, 376, 218, 22);
		f1.getContentPane().add(infopwre);

		f1.setVisible(true);
	}
}