package ui;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import DB.SongDAO;
import DB.SongDTO;

public class Test2 {

	
	
	public static void main(String[] args) {

		String artist = JOptionPane.showInputDialog("가수입력");
		SongDAO dao = new SongDAO();
		ArrayList<SongDTO> list = dao.list(artist);
//		String[][] searchlist = new String[list.size()][3];
//		searchlist[0][1] = "album_cover";
//		searchlist[0][2] = "song_title";
//		searchlist[0][3] = "artist";
//		System.out.println(searchlist[0]);
//		
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			
		}
		
		
		
		

	}

}
