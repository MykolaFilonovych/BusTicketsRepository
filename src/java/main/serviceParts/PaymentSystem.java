package main.serviceParts;

import main.Payment;
import main.PaymentsRepository;
import main.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@Service
public class PaymentSystem
{
    @Autowired
    TicketsRepository ticketsRepository;
    @Autowired
    PaymentsRepository paymentsRepository;

    public PaymentSystem()
    {

    }

    public int createPayment(@PathVariable String FIO, @PathVariable double sum)
    {
        Payment payment = new Payment();
        payment.setFIO(FIO);
        payment.setSum(sum);

        List<Payment> payments = paymentsRepository.findAll();
        payments.sort(Comparator.comparingInt(Payment::getPaymentId));
        int id = payments.get(payments.size()-1).getPaymentId() + 1;
        payment.setStatus(Payment.getRandomStatus());
        paymentsRepository.save(payment);

        return id;
    }

    @RequestMapping("/getPaymentStatus/{paymentId}")
    public String getPaymentStatus(@PathVariable int paymentId)
    {
        Payment payment = paymentsRepository.getById(paymentId);

        String paymentStatus = payment.getStatus();
        String paymentStatus0 = paymentStatus.toLowerCase().trim();
        if(!paymentStatus0.equals("new")&&!paymentStatus0.equals("done"))
        {
            paymentStatus = "FAILED";
        }

        return paymentStatus;
    }
}
