package controllers;

import Exceptions.InvaliderequestException;
import dtos.GenerateInvoiceRequestDto;
import dtos.GenerateInvoiceResponseDto;
import dtos.Response;
import dtos.ResponseStatus;
import models.Invoice;
import services.InvoiceService;

public class InvoiceController {
    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public GenerateInvoiceResponseDto generateInvoice(GenerateInvoiceRequestDto requestDto){
        GenerateInvoiceResponseDto responseDto=new GenerateInvoiceResponseDto();
        try{
            if(requestDto.getTicketId()<0){
                throw new InvaliderequestException("Ticket id conot be negative");
            }
            if(requestDto.getGateId()<0){
                throw new InvaliderequestException("gate id canot be negative");
            }
        }catch (Exception e){
            Response response=new Response();
            response.setStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
            responseDto.setResponse(response);
            return responseDto;
        }

        try{
            Invoice invoice = invoiceService.generateInvoice(requestDto.getTicketId(), requestDto.getGateId());
            Response response=new Response();
            response.setStatus(ResponseStatus.SUCCESS);
            responseDto.setInvoice(invoice);
            responseDto.setResponse(response);
            return responseDto;
        }catch (Exception e){
            Response response=new Response();
            response.setStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
            responseDto.setResponse(response);
            return responseDto;
        }

    }
}
