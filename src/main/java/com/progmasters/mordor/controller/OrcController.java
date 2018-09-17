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

package com.progmasters.mordor.controller;

import com.progmasters.mordor.domain.Orc;
import com.progmasters.mordor.domain.OrcRaceType;
import com.progmasters.mordor.domain.WeaponType;
import com.progmasters.mordor.dto.HordeListItem;
import com.progmasters.mordor.dto.OrcDetails;
import com.progmasters.mordor.dto.OrcFormData;
import com.progmasters.mordor.dto.OrcListItem;
import com.progmasters.mordor.service.HordeService;
import com.progmasters.mordor.service.OrcService;
import com.progmasters.mordor.validator.OrcDetailsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orcs")
public class OrcController {

    private final OrcService orcService;
    private final HordeService hordeService;

    @Autowired
    public OrcController(OrcService orcService, HordeService hordeService) {
        this.hordeService = hordeService;
        this.orcService = orcService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new OrcDetailsValidator());
    }

    @GetMapping("/formData")
    public ResponseEntity<OrcFormData> getOrcFormData() {
        Map<String, String> hordeMap = new LinkedHashMap<>();
        for (HordeListItem horde :  hordeService.listHordes()) {
            hordeMap.put(horde.getId().toString(), horde.getHordeName());
        }

        OrcFormData formData = new OrcFormData(getWeaponTypes(), getOrcRaceTypes(), hordeMap);
        return new ResponseEntity<>(formData, HttpStatus.OK);
    }

    private Map<String, String> getWeaponTypes() {
        Map<String, String> weaponTypeMap = new LinkedHashMap<>();
        for (WeaponType weaponType : WeaponType.values()) {
            weaponTypeMap.put(weaponType.name(), weaponType.getDisplayName());
        }

        return weaponTypeMap;
    }

    private Map<String, String> getOrcRaceTypes() {
        Map<String, String> orcRaceTypeMap = new LinkedHashMap<>();
        for (OrcRaceType orcRaceType : OrcRaceType.values()) {
            orcRaceTypeMap.put(orcRaceType.name(), orcRaceType.getDisplayName());
        }

        return orcRaceTypeMap;
    }

    @GetMapping("/possibleLeaders")
    public ResponseEntity<Map<Long, String>> getPossibleLeaders () {
        return new ResponseEntity<>(orcService.findUrukHaiOrcsWithName().stream()
                .collect(Collectors.toMap(Orc::getId, Orc::getName)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrcListItem>> getOrcs() {
        return new ResponseEntity<>(orcService.listOrcs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrcDetails> getOrc(@PathVariable Long id) {
        return new ResponseEntity<>(orcService.getOrcDetails(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveOrc(@Valid @RequestBody OrcDetails orcDetails) {
        orcService.saveOrc(orcDetails);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrcDetails> updateHorde(@Valid @RequestBody OrcDetails orcDetails, @PathVariable Long id) {
        Orc updatedOrc = orcService.updateOrc(orcDetails, id);
        ResponseEntity<OrcDetails> result;

        if (updatedOrc == null) {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            result = new ResponseEntity<>(new OrcDetails(updatedOrc), HttpStatus.OK);
        }

        return result;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrc(@PathVariable Long id) {
        boolean isDeleteSuccessful = orcService.deleteOrc(id);

        ResponseEntity<?> result;
        if (isDeleteSuccessful) {
            result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return result;
    }

}
