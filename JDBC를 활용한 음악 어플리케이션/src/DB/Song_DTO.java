package DB;

public class Song_DTO {
	int song_id;
	String song_title;
	String artist;
	String genre;
	String album_title;
	int song_like;
	int price;
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
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAlbum_title() {
		return album_title;
	}
	public void setAlbum_title(String album_title) {
		this.album_title = album_title;
	}
	public int getSong_like() {
		return song_like;
	}
	public void setSong_like(int song_like) {
		this.song_like = song_like;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAlbum_cover() {
		return album_cover;
	}
	public void setAlbum_cover(String album_cover) {
		this.album_cover = album_cover;
	}
	
	
	
	
}
