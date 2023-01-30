package main;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name="trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;
    @Column(name = "fromwhere")
    private String fromWhere;
    @Column(name = "towhere")
    private String toWhere;
    @Column(name = "departuretime")
    private Time departureTime;
    @Column(name = "price")
    private double price;
    @Column(name = "availableticketsquantity")
    private int availableTicketsQuantity;

    public Trip()
    {

    }

    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }

    public String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    public String getToWhere() {
        return toWhere;
    }

    public void setToWhere(String toWhere) {
        this.toWhere = toWhere;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableTicketsQuantity() {
        return availableTicketsQuantity;
    }

    public void setAvailableTicketsQuantity(int availableTicketsQuantity) {
        this.availableTicketsQuantity = availableTicketsQuantity;
    }

    public void subtractTicket()
    {
        if (this.availableTicketsQuantity > 0)
        {
            this.availableTicketsQuantity--;
        }
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + Id +
                ", fromWhere='" + fromWhere + '\'' +
                ", toWhere='" + toWhere + '\'' +
                ", departureTime=" + departureTime +
                ", price=" + price +
                ", availableTicketsQuantity=" + availableTicketsQuantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Id == trip.Id && Double.compare(trip.price, price) == 0 && availableTicketsQuantity == trip.availableTicketsQuantity && fromWhere.equals(trip.fromWhere) && toWhere.equals(trip.toWhere) && departureTime.equals(trip.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, fromWhere, toWhere, departureTime, price, availableTicketsQuantity);
    }
}
