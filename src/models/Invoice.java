package models;

import repositories.TicketRepository;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class Invoice extends BaseModel{
    private Ticket ticket;
    private Date exitTime;
    private double toalAmount;
    private List<InvoiceDetail> details;
}
