package com.model;

public class Booking {
	private int id;
	private String room_price;
	private String service_price;
	private String total_amount;
	private String checkin;
	private String checkout;
	private String payment_status;
	private int ru_id;
	private int r_id;
	
	
	public Booking() {
		this.id = 0;
		this.room_price = "";
		this.service_price = "";
		this.total_amount = "";
		this.checkin = "";
		this.checkout = "";
		this.payment_status = "";
		this.ru_id = 0;
		this.r_id = 0;
	}

	public Booking(int id, String room_price, String service_price, String total_amount, String checkin,
			String checkout, String payment_status, int ru_id, int r_id) {

		this.id = id;
		this.room_price = room_price;
		this.service_price = service_price;
		this.total_amount = total_amount;
		this.checkin = checkin;
		this.checkout = checkout;
		this.payment_status = payment_status;
		this.ru_id = ru_id;
		this.r_id = r_id;
	}

	public int getId() {
		return id;
	}

	public String getRoom_price() {
		return room_price;
	}

	public String getService_price() {
		return service_price;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public String getCheckin() {
		return checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public int getRu_id() {
		return ru_id;
	}

	public int getR_id() {
		return r_id;
	}

	@Override
    public String toString() {
        return "Booking{" +
                "b_id=" + id +
                ", room_price='" + room_price + '\'' +
                ", service_price='" + service_price + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", check_in='" + checkin + '\'' +
                ", check_out='" + checkout + '\'' +
                ", payment_status='" + payment_status + '\'' +
                ", ru_id=" + ru_id +
                ", r_id=" + r_id +
                '}';
    }
	
}
