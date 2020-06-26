package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import DB.MemberDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Passwordcheck {
	private JPasswordField pw;
	/**
	 * @wbp.parser.entryPoint
	 */
	public void passwordcheck() {
		JFrame f = new JFrame();
		f.getContentPane().setBackground(Color.GREEN);
		f.setSize(300, 300);
		f.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("비밀번호를 입력해주세요.");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(22, 34, 250, 46);
		f.getContentPane().add(lblNewLabel);
		
		pw = new JPasswordField();
		pw.setBounds(22, 102, 250, 46);
		f.getContentPane().add(pw);
		
		JButton btnNewButton = new JButton("탈퇴하겠습니다.");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//비밀번호 변환처리
				String pw2 = "";
				char[] secret_pw = pw.getPassword();
				pw2 = String.valueOf(secret_pw);
				MemberDAO dao = new MemberDAO();
				
				if (pw2.equals(dao.pwcheck(Main.getLogId()))) { // 입력한 비밀번호와 DB에서 불러온 비밀번호 가 같은 경우
					MemberDAO dao2 = new MemberDAO();
					dao2.delete(Main.getLogId());
					JOptionPane.showMessageDialog(null, "회원탈퇴가 되었습니다.");
					f.setVisible(false);
					Main.setLogId(null);
					new Main();
					
				} else { // 입력한 비밀번호와 DB에서 불러온 비밀번호 가 다른경우
					JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
				}
				
			}
		});
		btnNewButton.setBounds(22, 176, 119, 38);
		f.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("뒤로가기");
		btnNewButton_1.addActionListener(new ActionListener() {  // 회원정보 수정 ui로 돌아감
			public void actionPerformed(ActionEvent e) {
				MyInfoUpdate info = new MyInfoUpdate();
				info.MyInfoUpdateUI();
				f.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(153, 176, 119, 38);
		f.getContentPane().add(btnNewButton_1);
		
		
		f.setVisible(true);
	}
}
