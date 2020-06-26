package DB;

public class Pay_DTO {
	String id;
	int pay_id; //영수증 번호
	String date;
	int track_count;
	int total_price;
	String pay_way;
	String pay_company;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPay_id() {
		return pay_id;
	}
	public void setPay_id(int pay_id) {
		this.pay_id = pay_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTrack_count() {
		return track_count;
	}
	public void setTrack_count(int track_count) {
		this.track_count = track_count;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public String getPay_way() {
		return pay_way;
	}
	public void setPay_way(String pay_way) {
		this.pay_way = pay_way;
	}
	public String getPay_company() {
		return pay_company;
	}
	public void setPay_company(String pay_company) {
		this.pay_company = pay_company;
	}
	@Override
	public String toString() {
		return "Pay_DTO [id=" + id + ", pay_id=" + pay_id + ", date=" + date + ", track_count=" + track_count
				+ ", total_price=" + total_price + ", pay_way=" + pay_way + ", pay_company=" + pay_company + "]";
	}

	
	
}
