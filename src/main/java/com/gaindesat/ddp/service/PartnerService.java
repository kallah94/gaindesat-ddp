package com.gaindesat.ddp.service;

import com.gaindesat.ddp.dto.PartnerDTO;
import com.gaindesat.ddp.models.Partner;
import com.gaindesat.ddp.serviceinterface.PartnerServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class PartnerService implements PartnerServiceInterface {
    @Override
    public Partner populatePartner(PartnerDTO partnerDTO, Partner persistencePartner) {
        persistencePartner.setCode(partnerDTO.getCode());
        persistencePartner.setPartName(partnerDTO.getName());
        return persistencePartner;
    }
}
