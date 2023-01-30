package main;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name="payments")
public class Payment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="paymentid")
    private int paymentId;
    @Column(name="paymentstatus")
    private String status;
    @Column(name="fio")
    private String FIO;
    @Column(name="sum")
    private double sum;

    private static Random random;

    public Payment()
    {
        random = new Random();
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static String getRandomStatus()
    {
        int occasional = random.nextInt(3);
        String status0;
        switch (occasional)
        {
            case 0:
                status0 = "NEW";
                break;
            case 1:
                status0 = "DONE";
                break;
            default:
                status0 = "FAILED";
                break;
        }
        return status0;
    }
}
