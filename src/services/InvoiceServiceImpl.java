package services;

import Exceptions.InvalidGateException;
import Exceptions.InvalidTicketException;
import factories.CalculatefeesStrategyFactory;
import models.*;
import repositories.InvoiceRepository;
import strategies.pricing_strategy.CalculateFeeStrategy;
import java.util.Arrays;
import java.util.Date;

public class InvoiceServiceImpl implements InvoiceService{
    private TicketService ticketService;
    private GateService gateService;
    private CalculatefeesStrategyFactory factory;
    private InvoiceRepository invoiceRepository;


    public InvoiceServiceImpl(TicketService ticketService, GateService gateService, CalculatefeesStrategyFactory factory, InvoiceRepository invoiceRepository) {
        this.ticketService = ticketService;
        this.gateService = gateService;
        this.factory = factory;
        this.invoiceRepository = invoiceRepository;
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
        Date entryDate= ticket.getEntryTime();
        Date exitDate=new Date();
        CalculateFeeStrategy calculateFeeStrategy = factory.getCalculateFeeStrategy(exitDate);
        double totalAmount = calculateFeeStrategy.calculateFees(entryDate, exitDate, ticket.getVehicle().getVehicleType());

        InvoiceDetail invoiceDetail=new InvoiceDetail();
        invoiceDetail.setName("Parking fees");
        invoiceDetail.setPrice(totalAmount);

        Invoice invoice=new Invoice();
        invoice.setDetails(Arrays.asList(invoiceDetail));
        invoice.setTicket(ticket);
        invoice.setExitTime(exitDate);
        return invoiceRepository.insertInvoice(invoice);
    }
}
