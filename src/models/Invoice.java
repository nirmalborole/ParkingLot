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

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public double getToalAmount() {
        return toalAmount;
    }

    private void setToalAmount(double toalAmount) {
        this.toalAmount = toalAmount;
    }

    public List<InvoiceDetail> getDetails() {
        return details;
    }

    public void setDetails(List<InvoiceDetail> details) {
        this.details = details;
        double totalAmount=0;
        for(InvoiceDetail invoiceDetail:details){
            totalAmount+=invoiceDetail.getPrice();
        }
        setToalAmount(totalAmount);
    }
}
