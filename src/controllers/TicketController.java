package controllers;

import Exceptions.InvaliderequestException;
import dtos.GenerateTicketRequestDto;
import dtos.GenerateTicketResponseDto;
import dtos.Response;
import dtos.ResponseStatus;
import models.Ticket;
import services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto requestDto){
        GenerateTicketResponseDto responseDto=new GenerateTicketResponseDto();
        try {
            if (requestDto.getGateId() < 0) {
                throw new InvaliderequestException("Invalide Gate id");
            }

            if (requestDto.getVehicleType() == null || requestDto.getVehicleType().equals("")) {
                throw new InvaliderequestException("Vehicle type required");
            }
        }catch (InvaliderequestException e){
            Response response=new Response();
            response.setStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
            responseDto.setResponse(response);
            return responseDto;
        }

        Response response=new Response();
        try{
            Ticket ticket = ticketService.generateTicket(requestDto.getGateId(), requestDto.getVehicleNumber(), requestDto.getVehicleType());
            responseDto.setTicket(ticket);
            response.setStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            response.setStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
        }
        responseDto.setResponse(response);
        return responseDto;
    }
}
