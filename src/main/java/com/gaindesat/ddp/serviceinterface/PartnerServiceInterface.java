package com.gaindesat.ddp.serviceinterface;

import com.gaindesat.ddp.dto.PartnerDTO;
import com.gaindesat.ddp.models.Partner;

public interface PartnerServiceInterface {

    Partner populatePartner(PartnerDTO partnerDTO, Partner partner);
}
