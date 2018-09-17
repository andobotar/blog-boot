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

package com.progmasters.mordor.dto;

import com.progmasters.mordor.domain.Orc;
import com.progmasters.mordor.domain.WeaponType;

import java.util.List;
import java.util.stream.Collectors;

public class OrcListItem {

    private Long id;

    private String name;

    private String orcRaceType;

    private Long killCount;

    private List<String> weapons;

    private String hordeName;

    public OrcListItem(Orc orc) {
        this.id = orc.getId();
        this.name = orc.getName();
        this.orcRaceType = orc.getOrcRaceType().getDisplayName();
        this.killCount = orc.getKillCount();
        this.weapons = orc.getWeapons().stream().map(WeaponType::getDisplayName).collect(Collectors.toList());
        if (orc.getHorde() != null) {
            this.hordeName = orc.getHorde().getHordeName();
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOrcRaceType() {
        return orcRaceType;
    }

    public Long getKillCount() {
        return killCount;
    }

    public List<String> getWeapons() {
        return weapons;
    }

    public String getHordeName() {
        return hordeName;
    }
}