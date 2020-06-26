package ui;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import DB.MemberDAO;
import DB.MemberDTO;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePassword {
	private JPasswordField now;
	private JPasswordField newpass;
	private JPasswordField newpassRE;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void Changepassword() {

		JFrame f = new JFrame();
		f.getContentPane().setBackground(Color.GREEN);
		f.setSize(500, 500);
		f.getContentPane().setLayout(null);

		now = new JPasswordField();
		newpass = new JPasswordField();
		newpassRE = new JPasswordField();

		JLabel lblNewLabel = new JLabel("비밀번호 변경");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(111, 33, 276, 58);
		f.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() { // 확인 버튼을 눌렀을 때 실행
			public void actionPerformed(ActionEvent e) {
				// 스트링 타입 변수선언. 추후 스트링으로 변환된 비밀번호를 받아올 변수.
				// now라는 패스워드 필드에 있는 값들을 char 배열로 불러옴.
				// char배열을 문자열로 변환하여 만들어준 스트링 타입 변수에 저장.
				String pw = "";
				char[] secret_pw = now.getPassword();
				pw = String.valueOf(secret_pw);
				String pw1 = "";
				char[] secret_pw1 = newpass.getPassword();
				pw1 = String.valueOf(secret_pw1);
				String pw2 = "";
				char[] secret_pw2 = newpassRE.getPassword();
				pw2 = String.valueOf(secret_pw2);

				MemberDAO dao = new MemberDAO(); // dao 객체 생성

				MemberDTO dto = new MemberDTO(); // dto 객체생성

				if (pw.equals(dao.pwcheck(Main.getLogId()))) { // 현재 비밀번호 란에 들어간 pw 가 사용자의 비밀번호와 일치한다면.
					if (pw1.equals("")) { // 새 비밀번호란이 공백이라면
						JOptionPane.showMessageDialog(null, "새 비밀번호를 입력해주세요.");
					} else if (pw2.equals("")) { // 새 비밀번호 확인 란이 공백이라면
						JOptionPane.showMessageDialog(null, "새 비밀번호 확인을 입력해주세요.");
					} else { // 새 비밀번호란이 공백이 아니고 새 비밀번호 확인란이 공백이 아니면
						if (pw1.equals(pw2)) { // 새비밀번호란과 새 비밀번호 확인란이 같다면
							dto.setId(Main.getLogId()); // id를 DTO에 넣어줌
							dto.setPw(pw1); // 새로운 비밀번호 DTO에 넣어줌.
							dao.pwupdate(dto); // 비밀번호 업데이트 하는 DAO 실행
							JOptionPane.showMessageDialog(null, "비밀번호가 변경되었습니다.");
							f.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "새 비밀번호가 일치하지 않습니다.");
						}

					}
				} else { // 현재 비밀번호 란에 들어간 pw 가 사용자의 비밀번호와 일치하지 않는다면.
					JOptionPane.showMessageDialog(null, "현재 비밀번호가 일치하지 않습니다.");

				}
			}
		});
		btnNewButton.setBounds(47, 356, 140, 50);
		f.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 취소를 클릭하면 화면 꺼짐.
				f.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(295, 356, 140, 50);
		f.getContentPane().add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("현재비밀번호");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(27, 147, 88, 32);
		f.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("새 비밀번호");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(27, 229, 88, 32);
		f.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("새비밀번호확인");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(27, 279, 88, 32);
		f.getContentPane().add(lblNewLabel_1_2);

		now.setEchoChar('*');
		now.setBounds(165, 147, 235, 32);
		f.getContentPane().add(now);

		now.setEchoChar('*');
		newpass.setBounds(165, 229, 235, 32);
		f.getContentPane().add(newpass);

		now.setEchoChar('*');
		newpassRE.setBounds(165, 279, 235, 32);
		f.getContentPane().add(newpassRE);

		f.setVisible(true);
	}
}
