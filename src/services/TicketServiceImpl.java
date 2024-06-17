package services;

import models.*;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;
import repositories.VehicleRepository;
import strategies.spot_assignment.AssignSpotStrategy;

import java.util.Date;

public class TicketServiceImpl implements TicketService{
    private GateService gateService;
    private VehicleRepository vehicleRepository;
    private AssignSpotStrategy assignSpotStrategy;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketServiceImpl(GateService gateService, VehicleRepository vehicleRepository, AssignSpotStrategy assignSpotStrategy, ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository) {
        this.gateService = gateService;
        this.vehicleRepository = vehicleRepository;
        this.assignSpotStrategy = assignSpotStrategy;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket generateTicket(int gated, String vehicleNumber, String vehicleType) throws Exception {
        Gate gate = gateService.getGateById(gated);
        VehicleType type = VehicleType.getVehicleType(vehicleType);
        Vehicle vehicle = vehicleRepository.createIfNotexist(vehicleNumber, type);
        ParkingLot parkingLot = parkingLotRepository.getParkingLotByGateId(gated);
        if(parkingLot == null){
            throw new Exception("Invalid gate Id");
        }
        Spot spot = assignSpotStrategy.assignSpot(type, parkingLot);

        Ticket ticket=new Ticket();
        ticket.setAssignedSpot(spot);
        ticket.setGate(gate);
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(new Date());
        return ticketRepository.insertTicket(ticket);
    }

    @Override
    public Ticket getTicketById(int ticketId) {
        return ticketRepository.getTicketById(ticketId);
    }
}
