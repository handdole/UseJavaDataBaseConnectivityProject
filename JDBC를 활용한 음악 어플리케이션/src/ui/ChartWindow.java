package ui;

import java.awt.Color;
import java.awt.Dimension;
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

import DB.DBconnector;
import DB.SongDTO;

public class ChartWindow {

   // 원하는 차트를 띄워주는 메소드
   public void myChart(SongDTO song) {
      JFrame f = new JFrame();
      f.setSize(1000, 800);
      // 매개변수로 받아온 DTO 체크
      if (song.getArtist() != null) { // 가수이름만 들어있는 DTO일 경우
         f.setTitle(song.getArtist()); // 차트의 제목을 해당 가수로 설정
      } else if (song.getGenre() != null) { // 장르만 들어있는 DTO일 경우
         f.setTitle(song.getGenre()); // 차트의 제목을 해당 장르로 설정
      }
      DBconnector db = new DBconnector(); // DAO객체 생성
      ArrayList<SongDTO> list = db.getSongList(song); // 매개변수로 받아온 DTO를 DAO로 다시 넘겨줌

      JPanel panel = new JPanel(); // 차트를 올릴 패널
      panel.setBackground(new Color(50, 205, 50));
      panel.setPreferredSize(new Dimension(900, list.size() * 197)); // 패널의 크기 설정
      // 패널의 크기는 차트의 길이에 따라 달라진다

      for (int i = 0; i < list.size(); i++) {
         JLabel rankLbl1 = new JLabel((i + 1) + "위: "); // 순위표시 라벨
         panel.add(rankLbl1);
         int songId = list.get(i).getSongid();
         JLabel albumCoverLbl = new JLabel("앨범 이미지"); // 앨범커버를 넣을 라벨
         String imgPath = "albumcover\\" + list.get(i).getAlbumcover();
         // ImageIcon객체를 생성
         ImageIcon originIcon = new ImageIcon(imgPath);
         // ImageIcon에서 Image를 추출
         Image originImg = originIcon.getImage();
         // 추출된 Image의 크기를 조절하여 새로운 Image객체 생성
         Image changedImg = originImg.getScaledInstance(192, 192, Image.SCALE_SMOOTH);
         // 새로운 Image로 ImageIcon객체를 생성
         ImageIcon icon = new ImageIcon(changedImg);
         albumCoverLbl.setPreferredSize(new Dimension(192, 192));
         albumCoverLbl.setIcon(icon);
         panel.add(albumCoverLbl);
         JButton songTitleBtn = new JButton(list.get(i).getSongtitle()); // 곡 제목 버튼
         songTitleBtn.addActionListener(new ActionListener() { // 곡 제목 클릭시
            public void actionPerformed(ActionEvent e) {
               // 곡 id만 들어있는 DTO생성
               SongDTO song = new SongDTO();
               song.setSongid(songId);
               // 곡 정보창 객체 생성
               SongInfo si = new SongInfo();
               si.showSongInfo(song); // 곡 정보창 띄워주는 메소드 호출
               // 곡 id만 들어있는 DTO를 매개변수로 넘겨준다
               f.setVisible(false); // 현재 보고 있는 창은 닫아준다
            }
         });
         songTitleBtn.setPreferredSize(new Dimension(250, 50));
         panel.add(songTitleBtn);
         JButton artistBtn = new JButton(list.get(i).getArtist()); // 가수이름 넣을 버튼
         artistBtn.addActionListener(new ActionListener() { // 가수 이름 클릭시
            public void actionPerformed(ActionEvent e) {
               String artist = artistBtn.getText();
               SongDTO song = new SongDTO();
               song.setArtist(artist);
               ChartWindow ch = new ChartWindow(); // 차트 객체 생성
               ch.myChart(song); // 해당 가수가 부른 곡을 인기순위로 띄워줌
               f.setVisible(false);
            }
         });
         artistBtn.setPreferredSize(new Dimension(100, 50));
         panel.add(artistBtn);
         JButton albumTitleBtn = new JButton(list.get(i).getAlbumtitle()); // 앨범 제목 버튼
         albumTitleBtn.addActionListener(new ActionListener() { // 앨범 제목 클릭시
            public void actionPerformed(ActionEvent e) {
               String albumTitle = albumTitleBtn.getText();
               // 앨범 정보창 객체 생성
               AlbumInfo ai = new AlbumInfo();
               ai.showAlbumInfo(albumTitle); // 앨범 정보창 띄워주는 메소드 호출
               // 앨범제목을 매개변수로 넘겨준다.
               f.setVisible(false); // 현재 보고 있는 창은 닫아준다
            }
         });
         albumTitleBtn.setPreferredSize(new Dimension(250, 50));
         panel.add(albumTitleBtn);
         JLabel songLikeLbl = new JLabel(list.get(i).getSonglike() + ""); // 해당 곡의 좋아요수 라벨
         songLikeLbl.setHorizontalAlignment(SwingConstants.CENTER);
         songLikeLbl.setPreferredSize(new Dimension(100, 50));
         panel.add(songLikeLbl);
      }

      JScrollPane js = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      // 패널에 붙여줄 스크롤. 세로스크롤은 필요할때만 표시하고, 가로스크롤은 항상 표시하지 않는다.
      js.setPreferredSize(new Dimension(900, list.size() * 197)); // 스크롤의 크기 설정. 차트의 크기만큼 설정한다
      f.add(js);

      f.setVisible(true);
   }
}