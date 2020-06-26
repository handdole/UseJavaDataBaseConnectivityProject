package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DB.MemberDAO;
import DB.MemberDTO;

public class MyInfoUpdate {
	JFrame f2 = new JFrame();
	private static JTextField tID;
	private static JTextField tNAME;
	private static JTextField tTEL;
	private static JTextField tEMAIL;
	private static JTextField tNICKNAME;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void MyInfoUpdateUI() {
		f2.getContentPane().setBackground(Color.GREEN);
		f2.setTitle("회원가입 화면");
		f2.setSize(1600, 860);
		f2.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("나의 정보");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 50));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(429, 10, 729, 95);
		f2.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(181, 185, 226, 57);
		f2.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_3 = new JLabel("이름");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_1_3.setBounds(181, 290, 226, 57);
		f2.getContentPane().add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("번호");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_1_4.setBounds(181, 398, 226, 57);
		f2.getContentPane().add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("이메일");
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_5.setForeground(Color.WHITE);
		lblNewLabel_1_5.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_1_5.setBounds(181, 506, 226, 57);
		f2.getContentPane().add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_6 = new JLabel("닉네임");
		lblNewLabel_1_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_6.setForeground(Color.WHITE);
		lblNewLabel_1_6.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_1_6.setBounds(181, 613, 226, 57);
		f2.getContentPane().add(lblNewLabel_1_6);

		tID = new JTextField();

		tID.setEditable(false);   //아이디 변경은 불가능 함으로 비활성화시킴.
		tID.setBounds(604, 185, 737, 57);
		f2.getContentPane().add(tID);
		
		tID.setColumns(10);
		tNAME = new JTextField();
		tNAME.setColumns(10);
		tNAME.setBounds(604, 290, 737, 57);
		f2.getContentPane().add(tNAME);
		
		tTEL = new JTextField();
		tTEL.setColumns(10);
		tTEL.setBounds(604, 398, 737, 57);
		f2.getContentPane().add(tTEL);
		
		tEMAIL = new JTextField();
		tEMAIL.setColumns(10);
		tEMAIL.setBounds(604, 506, 737, 57);
		f2.getContentPane().add(tEMAIL);
		
		tNICKNAME = new JTextField();
		tNICKNAME.setColumns(10);
		tNICKNAME.setBounds(604, 613, 737, 57);
		f2.getContentPane().add(tNICKNAME);
		

		MemberDTO dto = new MemberDTO();
		dto.setId(Main.getLogId()); // 로그인때 받아온 아이디 dto 가방에 저장
		
		
		//DB에서 각 값에 알맞는 데이터 불러옴
		MemberDAO dao = new MemberDAO();

		MemberDTO dto2 = dao.myinfo(dto); 
		
		//맞는 데이터 별로 출력
		tID.setText(Main.getLogId());
		tNAME.setText(dto2.getName());
		tTEL.setText(dto2.getTel());
		tEMAIL.setText(dto2.geteMail());
		tNICKNAME.setText(dto2.getNickname());

		
		JButton btnNewButton = new JButton("정보 수정");
		btnNewButton.addActionListener(new ActionListener() { //정보수정 버튼을 눌렀을 떄
			public void actionPerformed(ActionEvent e) {
				//입력란에 들어가있는 값들을 dto에 넣어서 dao로 보내고 update함.
				String id = tID.getText();
				String name = tNAME.getText();
				String nickname = tNICKNAME.getText();
				String tel = tTEL.getText();
				String eMail = tEMAIL.getText();
				MemberDTO dto3 = new MemberDTO();
				dto3.setName(name);
				dto3.setNickname(nickname);
				dto3.setTel(tel);
				dto3.seteMail(eMail);
				dto3.setId(id);
				MemberDAO dao = new MemberDAO();
				dao.myInfoUpdate(dto3);
				JOptionPane.showMessageDialog(null, "회원정보 수정 완료.");

			}
		});
		btnNewButton.setBounds(408, 727, 277, 49);
		f2.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("회원탈퇴");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 회원탈퇴를 눌렀을경우
				//비밀번호 확인 ui로 이동
				Passwordcheck check = new Passwordcheck();
				check.passwordcheck();
				f2.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(761, 727, 277, 49);
		f2.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("비밀번호 수정");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //비밀번호 수정을 늘렀을 경우
				//비밀번호 수정 ui로 인동
				ChangePassword Cp = new ChangePassword();
				Cp.Changepassword();
			}
		});
		btnNewButton_1_1.setBounds(1135, 727, 277, 49);
		f2.getContentPane().add(btnNewButton_1_1);

		JButton btnNewButton_2 = new JButton("취소");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 취소를 눌렀을 경우
				//메인화면으로 이동
				f2.setVisible(false);
				new Main();
			}
		});
		btnNewButton_2.setBounds(68, 727, 277, 49);
		f2.getContentPane().add(btnNewButton_2);

		f2.setVisible(true);

	}
}
