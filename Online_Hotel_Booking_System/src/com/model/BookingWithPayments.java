package com.model;

import java.util.List;

public class BookingWithPayments {
    private Booking booking;
    private List<Payment> payments;

    public BookingWithPayments(Booking booking, List<Payment> payments) {
        this.booking = booking;
        this.payments = payments;
    }

    public Booking getBooking() {
        return booking;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    @Override
    public String toString() {
        return "BookingWithPayments{" +
                "booking=" + booking +
                ", payments=" + payments +
                '}';
    }
}