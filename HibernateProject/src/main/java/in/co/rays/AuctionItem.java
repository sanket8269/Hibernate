package in.co.rays;

import java.util.Set;
public class AuctionItem {
	private int id;
	private String Descreptions;
	private Set<Bid> Bids;
	

	public Set<Bid> getBids() {
		return Bids;
	}

	public void setBids(Set<Bid> bids) {
		Bids = bids;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescreptions() {
		return Descreptions;
	}

	public void setDescreptions(String descreptions) {
		Descreptions = descreptions;
	}

	
	
}
