package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import DB.MemberDAO;
import DB.MemberDTO;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Login {
	JFrame f = new JFrame();

	private JTextField tloginID;
	private JPasswordField pw;
	static String pw2;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void loginUI() {
		f.setTitle("로그인 화면");
		f.getContentPane().setBackground(Color.GREEN);
		f.setSize(1600, 860);
		f.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("MELON");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 50));
		lblNewLabel.setBounds(546, 123, 491, 68);
		f.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(273, 291, 267, 73);
		f.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("비밀번호");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 35));
		lblNewLabel_1_1.setBounds(273, 477, 267, 73);
		f.getContentPane().add(lblNewLabel_1_1);

		tloginID = new JTextField();
		tloginID.setBounds(629, 291, 627, 79);
		f.getContentPane().add(tloginID);
		tloginID.setColumns(10);
		
		pw = new JPasswordField();

		JButton btnNewButton = new JButton("로그인");
		btnNewButton.addActionListener(new ActionListener() {  //로그인버튼을 눌렀을 경우
			public void actionPerformed(ActionEvent e) {
				//입력한 아이디 비빌번호가 데이터에 있는지 확인.
				String ID = tloginID.getText();
				String PW2 = "";
				char[] x = pw.getPassword();
				PW2 = String.valueOf(x);    // 비밀번호 pw2에 저장.
				//dao로 아이디 , 비밀번호 불러오고 아이디랑 비밀번호 비교 pw2는 입력한 값
				MemberDTO dto = new MemberDTO();  //dto 객체 생성
				//dto에 아이디 비밀번호 담기
				dto.setId(ID);	 
				dto.setPw(PW2);
				MemberDAO dao = new MemberDAO(); // dao 객체 실행
				MemberDTO dto2 = dao.logincheck(dto);  //아이디와 비밀번호를 확인하는 dao 메소드 실행 
				if (dto2 != null) {  // 불러온 id pw 를 담은 dto2 값이 null이 아닌 경우.
					JOptionPane.showMessageDialog(null, ID+"님" +"로그인이 완료되었습니다.");
					
					Main.setLogId(ID);
					pw2 = PW2;
					f.setVisible(false);
					 // 메인으로 넘어가게 하고 
					new Main();
					
					// 메인에서 회원정보 수정할 때 다시 마이인포로 가게해야됨.
					
				} else {  // null인 경우.
					JOptionPane.showMessageDialog(null, "아이디 비밀번호를 확인해주세요.");
				}
				
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 35));
		btnNewButton.setBounds(273, 692, 307, 68);
		f.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("회원가입");
		btnNewButton_1.addActionListener(new ActionListener() {   //회원가입을 눌렀을 때 
			public void actionPerformed(ActionEvent e) {
				JoinMembership join = new JoinMembership();
				join.joinmembershipUI();  // 회원가입 화면으로 넘어감.
				f.setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 35));
		btnNewButton_1.setBounds(676, 692, 307, 68);
		f.getContentPane().add(btnNewButton_1);

		f.setVisible(true);
		
		JRootPane  rootPane  =  f.getRootPane();
        rootPane.setDefaultButton(btnNewButton);  
        
        pw.setBounds(629, 477, 627, 73);
        f.getContentPane().add(pw);
        
        JButton btnNewButton_2 = new JButton("뒤로가기");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		f.setVisible(false);
        		new Main();
        	}
        });
        btnNewButton_2.setFont(new Font("굴림", Font.PLAIN, 35));
        btnNewButton_2.setBounds(1028, 692, 307, 68);
        f.getContentPane().add(btnNewButton_2);
		
       
	}
}
