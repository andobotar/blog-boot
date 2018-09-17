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

import com.progmasters.mordor.domain.Horde;

public class HordeListItem {

    private Long id;

    private String hordeName;

    private Integer orcsCount;

    private String leaderName;

    public HordeListItem(Horde horde) {
        this.id = horde.getId();
        this.hordeName = horde.getHordeName();
        this.orcsCount = horde.getOrcs().size();
        if (horde.getLeader() != null) {
            this.leaderName = horde.getLeader().getName();
        }
    }

    public Long getId() {
        return id;
    }

    public String getHordeName() {
        return hordeName;
    }

    public Integer getOrcsCount() {
        return orcsCount;
    }

    public String getLeaderName() {
        return leaderName;
    }

}
