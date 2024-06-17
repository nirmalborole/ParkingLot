import controllers.TicketController;
import dtos.GenerateTicketRequestDto;
import dtos.GenerateTicketResponseDto;
import models.*;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;
import repositories.VehicleRepository;
import services.GateService;
import services.TicketService;
import services.TicketServiceImpl;
import strategies.spot_assignment.AssignSpotStrategy;
import strategies.spot_assignment.NerestFirstSpotAssignmentStrategy;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Gate gate1=new Gate();
        gate1.setGateType(GateType.ENTRY);
        gate1.setName("1A");
        gate1.setOperator(new Operator());
        gate1.setId(1);

        Gate gate2=new Gate();
        gate1.setGateType(GateType.EXIT);
        gate1.setName("4Z");
        gate1.setOperator(new Operator());
        gate1.setId(2);


        Map<Integer,Gate> gateMap=new HashMap<Integer,Gate>(){{
            put(1,gate1);
            put(2,gate2);
        }};


        List<Spot>spots= Arrays.asList(new Spot("1A",SpotStatus.UNOCCUPIED,VehicleType.CAR),new Spot("2A",SpotStatus.UNOCCUPIED,VehicleType.CAR));
        List<Section>sections=new ArrayList<>();
        Section section=new Section();
        section.setName("AA");
        section.setId(1);
        section.setSpots(spots);
        sections.add(section);

        List<Floor>floors=new ArrayList<>();
        Floor floor=new Floor();
        floor.setFloorNum(1);
        floor.setFloorStatus(FloorStatus.OPERATIONAL);
        floor.setSections(sections);
        floor.setId(1);
        floors.add(floor);

        List<Gate>gates=Arrays.asList(gate1,gate2);

        ParkingLot parkingLot=new ParkingLot();
        parkingLot.setFloors(floors);
        parkingLot.setGates(gates);
        parkingLot.setId(1);

        Map<Integer,ParkingLot>parkingLotMap=new HashMap<Integer,ParkingLot>(){{
            put(1,parkingLot);
        }};


        GateRepository gateRepository=new GateRepository(gateMap);
        ParkingLotRepository parkingLotRepository=new ParkingLotRepository(parkingLotMap);
        TicketRepository ticketRepository=new TicketRepository();
        VehicleRepository vehicleRepository=new VehicleRepository();

        GateService gateService=new GateService(gateRepository);
        AssignSpotStrategy assignSpotStrategy=new NerestFirstSpotAssignmentStrategy();
        TicketService ticketService=new TicketServiceImpl(gateService,vehicleRepository,assignSpotStrategy,parkingLotRepository,ticketRepository);

        TicketController ticketController=new TicketController(ticketService);

        GenerateTicketRequestDto requestDto=new GenerateTicketRequestDto();
        requestDto.setGateId(1);
        requestDto.setVehicleType(VehicleType.CAR.toString());
        requestDto.setVehicleNumber("MH 23 1234");
        GenerateTicketResponseDto responseDto = ticketController.generateTicket(requestDto);
        System.out.println(responseDto);

        requestDto.setVehicleNumber("MH1 3454");
        responseDto=ticketController.generateTicket(requestDto);
        System.out.println(responseDto);

    }
}