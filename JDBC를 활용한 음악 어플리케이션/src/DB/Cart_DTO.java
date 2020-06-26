package DB;

public class Cart_DTO {
	int song_id;
	String song_title;
	String artist;
	String album_cover;
	
	
	public int getSong_id() {
		return song_id;
	}
	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}
	public String getSong_title() {
		return song_title;
	}
	public void setSong_title(String song_title) {
		this.song_title = song_title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum_cover() {
		return album_cover;
	}
	public void setAlbum_cover(String album_cover) {
		this.album_cover = album_cover;
	}
	
	
	
	
}
