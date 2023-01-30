package main;

import javax.persistence.*;

@Entity
@Table(name="tickets")
public class Ticket
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ticketid")
    private int ticketId;
    @Column(name="tripid")
    private int tripId;
    @Column(name="fio")
    private String FIO;
    @Column(name="paymentid")
    private int paymentId;

    public Ticket()
    {

    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getFIO()
    {
        return this.FIO;
    }

    public void setFIO(String FIO)
    {
        this.FIO = FIO;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
}
