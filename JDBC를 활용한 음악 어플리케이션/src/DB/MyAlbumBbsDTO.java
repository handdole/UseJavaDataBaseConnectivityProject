package DB;

import java.util.Date;

public class MyAlbumBbsDTO {
	@Override
	public String toString() {
		return "MyAlbumBbsDTO [bbsid=" + bbsid + ", title=" + title + ", nickname=" + nickname + ", time=" + time
				+ ", like=" + like + ", tag=" + tag + ", songcount=" + songcount + ", albumcover=" + albumcover + "]";
	}
	
	   // 각 테이블에 들어가는 칼럼들을 지역변수로 지정 
	public int bbsid;
	public String title;
	public String nickname;
	public Date time; 
	public int like;
	public String tag;
	public int songcount;
	public String albumcover;
	public String content;
	
	
	// 각 변수별 getter , setter 설정.
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBbsid() {
		return bbsid;
	}
	public void setBbsid(int bbsid) {
		this.bbsid = bbsid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getSongcount() {
		return songcount;
	}
	public void setSongcount(int songcount) {
		this.songcount = songcount;
	}
	public String getAlbumcover() {
		return albumcover;
	}
	public void setAlbumcover(String albumcover) {
		this.albumcover = albumcover;
	}

	
	
	
	
}
