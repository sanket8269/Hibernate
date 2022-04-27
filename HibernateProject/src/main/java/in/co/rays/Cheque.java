package in.co.rays;

public class Cheque extends Payment {
private int cheNumber;
private String bankName;
public int getCheNumber() {
	return cheNumber;
}
public void setCheNumber(int cheNumber) {
	this.cheNumber = cheNumber;
}
public String getBankName() {
	return bankName;
}
public void setBankName(String bankName) {
	this.bankName = bankName;
}

}
