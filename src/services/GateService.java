package services;

import models.Gate;
import repositories.GateRepository;

public class GateService {
    private GateRepository gateRepository;

    public GateService(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    public Gate getGateById(int gatewId){
        return gateRepository.getgateById(gatewId);
    }
}
