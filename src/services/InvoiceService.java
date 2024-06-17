package services;

import Exceptions.InvalidGateException;
import Exceptions.InvalidTicketException;
import models.Invoice;

public interface InvoiceService {
    public Invoice generateInvoice(int ticketId, int gateId) throws InvalidTicketException, InvalidGateException;
}
