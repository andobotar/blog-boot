/*
 * Copyright © Progmasters (QTC Kft.), 2016-2018.
 * All rights reserved. No part or the whole of this Teaching Material (TM) may be reproduced, copied, distributed, 
 * publicly performed, disseminated to the public, adapted or transmitted in any form or by any means, including 
 * photocopying, recording, or other electronic or mechanical methods, without the prior written permission of QTC Kft. 
 * This TM may only be used for the purposes of teaching exclusively by QTC Kft. and studying exclusively by QTC Kft.’s 
 * students and for no other purposes by any parties other than QTC Kft.
 * This TM shall be kept confidential and shall not be made public or made available or disclosed to any unauthorized person.
 * Any dispute or claim arising out of the breach of these provisions shall be governed by and construed in accordance with the laws of Hungary.
 */

package com.progmasters.mordor.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "horde")
public class Horde {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "horde_name")
    private String hordeName;

    @OneToMany(mappedBy = "horde", fetch = FetchType.EAGER)
    private List<Orc> orcs = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "leader_id")
    private Orc leader;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHordeName() {
        return hordeName;
    }

    public void setHordeName(String hordeName) {
        this.hordeName = hordeName;
    }

    public List<Orc> getOrcs() {
        return orcs;
    }

    public void setOrcs(List<Orc> orcs) {
        this.orcs = orcs;
    }

    public Orc getLeader() {
        return leader;
    }

    public void setLeader(Orc leader) {
        this.leader = leader;
    }

    @Override
    public String toString() {
        return hordeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Horde horde = (Horde) o;

        return id != null ? id.equals(horde.id) : horde.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

