package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DB.MyAlbumBbsDAO;
import DB.MyAlbumBbsDTO;
import DB.MemberDAO;
import DB.SongDAO;
import DB.SongDTO;
import DB.file;

public class MyAlubmWrite {

	JFrame f3 = new JFrame();
	private static JTextField ttitle;
	private static JTextField TSearch;

	int songcount;
	ArrayList<String> songid = new ArrayList<String>();

	// arraylist<songdto> list 를 배열로 다시 만들어서 사용해야될것같음.

	/**
	 * @wbp.parser.entryPoint
	 */
	public void MyAlbumWrite() {

		f3.getContentPane().setBackground(Color.GREEN);
		f3.setBackground(Color.GREEN);
		f3.setSize(1600, 860);
		f3.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("나만의 앨범작성");
		lblNewLabel.setBounds(44, 10, 239, 48);
		f3.getContentPane().add(lblNewLabel);

		Panel searchpanel = new Panel();
		searchpanel.setBackground(Color.PINK);
		searchpanel.setPreferredSize(new Dimension(650, 600));

		Panel panel_2 = new Panel();
		panel_2.setBackground(Color.ORANGE);
		panel_2.setBounds(44, 101, 1460, 170);
		f3.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("앨범 제목");
		lblNewLabel_2.setBounds(193, 10, 101, 36);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("앨범 장르");
		lblNewLabel_2_1.setBounds(193, 67, 101, 36);
		panel_2.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("앨범 이미지선택");
		lblNewLabel_2_2.setBounds(193, 124, 101, 36);
		panel_2.add(lblNewLabel_2_2);

		JLabel showimg = new JLabel("");

		JButton btnNewButton = new JButton("이미지 불러오기");
		btnNewButton.addActionListener(new ActionListener() { // 이미지 불러오기 버튼을 눌렀을 때
			public void actionPerformed(ActionEvent e) {
				file call = new file(); // file 클레스를 사용하기위한 객체 생성
				String filename1 = call.jFileChooserUtil(); // 선택한 파일의 위치값을 스트링으로 저장.
				String filename = filename1.substring(filename1.lastIndexOf("\\") + 1);
				// path를 의미하는 \는 특수기호이기 때문에 스트링으로 사용하고자할땐
				// \\ 이런식으로 사용해야한다. 위에 lastindexof(\\) 은 마지막 path 이후 문자열을 뽑아낸 것.
				ImageIcon icon = new ImageIcon("albumcover\\" + filename); // albumcover 폴더에 있는 해당파일을 icon으로 설정
				Image img = icon.getImage(); // ImageIcon을 Image로 변환.
				Image img2 = img.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
				ImageIcon icon2 = new ImageIcon(img2); // Image로 ImageIcon 생성
				showimg.setIcon(icon2); // showing라벨에 변환한 icon2를 삽입
				showimg.setText(filename);

			}
		});
		btnNewButton.setBounds(327, 124, 204, 36);
		panel_2.add(btnNewButton);

		ttitle = new JTextField();
		ttitle.setBounds(327, 10, 406, 36);
		panel_2.add(ttitle);
		ttitle.setColumns(10);

		showimg.setHorizontalAlignment(SwingConstants.CENTER);
		showimg.setBounds(12, 10, 150, 150);
		panel_2.add(showimg);

		JCheckBox cb1 = new JCheckBox("힙합");

		cb1.setBounds(327, 67, 77, 36);
		panel_2.add(cb1);

		JCheckBox cb2 = new JCheckBox("발라드");
		cb2.setBounds(408, 67, 77, 36);
		panel_2.add(cb2);

		JCheckBox cb3 = new JCheckBox("pop");
		cb3.setBounds(489, 67, 77, 36);
		panel_2.add(cb3);

		JCheckBox cb4 = new JCheckBox("OST");
		cb4.setBounds(570, 67, 77, 36);
		panel_2.add(cb4);

		JCheckBox cb5 = new JCheckBox("댄스");
		cb5.setBounds(651, 67, 77, 36);
		panel_2.add(cb5);

		JCheckBox cb6 = new JCheckBox("락");
		cb6.setBounds(732, 67, 77, 36);
		panel_2.add(cb6);

		JLabel lblNewLabel_1 = new JLabel("나만의 앨범 정보입력");
		lblNewLabel_1.setBounds(44, 68, 125, 29);
		f3.getContentPane().add(lblNewLabel_1);

		JButton btnNewButton_2_1 = new JButton("취소");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MyAlbumList();
				f3.setVisible(false);
			}
		});
		btnNewButton_2_1.setBounds(872, 763, 219, 48);
		f3.getContentPane().add(btnNewButton_2_1);

		JLabel Searchlbl = new JLabel("검색");
		Searchlbl.setBounds(44, 322, 69, 31);
		f3.getContentPane().add(Searchlbl);
		Searchlbl.setHorizontalAlignment(SwingConstants.CENTER);

		TSearch = new JTextField();
		TSearch.setBounds(137, 322, 405, 31);
		f3.getContentPane().add(TSearch);
		TSearch.setColumns(10);

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(0, 4));
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(0, 3));

		JScrollPane js = new JScrollPane(p1);
		JScrollPane js2 = new JScrollPane(p2);
		js.setBounds(54, 425, 640, 303);
		js2.setBounds(872, 425, 650, 303);
		f3.getContentPane().add(js);
		f3.getContentPane().add(js2);

		JButton searchButton = new JButton("검색");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1.removeAll();
				String x = TSearch.getText(); // 검색어를 x에 넣음
				SongDAO dao = new SongDAO();
				ArrayList<SongDTO> list = dao.list(x); // 검색어를 넣었을 때 곡에 관련된 모든 정보를 songDTO롤 불러옴
				if (list == null) { // 검색결과가 없을 경우
					JOptionPane.showMessageDialog(null, "검색결과가 없습니다.");
				} else {
					// 검색결과가 있을 경우 내 수록곡에 넣기 위한 버튼과 각 노래에대한 정보를 판넬에 표시
					for (int i = 0; i < list.size(); i++) {
						JLabel albumcover = new JLabel();
						String imgPath = "albumcover\\" + list.get(i).getAlbumcover();
						// 앨범커버 이미지를 원하는 크기로 불러옴
						// ImageIcon객체를 생성
						ImageIcon originIcon = new ImageIcon(imgPath);
						// ImageIcon에서 Image를 추출
						Image originImg = originIcon.getImage();
						// 추출된 Image의 크기를 조절하여 새로운 Image객체 생성
						Image changedImg = originImg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
						// 새로운 Image로 ImageIcon객체를 생성
						ImageIcon icon = new ImageIcon(changedImg);
						albumcover.setIcon(icon);
						p1.add(albumcover);
						JLabel songtitle = new JLabel(list.get(i).getSongtitle());
						p1.add(songtitle);
						JLabel artist = new JLabel(list.get(i).getArtist());
						p1.add(artist); // 불러온 정보들 판넬에 출력

						// songid를 스트링으로 임시저장
						String songidinput = Integer.toString(list.get(i).getSongid());

						// 추가버튼을 눌렀을 때 선곡리스트 판넬로 넘어가게함.
						JButton addbtn = new JButton("추가");
						addbtn.addActionListener(new ActionListener() { // 추가버튼을 눌렀을때
							public void actionPerformed(ActionEvent e) {
								// 추가 버튼에 해당하는 노래 정보들을 수록곡의 정보를 표시하는 판넬에 표시
								JLabel albumcover2 = new JLabel();
								albumcover2.setIcon(albumcover.getIcon());
								p2.add(albumcover2);
								JLabel songtitle2 = new JLabel(songtitle.getText());
								p2.add(songtitle2);
								JLabel artist2 = new JLabel(artist.getText());
								p2.add(artist2);

								// songid라는 배열에 추가 버튼에 해당하는 songid를 추가
								songid.add(songidinput);

								songcount++; // 추가를 누를때마다 수록고 수 증가
								f3.setVisible(true);

							}

						});

						p1.add(addbtn);
					}

				}

				f3.setVisible(true);
			}
		});

		searchButton.setBounds(554, 322, 125, 31);
		f3.getContentPane().add(searchButton);

		JLabel albumcoverlbl = new JLabel("앨범커버");
		albumcoverlbl.setBounds(54, 371, 55, 31);
		f3.getContentPane().add(albumcoverlbl);
		albumcoverlbl.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel Titlelbl = new JLabel("제목");
		Titlelbl.setBounds(147, 371, 318, 31);
		f3.getContentPane().add(Titlelbl);
		Titlelbl.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel Aritstlbl = new JLabel("가수");
		Aritstlbl.setBounds(495, 371, 86, 31);
		f3.getContentPane().add(Aritstlbl);
		Aritstlbl.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel Buttonlbl = new JLabel("추가버튼");
		Buttonlbl.setBounds(593, 371, 86, 31);
		f3.getContentPane().add(Buttonlbl);
		Buttonlbl.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_3 = new JLabel("선곡 리스트");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 58));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(872, 330, 650, 72);
		f3.getContentPane().add(lblNewLabel_3);

		JButton btnNewButton_1 = new JButton("모두 삭제");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p2.removeAll(); // 모두삭제 버튼을 눌렀을 경우 선곡리스트 판넬에 있는 모든 곡들을 지움
				p2.revalidate();
				p2.repaint();
				songcount = 0; // 지움과 동시에 전역변수인 songcount 를 0으로 초기화

				f3.setVisible(true);
				songid.removeAll(songid);

			}
		});
		btnNewButton_1.setBounds(1373, 738, 149, 36);
		f3.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("앨범올리기");
		btnNewButton_2.addActionListener(new ActionListener() { // 앨범올리기 버튼을 눌렀을 때
			public void actionPerformed(ActionEvent e) {
				// 앨범올릴 때 DB에 들어갈 값들을 처리.

				String text = ""; // text라는 스트링 선언.
				// 각 체크박스에 해당하는 스트링값을 갖는 x라는 스트링 배열을 만듬. 또한 값들을 순서에 맞게 넣어줌.
				String[] x = { cb1.getText(), cb2.getText(), cb3.getText(), cb4.getText(), cb5.getText(),
						cb6.getText() };
				// 위에 배열에 맞게 체크의 유무 (체크면 true 아니면 false)를 갖는 boolean 값들을 배열로 만듬.
				boolean[] y = { cb1.isSelected(), cb2.isSelected(), cb3.isSelected(), cb4.isSelected(),
						cb5.isSelected(), cb6.isSelected() };
				// text에 체크된 값들을 넣는 반복문
				for (int i = 0; i < x.length; i++) {
					if (y[i] == true) { // cb(i+1)체크박스가 체크되어있다면
						text = x[i] + " " + text; // cb(i+1) 값을 텍스트에 넣고 누적.
					} else {
						text = text + ""; // 아니라면 공백 넣음.
					}
				}

				String title = ttitle.getText(); // 앨범을 만들때 작성자가 정한 제목을 title에 저장.

				MemberDAO dao = new MemberDAO();
				// log인 한 id를 입력값에 넣는 dao실행 -> 해당하는 nickname을 DB에서 가져옴
				String nickname = dao.getNickname(Main.getLogId());

//				like 는 0으로 초기화 저장
				int albumlike = 0;
				String tag = text;
//				songcount 는 곡 고른수 변수로 지정.
				String albumcover = showimg.getText(); // showing에 들어간 파일 이름을 변수에 넣어줌

				// 수록곡의 songid를 스트링 값으로 모을 변수를만들고
				// songid라는 ararrylist에서 각 인덱스의 값들을
				// content라는 스트링에 합해줌
				String content = "";
				for (int i = 0; i < songid.size(); i++) {
					if (i == songid.size() - 1) {

						content = content + songid.get(i);
					} else {
						content = content + songid.get(i) + ",";

					}
				}

				// UI상에서 입력값을 받아올 때 있을만한 오류를 체크하는 작업.
				if (title.equals("") || text.equals("") || songcount == 0 || albumcover.equals("")) {
					if (title.equals("")) { // 모든 값들중에 빈값이 있을 때
						JOptionPane.showMessageDialog(null, "앨범 제목을 입력해주세요.");
					} else if (text.equals("")) {
						JOptionPane.showMessageDialog(null, "장르를 체크해주세요.");
					} else if (albumcover.equals("")) {
						JOptionPane.showMessageDialog(null, "앨범이미지를 선택해주세요.");
					} else if (songcount == 0) {
						JOptionPane.showMessageDialog(null, "수록곡은 한개 이상 필수입니다.");
					}

				} else { // 모든값들이 빈값이 아닐경우

					MyAlbumBbsDTO MABDTO = new MyAlbumBbsDTO(); // myalbumbbsDTO 객체 생성 후
					MABDTO.setBbsid(0); // 그에 맞는 값들을 넣어줌
					MABDTO.setTitle(title);
					MABDTO.setNickname(nickname);
					MABDTO.setTime(null);
					MABDTO.setLike(albumlike);
					MABDTO.setTag(tag);
					MABDTO.setSongcount(songcount);
					MABDTO.setAlbumcover(albumcover);
					MABDTO.setContent(content);

					MyAlbumBbsDAO MABDAO = new MyAlbumBbsDAO();
					MABDAO.insert(MABDTO); // DB에 DTO에 담은 값들을 넣어줌

					JOptionPane.showMessageDialog(null, "나의 앨범 작성이 완료되었습니다.");
					f3.setVisible(false);
					new MyAlbumList();
				}

			}
		});
		btnNewButton_2.setBounds(475, 763, 219, 48);
		f3.getContentPane().add(btnNewButton_2);

		f3.setVisible(true);

	}
}
