package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import DB.MySong_DTO;
import DB.ProjectDAO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mypage {

	
		public Mypage() {
		JFrame f = new JFrame();
		f.getContentPane().setBackground(Color.GREEN);
		f.setSize(1000,800);
		f.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("내가 구입한 노래");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 32));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(34, 0, 915, 80);
		f.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,5));
		JScrollPane scroll = new JScrollPane(panel);
		scroll.setBounds(34, 88, 915, 600);
		f.getContentPane().add(scroll);
		
		ProjectDAO dao = new ProjectDAO();
		ArrayList<MySong_DTO> list4 = dao.my_song();
		
		for (int i = 0; i < list4.size(); i++) {
	         MySong_DTO dto = new MySong_DTO();
	         dto = list4.get(i);

	         JLabel img = new JLabel("");
	         String albumCover = dto.getAlbum_cover();

	         String imgPath = "albumcover\\" + albumCover;
	         ImageIcon originIcon = new ImageIcon(imgPath);
	         Image originImg = originIcon.getImage();
	         Image changedImg = originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // ****************
	         ImageIcon icon = new ImageIcon(changedImg);
	         img.setIcon(icon);
	         panel.add(img);

	         JLabel l1 = new JLabel("타이틀");
	         l1.setText(dto.getSong_title());
	         panel.add(l1);

	         JLabel l2 = new JLabel("아티스트");
	         l2.setText(dto.getArtist());
	         panel.add(l2);
	         
	         JLabel l3 = new JLabel("장르");
	         l3.setText(dto.getGenre());
	         panel.add(l3);
	         
	         JLabel l4 = new JLabel("앨범 타이틀");
	         l4.setText(dto.getAlbum_title());
	         panel.add(l4);
	         
	      }
		
		JLabel l2 = new JLabel("");
		l2.setBounds(639, 154, 263, 56);
		f.getContentPane().add(l2);
		
		JLabel l3 = new JLabel("");
		l3.setBounds(639, 220, 263, 56);
		f.getContentPane().add(l3);
		
		JLabel l4 = new JLabel("");
		l4.setBounds(639, 286, 263, 56);
		f.getContentPane().add(l4);
		
		JLabel l5 = new JLabel("");
		l5.setBounds(639, 352, 263, 56);
		f.getContentPane().add(l5);
		
		JLabel l6 = new JLabel("");
		l6.setBounds(639, 418, 263, 56);
		f.getContentPane().add(l6);
		
		JButton btnNewButton = new JButton("뒤로가기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				new Main();
			}
		});
		btnNewButton.setBounds(743, 698, 206, 42);
		f.getContentPane().add(btnNewButton);
		
		
		
		
		f.setVisible(true);
		
	}
}
