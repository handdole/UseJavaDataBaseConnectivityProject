package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import DB.MyAlbumBbsDAO;
import DB.MyAlbumBbsDTO;

public class MyAlbumList extends JFrame {

	GridBagLayout gbl;
	GridBagConstraints gbc;
	static int STbbsid;
	public MyAlbumList() {
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		setLayout(gbl); // 레이아웃을 그리드백 레이아웃으로 설정
		gbc.fill = GridBagConstraints.BOTH; // 빈 공간 없이 채워줌

		MyAlbumBbsDAO dao = new MyAlbumBbsDAO(); // DAO 객체생성
		ArrayList<MyAlbumBbsDTO> list = dao.infomyalbumlist(); // 게시판 판넬에 들아갈 list객체생성

		JPanel listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(1500, list.size() * 110));
		// 전체 게시판 목록의 크기 만큼만 패널의 크기 설정

		JPanel infopanel = new JPanel();
		infopanel.setPreferredSize(new Dimension(1500,50));
		
		JLabel mainlblbbsid = new JLabel("게시판 번호");
		mainlblbbsid.setHorizontalAlignment(SwingConstants.CENTER);
		mainlblbbsid.setPreferredSize(new Dimension(150, 40));
		infopanel.add(mainlblbbsid);
		JLabel maincoverlbl = new JLabel("앨범커버");
		maincoverlbl.setHorizontalAlignment(SwingConstants.CENTER);
		maincoverlbl.setPreferredSize(new Dimension(100, 40));
		infopanel.add(maincoverlbl);
		JLabel mainlbltitle = new JLabel("제목");
		mainlbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		mainlbltitle.setPreferredSize(new Dimension(900, 40));
		infopanel.add(mainlbltitle);
		JLabel mainnicknamelbl = new JLabel("닉네임");
		mainnicknamelbl.setHorizontalAlignment(SwingConstants.CENTER);
		mainnicknamelbl.setPreferredSize(new Dimension(155, 40));
		infopanel.add(mainnicknamelbl);
		JLabel mainsongcountlbl = new JLabel("수록곡 수");
		mainsongcountlbl.setHorizontalAlignment(SwingConstants.CENTER);
		mainsongcountlbl.setPreferredSize(new Dimension(115, 40));
		infopanel.add(mainsongcountlbl);
		JLabel mainlikelbl = new JLabel("좋아요");
		mainlikelbl.setHorizontalAlignment(SwingConstants.CENTER);
		mainlikelbl.setPreferredSize(new Dimension(115, 40));
		infopanel.add(mainlikelbl);

		
		
		for (int i = 0; i < list.size(); i++) {
			JLabel lblbbsid = new JLabel(list.get(i).getBbsid() + "");
			lblbbsid.setHorizontalAlignment(SwingConstants.CENTER);
			lblbbsid.setPreferredSize(new Dimension(150, 50));
			listPanel.add(lblbbsid);
			JLabel myalbumcoverlbl = new JLabel();
			String imgPath = "albumcover\\" + list.get(i).getAlbumcover();
			// 일관성있게 수정 필요.
			ImageIcon originIcon = new ImageIcon(imgPath);
			// ImageIcon에서 Image를 추출
			Image originImg = originIcon.getImage();
			// 추출된 Image의 크기를 조절하여 새로운 Image객체 생성
			Image changedImg = originImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			// 새로운 Image로 ImageIcon객체를 생성
			ImageIcon icon = new ImageIcon(changedImg);
			myalbumcoverlbl.setHorizontalAlignment(SwingConstants.CENTER);
			myalbumcoverlbl.setPreferredSize(new Dimension(100, 100));
			myalbumcoverlbl.setIcon(icon);
			listPanel.add(myalbumcoverlbl);
			JButton titleBtn = new JButton(list.get(i).getTitle());
			titleBtn.addActionListener(new ActionListener() { // 제목 버튼을 눌렀을 경우 게시물의 내부로 들어감.
				public void actionPerformed(ActionEvent e) {
					STbbsid = Integer.parseInt(lblbbsid.getText());
					AlbumInner inner = new AlbumInner();
					inner.AlbumInner();
					setVisible(false);
				}
			});
			titleBtn.setPreferredSize(new Dimension(900, 50));
			listPanel.add(titleBtn);
			JLabel lblnickname = new JLabel(list.get(i).getNickname());
			lblnickname.setHorizontalAlignment(SwingConstants.CENTER);
			lblnickname.setPreferredSize(new Dimension(155, 50));
			listPanel.add(lblnickname);
			JLabel lblsongcount = new JLabel(list.get(i).getSongcount() + "");
			lblsongcount.setHorizontalAlignment(SwingConstants.CENTER);
			lblsongcount.setPreferredSize(new Dimension(115, 50));
			listPanel.add(lblsongcount);
			JLabel lbllike = new JLabel(list.get(i).getLike() + "");
			lbllike.setHorizontalAlignment(SwingConstants.CENTER);
			lbllike.setPreferredSize(new Dimension(115, 50));
			listPanel.add(lbllike);

		}
		listPanel.setBackground(Color.GREEN);

		JScrollPane js = new JScrollPane(listPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		gbc.weightx = 0.1;
		gbc.weighty = 0.8;

		JLabel maintitlelbl = new JLabel("나만의 앨범 게시판");
		maintitlelbl.setHorizontalAlignment(SwingConstants.CENTER);
		maintitlelbl.setFont(new Font("굴림", Font.PLAIN, 40));
		gbAdd(maintitlelbl, 0, 0, 12, 1);
		
		gbc.weighty = 0.8;
		gbAdd(infopanel, 0, 1, 12, 1);
		
		gbc.weighty = 15;
		js.setPreferredSize(new Dimension(1500, list.size() * 110));
		gbAdd(js, 0, 2, 12, 1);

		gbc.weighty = 0.8;
		JButton backBtn = new JButton("뒤로가기");
		gbAdd(backBtn, 0, 3, 3, 1);
		backBtn.addActionListener(new ActionListener() { // 뒤로가기 클릭시
			public void actionPerformed(ActionEvent e) {
				new Main();
				setVisible(false);
			}
		});
		JButton writeBtn = new JButton("나만의 앨범 만들기");
		writeBtn.addActionListener(new ActionListener() { // 뒤로가기 클릭시
			public void actionPerformed(ActionEvent e) {

				MyAlubmWrite wirte = new MyAlubmWrite();
				wirte.MyAlbumWrite();
				setVisible(false);

			}
		});
		gbAdd(writeBtn, 9, 3, 3, 1);

		setSize(1600, 860);
		setVisible(true);

	}

	private void gbAdd(Component c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbl.setConstraints(c, gbc);
		add(c);
	}

	public static void main(String[] args) {
		new MyAlbumList();
	}

}
