package com.model;

public class Payment {
	private int pId;
    private String amount;
    private String paymentType;
    private String remainingAmount;
    private int bId;
    
	public Payment(int pId, String amount, String paymentType, String remainingAmount, int bId) {
		super();
		this.pId = pId;
		this.amount = amount;
		this.paymentType = paymentType;
		this.remainingAmount = remainingAmount;
		this.bId = bId;
	}

	public int getpId() {
		return pId;
	}

	public String getAmount() {
		return amount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public String getRemainingAmount() {
		return remainingAmount;
	}

	public int getbId() {
		return bId;
	}
	
	public String toString() {
        return "Payment [pId=" + pId + ", amount=" + amount + ", paymentType=" + paymentType + ", remainingAmount=" + remainingAmount + ", bId=" + bId + "]";
    }
    
    
}
