/*
 * Copyright © Progmasters (QTC Kft.), 2018.
 * All rights reserved. No part or the whole of this Teaching Material (TM) may be reproduced, copied, distributed,
 * publicly performed, disseminated to the public, adapted or transmitted in any form or by any means, including
 * photocopying, recording, or other electronic or mechanical methods, without the prior written permission of QTC Kft.
 * This TM may only be used for the purposes of teaching exclusively by QTC Kft. and studying exclusively by QTC Kft.’s
 * students and for no other purposes by any parties other than QTC Kft.
 * This TM shall be kept confidential and shall not be made public or made available or disclosed to any unauthorized person.
 * Any dispute or claim arising out of the breach of these provisions shall be governed by and construed in accordance with the laws of Hungary.
 */

package com.progmasters.mordor.service;

import com.progmasters.mordor.domain.Horde;
import com.progmasters.mordor.domain.Orc;
import com.progmasters.mordor.dto.HordeDetails;
import com.progmasters.mordor.dto.HordeListItem;
import com.progmasters.mordor.repository.HordeRepository;
import com.progmasters.mordor.repository.OrcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HordeService {

    private HordeRepository hordeRepository;

    private OrcRepository orcRepository;

    @Autowired
    public HordeService(HordeRepository hordeRepository, OrcRepository orcRepository) {
        this.hordeRepository = hordeRepository;
        this.orcRepository = orcRepository;
    }

    public Horde saveHorde(HordeDetails hordeDetails) {
        Horde horde = new Horde();
        updateValues(hordeDetails, horde);

        return hordeRepository.save(horde);
    }

    public Horde updateHorde(HordeDetails hordeDetails, Long id) {
        Horde horde = hordeRepository.getOne(id);
        if (horde != null) {
            updateValues(hordeDetails, horde);
        }

        return horde;
    }

    private void updateValues(HordeDetails hordeDetails, Horde horde) {
        horde.setHordeName(hordeDetails.getName());
        if (hordeDetails.getLeaderId() != null) {
            horde.setLeader(orcRepository.getOne(hordeDetails.getLeaderId()));
        }
    }

    public HordeDetails getHordeDetails(Long id) {
        Horde horde = hordeRepository.getOne(id);
        return new HordeDetails(horde);
    }

    public List<HordeListItem> listHordes() {
        return hordeRepository.findAll().stream()
                .map(HordeListItem::new).collect(Collectors.toList());
    }

    public void deletehorde(Long id) {
        Horde horde = hordeRepository.getOne(id);
        List<Orc> orcsInHordeAboutToBeDeleted = orcRepository.findAllByHorde(horde);
        for (Orc orc : orcsInHordeAboutToBeDeleted) {
            orc.setHorde(null);
            orcRepository.save(orc);
        }
        hordeRepository.delete(horde);
    }
}
