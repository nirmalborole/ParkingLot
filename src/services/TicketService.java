package services;

import models.Ticket;

public interface TicketService {
    public Ticket generateTicket(int gated, String vehicleNumber, String vehicleType) throws Exception;
    public Ticket getTicketById(int ticketId);
}
