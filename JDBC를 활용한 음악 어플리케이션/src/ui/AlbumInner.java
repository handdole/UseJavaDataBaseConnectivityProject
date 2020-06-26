package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import DB.MyAlbumBbsDAO;
import DB.MyAlbumBbsDTO;
import DB.SongDAO;
import DB.SongDTO;

public class AlbumInner {
	private JLabel lbltag;
	private JLabel lbldate;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void AlbumInner() {
		JFrame f = new JFrame();
		f.getContentPane().setBackground(Color.GREEN);
		f.setSize(1600, 860);
		f.getContentPane().setLayout(null);

		// 해당 UI의 title
		JLabel lblNewLabel = new JLabel("나만의 앨범 정보 ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 50));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(52, 40, 409, 61);
		f.getContentPane().add(lblNewLabel);

		// panel_1 은 앨범정보가 들어 있는 panel
		// 게시물 번호 , 제목 , 닉네임 , 태그 , 수록곡수 , 좋아요수 ,앨범커버이미지가 들어간다.
		Panel panel_1 = new Panel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(146, 220, 356, 531);
		f.getContentPane().add(panel_1);

		JLabel lblBbsid = new JLabel();
		lblBbsid.setBounds(89, 10, 57, 30);
		panel_1.add(lblBbsid);

		JLabel lbltitle = new JLabel("TITLE");
		lbltitle.setBounds(27, 368, 295, 23);
		panel_1.add(lbltitle);

		JLabel lblnickname = new JLabel();
		lblnickname.setBounds(27, 401, 295, 23);
		panel_1.add(lblnickname);

		lbltag = new JLabel();
		lbltag.setBounds(27, 449, 245, 23);
		panel_1.add(lbltag);

		JLabel lblsongcount = new JLabel();
		lblsongcount.setBounds(27, 487, 119, 23);
		panel_1.add(lblsongcount);

		JLabel lbllike = new JLabel();
		lbllike.setBounds(275, 491, 69, 23);
		panel_1.add(lbllike);

		JLabel lblcover = new JLabel();
		lblcover.setHorizontalAlignment(SwingConstants.CENTER);
		lblcover.setBackground(Color.WHITE);
		lblcover.setBounds(27, 50, 295, 295);
		panel_1.add(lblcover);

		MyAlbumBbsDAO dao = new MyAlbumBbsDAO(); // UI 왼쪽편에 넣을 데이터 불러오기 위한 DAO 객체 생성
		// dao 입력값에 myalbumlist에서 누른 제목 버튼의 bbsid를 전역변수(STbbsid)로 선언해주었고
		// STbbsid를 innerinfo 메소드의 입력값으로 넣고 호출.
		MyAlbumBbsDTO dto = dao.innerinfo(MyAlbumList.STbbsid);

		JLabel lblNewLabel_1 = new JLabel("게시물번호 : "); // 게시물 번호 및 좋아요는 int값이고 like 처리를 할때 int+string인 구성이라
		lblNewLabel_1.setBounds(12, 10, 77, 30); // 처리하기 어려움 따라서 따로 라벨을 하나 더 추가하여 ui 구현
		panel_1.add(lblNewLabel_1);
		JLabel lblNewLabel_2 = new JLabel("좋아요 :");
		lblNewLabel_2.setBounds(220, 491, 57, 23);
		panel_1.add(lblNewLabel_2);

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = transFormat.format(dto.getTime());

		lbllike.setText(dto.getLike() + ""); // 각라벨에 해당하는 dto값을 넣어줌.
		lblnickname.setText("닉네임 : " + dto.getNickname());
		lblsongcount.setText("수록곡 수 : " + dto.getSongcount() + "");
		lbltag.setText("장르 : " + dto.getTag());
		lbltitle.setText("나만의 앨범 제목 : " + dto.getTitle());
		lbldate = new JLabel("게시일 : " + date1);
		lbldate.setHorizontalAlignment(SwingConstants.CENTER);
		lbldate.setBounds(125, 10, 230, 30);
		panel_1.add(lbldate);
		lblBbsid.setText(dto.getBbsid() + ""); // 게시물 번호 라벨에 게시물 번호 기입
		String imgPath = "albumcover\\" + dto.getAlbumcover(); // project 파일 안에 albumcover폴더에 접근 파일명을 imgpath변수에 넣음.
		// 일관성있게 수정 필요.
		ImageIcon originIcon = new ImageIcon(imgPath);
		// ImageIcon에서 Image를 추출
		Image originImg = originIcon.getImage();
		// 추출된 Image의 크기를 조절하여 새로운 Image객체 생성
		Image changedImg = originImg.getScaledInstance(295, 295, Image.SCALE_SMOOTH);
		// 새로운 Image로 ImageIcon객체를 생성
		ImageIcon icon = new ImageIcon(changedImg); // 새로 만든 changedimg를 lblcover에 넣기위해 icon으로 객체생성하여 변환
		lblcover.setIcon(icon); // 변환한 icon을 lblcover에 넣음

		JButton btnNewButton = new JButton("이전");
		btnNewButton.addActionListener(new ActionListener() { // 제목 버튼을 눌렀을 경우 게시물의 내부로 들어감.
			public void actionPerformed(ActionEvent e) {

				f.setVisible(false);
				new MyAlbumList();
			}
		});
		btnNewButton.setBounds(1180, 768, 263, 43);
		f.getContentPane().add(btnNewButton);

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new GridLayout(0, 4));
		listPanel.setBackground(Color.WHITE);

		JScrollPane js = new JScrollPane(listPanel);
		js.setBounds(593, 220, 850, 531);
		f.getContentPane().add(js);

		JButton likeBtn = new JButton("좋아요");
		likeBtn.addActionListener(new ActionListener() {// 좋아요 버튼을 눌렀을경우
			public void actionPerformed(ActionEvent e) {
				int albumlike = Integer.parseInt(lbllike.getText()) + 1; // 원래 있던 좋아요에 +1을 함
				int albumid = dto.getBbsid(); // 검색을 위해 bbsid를 받아옴.
				MyAlbumBbsDAO dao = new MyAlbumBbsDAO(); // dao사용을위해 객체생성
				dao.like(albumlike, albumid); // bbsid와 일치하는 게시물에 좋아요를 +1한 값으로 업데이트함.
				lbllike.setText(albumlike + ""); // 업데이트한 좋아요한 수 표시.

			}
		});
		likeBtn.setBounds(885, 768, 263, 43);
		f.getContentPane().add(likeBtn);

		String songid = dto.getContent(); // dto.content를 songid에 저장
		// songid 구조상 노래1,노래2,노래3... 이런식이기 때문에 스플릿메소드를 사용하여
		String[] songlist = songid.split(","); // , 를 지워주고 songlist라는 스트링 배열을 만듬.
		for (int i = 0; i < songlist.length; i++) { // 받아온 songid 개수 만큼 for문을 돌림.

			SongDAO dao1 = new SongDAO(); // dao사용을 위한 객체 생성
			SongDTO dto2 = new SongDTO(); // dao를 받아오기위해 dto 객체 생성
			dto2 = dao1.innersonglist(Integer.parseInt(songlist[i])); // innersonglist메소드에 입력값으로 songid한개를 넣어줌.

			JLabel lblsongnum = new JLabel(i + 1 + ""); // 게시물 번호
			lblsongnum.setHorizontalAlignment(SwingConstants.CENTER);
			lblsongnum.setPreferredSize(new Dimension(50, 100));
			listPanel.add(lblsongnum);

			JLabel lblalbumcover = new JLabel();
			String imgPath1 = "albumcover\\" + dto2.getAlbumcover(); // 불러온 albumcover를 불러옴.
			// 일관성있게 수정 필요.
			ImageIcon originIcon1 = new ImageIcon(imgPath1);
			// ImageIcon에서 Image를 추출
			Image originImg1 = originIcon1.getImage();
			// 추출된 Image의 크기를 조절하여 새로운 Image객체 생성
			Image changedImg1 = originImg1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			// 새로운 Image로 ImageIcon객체를 생성
			ImageIcon icon1 = new ImageIcon(changedImg1);
			lblalbumcover.setPreferredSize(new Dimension(100, 100));
			lblalbumcover.setIcon(icon1);
			listPanel.add(lblalbumcover);

			JLabel lblsongtitle = new JLabel();
			lblsongtitle.setText(dto2.getSongtitle()); // 불러온 노래 제목을 제목라벨에 넣음.
			lblsongtitle.setPreferredSize(new Dimension(200, 100));
			listPanel.add(lblsongtitle);

			JLabel lblartist = new JLabel();
			lblartist.setText(dto2.getArtist()); // 불러온 가수를 가수라벨에 넣음.
			lblartist.setPreferredSize(new Dimension(100, 100));
			listPanel.add(lblartist);

		}
		f.setVisible(true);

		f.setVisible(true);

	}
}
