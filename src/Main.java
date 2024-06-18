import controllers.InvoiceController;
import controllers.TicketController;
import dtos.GenerateInvoiceRequestDto;
import dtos.GenerateInvoiceResponseDto;
import dtos.GenerateTicketRequestDto;
import dtos.GenerateTicketResponseDto;
import factories.CalculatefeesStrategyFactory;
import models.*;
import repositories.*;
import services.*;
import strategies.pricing_strategy.CalculateFeeStrategy;
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
        gate2.setGateType(GateType.EXIT);
        gate2.setName("4Z");
        gate2.setOperator(new Operator());
        gate2.setId(2);


        Map<Integer,Gate> gateMap=new HashMap<Integer,Gate>(){{
            put(1,gate1);
            put(2,gate2);
        }};

        Slab slab1=new Slab(1,VehicleType.CAR,0,2,10);
        Slab slab2=new Slab(2,VehicleType.CAR,2,4,15);
        Slab slab3=new Slab(3,VehicleType.CAR,4,8,25);
        Slab slab4=new Slab(4,VehicleType.CAR,8,-1,30);
        Map<Integer,Slab>slabMap=new HashMap<>(){{
            put(1,slab1);
            put(2,slab2);
            put(3,slab3);
            put(4,slab4);
        }};

        SlabRepository slabRepository=new SlabRepository(slabMap);
        InvoiceRepository invoiceRepository=new InvoiceRepository();
        CalculatefeesStrategyFactory calculatefeesStrategyFactory=new CalculatefeesStrategyFactory(slabRepository);


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

        InvoiceService invoiceService=new InvoiceServiceImpl(ticketService,gateService,calculatefeesStrategyFactory,invoiceRepository);
        TicketController ticketController=new TicketController(ticketService);
        InvoiceController invoiceController=new InvoiceController(invoiceService);

        GenerateTicketRequestDto requestDto=new GenerateTicketRequestDto();
        requestDto.setGateId(1);
        requestDto.setVehicleType(VehicleType.CAR.toString());
        requestDto.setVehicleNumber("MH 23 1234");

        GenerateTicketResponseDto responseDto = ticketController.generateTicket(requestDto);
        System.out.println(responseDto);
        int ticketId = responseDto.getTicket().getId();

        requestDto.setVehicleNumber("MH1 3454");
        responseDto=ticketController.generateTicket(requestDto);
        System.out.println(responseDto);
        try{
            Thread.sleep(5000);
        }catch (Exception e){
            System.out.println("Exception while sleep");
        }

        GenerateInvoiceRequestDto invoiceRequestDto=new GenerateInvoiceRequestDto();
        invoiceRequestDto.setTicketId(ticketId);
        invoiceRequestDto.setGateId(gate2.getId());

        GenerateInvoiceResponseDto generateInvoiceResponseDto = invoiceController.generateInvoice(invoiceRequestDto);

        System.out.println(generateInvoiceResponseDto);

    }
}