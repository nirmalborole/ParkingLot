package services;

import Exceptions.InvalidGateException;
import Exceptions.InvalidTicketException;
import models.Gate;
import models.GateType;
import models.Invoice;
import models.Ticket;

public class InvoiceServiceImpl implements InvoiceService{
    private TicketService ticketService;
    private GateService gateService;


    public InvoiceServiceImpl(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public Invoice generateInvoice(int ticketId, int gateId) throws InvalidTicketException, InvalidGateException {
        Ticket ticket = this.ticketService.getTicketById(ticketId);
        if(ticket == null){
            throw new InvalidTicketException("ticket is not present in DB");
        }

        Gate gate = this.gateService.getGateById(gateId);
        if(gate == null){
            throw new InvalidGateException("gate is not present in DB");
        }
        if(gate.getGateType().equals(GateType.ENTRY)){
            throw new InvalidGateException("Invoice cannot be created at entry gate");
        }




        return null;
    }
}
