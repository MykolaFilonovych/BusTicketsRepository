package main.serviceParts;

import main.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@Service
public class TicketsManipulationSystem
{
    @Autowired
    TripsRepository tripsRepository;
    @Autowired
    TicketsRepository ticketsRepository;
    @Autowired
    PaymentsRepository paymentsRepository;

    private List<Trip> tripsList;
    @Autowired
    private PaymentSystem paymentSystem;

    @RequestMapping("/getTripsList")
    public List<Trip> getTripsList()
    {
        tripsList = tripsRepository.findAll();
        tripsList.sort(Comparator.comparingInt(Trip::getId));

        return tripsList;
    }

    @RequestMapping("/buyTicket/{FIO}/{tripId}")
    public Integer buyTicket(@PathVariable("FIO") String FIO, @PathVariable("tripId") int tripId)
    {
        if(tripId <= tripsList.size())
        {
            Trip selectedTrip = tripsRepository.getById(tripId);
            if(selectedTrip.getAvailableTicketsQuantity()>0)
            {
                selectedTrip.subtractTicket();
            }
            else return null;
            tripsRepository.save(selectedTrip);
            Ticket ticket = new Ticket();
            ticket.setFIO(FIO);
            ticket.setTripId(tripId);

            if(this.paymentSystem==null)
            {
                paymentSystem = new PaymentSystem();
            }

            int paymentId = paymentSystem.createPayment(FIO,selectedTrip.getPrice());
            ticket.setPaymentId(paymentId);
            ticketsRepository.save(ticket);
        }

        List<Ticket> ticketList = ticketsRepository.findAll();
        Ticket createdTicket = ticketList.get(ticketList.size()-1);

        return createdTicket.getTicketId();
    }

    @RequestMapping("/scheduleProcessor/{ticketId}")
    public String getTicketStatus(@PathVariable int ticketId)
    {
        Ticket selectedTicket = ticketsRepository.getById(ticketId);
        int paymentId = selectedTicket.getPaymentId();
        int tripId = selectedTicket.getTripId();
        int availablePlaces = tripsRepository.getById(tripId).getAvailableTicketsQuantity();
        Payment payment = paymentsRepository.getById(paymentId);
        String paymentStatus = payment.getStatus();
        String paymentStatus0 = paymentStatus.toLowerCase().trim();
        if(!paymentStatus0.equals("new")&&!paymentStatus0.equals("done"))
        {
            paymentStatus = "FAILED";
        }
        if(paymentStatus.equals("FAILED"))
        {
            paymentStatus = "?????????????????? ?????????????? ?????????? ???? ???????? ?? ?????????????????????????????? " + tripId + " = " + availablePlaces+".";
        }

        return paymentStatus;
    }

    @RequestMapping("/ticketInformation/{ticketId}")
    public String getTicketInformation(@PathVariable int ticketId)
    {
        String information = "???????????? ?? ???????????????? ?????????????????????????????? ?????????? ?? ????????.";
        Ticket selectedTicket = ticketsRepository.getById(ticketId);
        if(selectedTicket != null)
        {
            int tripId = selectedTicket.getTripId();
            int paymentId = selectedTicket.getPaymentId();

            Trip selectedTrip = tripsRepository.getById(tripId);

            String tripInformation = "?? ?????????? ?? ?????????????????????????????? " + selectedTrip.getId()
                    + " ?????????? ???????????????????????? ?? ???????????????????? ???????????? " + selectedTrip.getFromWhere()
                    + " ???? ???????????????????? ???????????? " + selectedTrip.getToWhere()
                    + " ?????? ???????????????????????? - " + selectedTrip.getDepartureTime()
                    + ", ???????? - " + selectedTrip.getPrice() + " ??????????????, ?????????????????? ?????????????????? ?????????? - "
                    + selectedTrip.getAvailableTicketsQuantity() + ", ???????????? ?????????????? - ";

            information = tripInformation + paymentsRepository.getById(paymentId).getStatus() + ".";
            return information;
        }
        else return information;
    }
}
