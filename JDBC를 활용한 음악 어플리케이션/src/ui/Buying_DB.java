package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DB.Cart_DTO;
import DB.Pay_DTO;
import DB.ProjectDAO;

public class Buying_DB {
	private static JPasswordField pw3;
	private static JTextField t0;
	private static JTextField t1;
	private static JTextField t2;
	private static JTextField t3;
	private static JPasswordField pw0;
	private static JPasswordField pw1;
	private static JPasswordField pw2;
	private static JComboBox bank_choice;
	private static JTextArea ta1;
	private static JTextField input_name;
	private static JComboBox card_choice;
	private static JComboBox installment_choice;
	
	
	private static int account_create_flag = 0;
	private static int payment_attempt_flag = 0;

	public Buying_DB() {
		JFrame f = new JFrame();
		f.getContentPane().setBackground(Color.GREEN);
		f.setSize(1000,800);
		f.getContentPane().setLayout(null);
		
		JLabel l0 = new JLabel("구매 곡 정보");
		l0.setHorizontalAlignment(SwingConstants.CENTER);
		l0.setBounds(34, 32, 387, 57);
		f.getContentPane().add(l0);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,3));
		JScrollPane scroll = new JScrollPane(panel);
		scroll.setBounds(34, 88, 387, 555);
		f.getContentPane().add(scroll);
		
		ProjectDAO dao = new ProjectDAO();
		ArrayList<Cart_DTO> list = dao.all();
		
		
		for (int i = 0; i < list.size(); i++) {
			Cart_DTO dto = new Cart_DTO();
			dto = list.get(i);
			
			JLabel img = new JLabel("");
			
			String albumCover = dto.getAlbum_cover(); // 앨범커버의 파일명을 임시로 저장해둠
//			JLabel albumCoverLbl = new JLabel("앨범 이미지"); // 앨범커버를 넣을 라벨
			String imgPath = "albumcover\\" + albumCover;
			// 앨범커버 이미지를 원하는 크기로 불러옴
			// ImageIcon객체를 생성
			ImageIcon originIcon = new ImageIcon(imgPath);
			// ImageIcon에서 Image를 추출
			Image originImg = originIcon.getImage();
			// 추출된 Image의 크기를 조절하여 새로운 Image객체 생성
			Image changedImg = originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			// 새로운 Image로 ImageIcon객체를 생성
			ImageIcon icon = new ImageIcon(changedImg);
//			albumCoverLbl.setPreferredSize(new Dimension(50, 50));
//			albumCoverLbl.setIcon(icon);
			img.setIcon(icon);
			panel.add(img);
			
			JLabel l2 = new JLabel("타이틀"); 
			l2.setText(dto.getSong_title());
			panel.add(l2);
			
			
			JLabel l_2_1 = new JLabel("아티스트");
			l_2_1.setText(dto.getArtist());
			panel.add(l_2_1);
			
		}

		
		DecimalFormat formatter = new DecimalFormat("###,###");
		JLabel l3 = new JLabel("총 " + list.size() + "곡 X 800원 = 합계 " + 
					formatter.format(list.size()*800) + "원");
		l3.setOpaque(true); //Opaque값을 true로 미리 설정해 주어야 배경색이 적용된다.
		l3.setBackground(Color.WHITE);
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		l3.setBounds(34, 668, 384, 57);
		f.getContentPane().add(l3);
		
		
		JLabel l1 = new JLabel("결제방식");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(465, 32, 142, 86);
		f.getContentPane().add(l1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(478, 159, 462, 379);
		panel_1.setLayout(null);

		f.getContentPane().add(panel_1);
		
		JButton card_btn = new JButton("카드");
		card_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.removeAll();
				
				JLabel lblNewLabel_7 = new JLabel("카드사 선택");
				lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_7.setBounds(12, 10, 102, 29);
				panel_1.add(lblNewLabel_7);
				
				JLabel lblNewLabel_7_1 = new JLabel("카드번호 입력");
				lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_7_1.setBounds(12, 60, 102, 23);
				panel_1.add(lblNewLabel_7_1);
				
				JLabel lblNewLabel_7_2 = new JLabel("유효기간");
				lblNewLabel_7_2.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_7_2.setBounds(12, 99, 102, 29);
				panel_1.add(lblNewLabel_7_2);
				
				JLabel lblNewLabel_7_3 = new JLabel("CVC");
				lblNewLabel_7_3.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_7_3.setBounds(271, 99, 102, 29);
				panel_1.add(lblNewLabel_7_3);
				
				JLabel lblNewLabel_7_4 = new JLabel("비밀번호");
				lblNewLabel_7_4.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_7_4.setBounds(12, 141, 102, 29);
				panel_1.add(lblNewLabel_7_4);
				
				t0 = new JTextField();
				t0.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if (t0.getText().length() == 5) {
							t0.setEditable(false);
							JOptionPane.showMessageDialog(null, "5자리 이상 입력할 수 없습니다.");
							t0.setText(null);
							t0.setEditable(true);
						}
					}
				});
				t0.setBounds(137, 60, 65, 23);
				panel_1.add(t0);
				t0.setColumns(10);
				
				t1 = new JTextField();
				t1.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if (t1.getText().length() == 5) {
							t1.setEditable(false);
							JOptionPane.showMessageDialog(null, "5자리 이상 입력할 수 없습니다.");
							t1.setText(null);
							t1.setEditable(true);
						}
					}
				});
				t1.setColumns(10);
				t1.setBounds(368, 60, 65, 23);
				panel_1.add(t1);
				
				t2 = new JTextField();
				t2.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if (t2.getText().length() == 3) {
							t2.setEditable(false);
							JOptionPane.showMessageDialog(null, "3자리 이상 입력할 수 없습니다.");
							t2.setText(null);
							t2.setEditable(true);
						}
					}
				});
				t2.setColumns(10);
				t2.setBounds(137, 103, 27, 21);
				panel_1.add(t2);
				
				String card[] = {"카드선택", "현대카드", "신한카드", "BC카드", "KB국민카드", "삼성카드", "우리카드", "카카오뱅크", "NH채움카드", "롯데카드", "하나카드"};
				card_choice = new JComboBox(card);
				card_choice.setBounds(137, 14, 142, 23);
				panel_1.add(card_choice);
				
				JLabel lblNewLabel_8 = new JLabel("**");
				lblNewLabel_8.setBounds(175, 145, 27, 25);
				panel_1.add(lblNewLabel_8);
				
				pw3 = new JPasswordField(); //jtextfield 정규식 검색
				pw3.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if (pw3.getText().length() == 3) { /////////////////////////
							pw3.setEditable(false);
							JOptionPane.showMessageDialog(null, "3자리 이상 입력할 수 없습니다.");
							pw3.setText(null);
							pw3.setEditable(true);
						}
					}
				});
				pw3.setBounds(137, 145, 36, 21);
				panel_1.add(pw3);
				
				JLabel lblNewLabel_7_4_1 = new JLabel("할부 ");
				lblNewLabel_7_4_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_7_4_1.setBounds(12, 180, 102, 29);
				panel_1.add(lblNewLabel_7_4_1);
				
				String installment[] = {"할부선택", "일시불", "2개월", "3개월", "4개월", "5개월", "6개월", "9개월", "12개월"};
				installment_choice = new JComboBox(installment);
				installment_choice.setBounds(137, 184, 102, 23);
				panel_1.add(installment_choice);
				
				t3 = new JTextField();
				t3.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if (t3.getText().length() == 3) {
							t3.setEditable(false);
							JOptionPane.showMessageDialog(null, "3자리 이상 입력할 수 없습니다.");
							t3.setText(null);
							t3.setEditable(true);
						}
					}
				});
				t3.setColumns(10);
				t3.setBounds(176, 103, 27, 21);
				panel_1.add(t3);
				
				JLabel lblNewLabel_8_1 = new JLabel("/");
				lblNewLabel_8_1.setFont(new Font("굴림", Font.PLAIN, 14));
				lblNewLabel_8_1.setBounds(166, 102, 20, 23);
				panel_1.add(lblNewLabel_8_1);
				
				pw0 = new JPasswordField();
				pw0.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if (pw0.getText().length() == 5) { 
							pw0.setEditable(false);
							JOptionPane.showMessageDialog(null, "5자리 이상 입력할 수 없습니다.");
							pw0.setText(null);
							pw0.setEditable(true);
					}
					}
				});
				pw0.setBounds(214, 60, 65, 23);
				panel_1.add(pw0);
				
				pw1 = new JPasswordField();
				pw1.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if (pw1.getText().length() == 5) { 
							pw1.setEditable(false);
							JOptionPane.showMessageDialog(null, "5자리 이상 입력할 수 없습니다.");
							pw1.setText(null);
							pw1.setEditable(true);
					}
					}
				});
				pw1.setBounds(291, 61, 65, 23);
				panel_1.add(pw1);
				
				pw2 = new JPasswordField();
				pw2.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if (pw2.getText().length() == 4) { 
							pw2.setEditable(false);
							JOptionPane.showMessageDialog(null, "4자리 이상 입력할 수 없습니다.");
							pw2.setText(null);
							pw2.setEditable(true);
					}
					}
				});
				pw2.setBounds(368, 102, 65, 23);
				panel_1.add(pw2);
				
				payment_attempt_flag = 3;
			
				panel_1.revalidate(); //다시 일깨우는, 불러일으키는 역할
				panel_1.repaint(); //위와 같음.. 
			}
		});
		card_btn.setBounds(630, 32, 142, 86);
		f.getContentPane().add(card_btn);
		
		
		
		
		
		
		JButton account_btn = new JButton("무통장 입금");
		account_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_1.removeAll(); 
				
				JLabel lblNewLabel_7 = new JLabel("은행 선택");
				lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_7.setBounds(12, 10, 102, 29);
				panel_1.add(lblNewLabel_7);
				
				JLabel lblNewLabel_7_2 = new JLabel("입금자 성명");
				lblNewLabel_7_2.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_7_2.setBounds(12, 49, 102, 29);
				panel_1.add(lblNewLabel_7_2);
				
				
				input_name = new JTextField();
				input_name.setColumns(10);
				input_name.setBounds(137, 53, 65, 21);
				panel_1.add(input_name);
				
				
				String bank[] = {"은행선택", "기업은행", "외환은행", "국민은행", "농협중앙회", "우리은행", "SC제일은행", "씨티은행", "대구은행", "우체국", "KEB하나은행"};
				bank_choice = new JComboBox(bank);
				bank_choice.setBounds(137, 14, 142, 23);
				panel_1.add(bank_choice);
				
				
				JButton account_create = new JButton("개인용 가상계좌 생성");
				account_create.setBounds(12, 281, 438, 88);
				panel_1.add(account_create);
				
				ta1 = new JTextArea();
				ta1.setForeground(Color.BLACK);
				ta1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				ta1.setBounds(39, 123, 394, 105);
				panel_1.add(ta1);
				
				payment_attempt_flag = 1;
				
				account_create.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) { 
						if ((bank_choice.getSelectedItem().equals("은행선택")) || (input_name.getText().equals(""))) {
						//비교연산자는 기본형에 대해서만 쓸 수 있음. 주소값은 .equals 사용해야.
							JOptionPane.showMessageDialog(null, "결제 정보를 입력해주세요");
						} else {
							Random r = new Random();
							int r1 = r.nextInt(1000000);
							int r2 = r.nextInt(1000);
							int r3 = r.nextInt(100000);
							
							ta1.setText(
									"성명  :  " + input_name.getText() + "\n" +		
											"은행  :  " + bank_choice.getSelectedItem() + "\n" +
											"계좌번호 : " + r1 + " - " + r2 + " - " + r3 + "\n" +
											"금액  :  " + list.size()*800 + "원" + "\n" + "\n" +
											"입금자 성명과 금액을 확인해주세요." // Jtextarea로 바꿔야함. Panel은 1줄만 가능
									);	
							account_create_flag++;
							payment_attempt_flag = 2;
							}
					}
				});
				panel_1.revalidate();
				panel_1.repaint();
				
				
			}
		});
		account_btn.setBounds(798, 32, 142, 86);
		f.getContentPane().add(account_btn);
		
		
		JLabel payment_result = new JLabel("결제 결과");
		payment_result.setOpaque(true);
		payment_result.setBackground(Color.WHITE);
		payment_result.setHorizontalAlignment(SwingConstants.CENTER);
		payment_result.setBounds(478, 668, 462, 57);
		f.getContentPane().add(payment_result);
		
		
		
		//결제시도 카드 or 통장에 따라서 다른 if문으로 들어가게 설정
		
		JButton payment_attempt = new JButton("결제 시도");
		payment_attempt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (payment_attempt_flag == 2) {
				if (account_create_flag == 0) {
					JOptionPane.showMessageDialog(null, "개인 가상계좌를 먼저 생성해주세요.");
				} 
				else {
					//1. 결제정보 DB에 저장하기
	               Pay_DTO dto = new Pay_DTO(); //가방을 만들고 
	               dto.setId(Main.getLogId()); //가방에 넣음 //******************static id 입력받기 나중에  
	               dto.setPay_id(0); //auto increment는, db에서 primary key지정하고 변수값 0 넣어두면 알아서 적용됨
	               SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	               Date today = new Date();
	               String date = s.format(today);
	               dto.setDate(String.valueOf(date));
	               dto.setTrack_count(list.size());
	               dto.setTotal_price(list.size()*800); 
	               dto.setPay_way("무통장입금");
	               dto.setPay_company(String.valueOf(bank_choice.getSelectedItem()));
	               ProjectDAO dao = new ProjectDAO();
	               dao.insert_pay(dto);
					
					//2. 구매한 곡들 my_song으로 옮기기
					//dao로 cart DB를 ArrayList로 담아온다.
					ArrayList<Cart_DTO> list2 = dao.all();
					dao.insert_my_song(list2);
					
					//3. cart를 비운다.
					dao.delete(list2);
					
					payment_result.setText("결제가 완료되었습니다.");
					ta1.setText("");
					input_name.setText("");
					bank_choice.setSelectedItem("은행선택");
					panel.removeAll();
					panel.revalidate();
					panel.repaint(); 
					l3.setText("");
					JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.");
					f.setVisible(false);
					new Main();
					}
				
			} else if (payment_attempt_flag == 1) {
				JOptionPane.showMessageDialog(null, "개인 가상계좌를 먼저 생성해주세요.");
			}
			else if (payment_attempt_flag == 0) {
				JOptionPane.showMessageDialog(null, "결제 방식을 선택해주세요.");
			}
			else if (payment_attempt_flag == 3) {
				
				if (	
						(card_choice.getSelectedItem().equals("카드선택")) ||
						(t0.getText().equals("")) || (pw0.getText().equals("")) || (pw1.getText().equals("")) ||
						(t1.getText().equals("")) || (t2.getText().equals("")) || (t3.getText().equals("")) ||
						(pw2.getText().equals("")) || 
						(pw3.getText().equals("")) || 
						(installment_choice.getSelectedItem().equals("할부선택"))
						)  
				{ 
					JOptionPane.showMessageDialog(null, "결제 정보를 입력해주세요");
				} else { 
						//1. 결제정보 DB에 저장하기
						Pay_DTO dto = new Pay_DTO(); //가방을 만들고 
						dto.setId(Main.getLogId()); //***********static id 입력받기?  //가방에 넣음
						dto.setPay_id(0); //**********영수증 번호 생성?
						SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date today = new Date();
						String date = s.format(today);
						dto.setDate(String.valueOf(date));
						dto.setTrack_count(list.size());
						dto.setTotal_price(list.size()*800); 
	                  	dto.setPay_way("카드");
	                  	dto.setPay_company(String.valueOf(card_choice.getSelectedItem()));
	                  	ProjectDAO dao = new ProjectDAO();
	                  	dao.insert_pay(dto);
					
						//2. 구매한 곡들 my_song으로 옮기기
						//dao로 cart DB를 ArrayList로 담아온다.
						ArrayList<Cart_DTO> list2 = dao.all();
						dao.insert_my_song(list2);
						
						//3. cart를 비운다.
						dao.delete(list2);
						
						payment_result.setText("결제가 완료되었습니다.");
						card_choice.setSelectedItem("카드선택"); t0.setText(""); pw0.setText("");
						pw1.setText(""); t1.setText(""); t2.setText(""); t3.setText(""); pw2.setText("");
						pw3.setText(""); installment_choice.setSelectedItem("할부선택");
						panel.removeAll();
						panel.revalidate();
						panel.repaint(); 
						l3.setText("");
						JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.");
						f.setVisible(false);
						new Main();
				}

			}	
				
			
				
				}
		});
		payment_attempt.setBounds(478, 575, 462, 68);
		f.getContentPane().add(payment_attempt);
		
		
		f.setVisible(true);
		
	}
}
