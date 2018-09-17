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

import com.progmasters.mordor.domain.Horde;
import com.progmasters.mordor.dto.HordeDetails;
import com.progmasters.mordor.dto.HordeListItem;
import com.progmasters.mordor.service.HordeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/hordes")
public class HordeController {

    private final HordeService hordeService;

    @Autowired
    public HordeController(HordeService hordeService) {
        this.hordeService = hordeService;
    }

    @GetMapping
    public ResponseEntity<List<HordeListItem>> getHordes() {
        return new ResponseEntity<>(hordeService.listHordes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HordeDetails> getHorde(@PathVariable Long id) {
        return new ResponseEntity<>(hordeService.getHordeDetails(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createHorde(@Valid @RequestBody HordeDetails hordeDetails) {
        hordeService.saveHorde(hordeDetails);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HordeDetails> updateHorde(@Valid @RequestBody HordeDetails hordeDetails, @PathVariable Long id) {
        Horde updatedHorde = hordeService.updateHorde(hordeDetails, id);
        ResponseEntity<HordeDetails> result;

        if (updatedHorde == null) {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            result = new ResponseEntity<>(new HordeDetails(updatedHorde), HttpStatus.OK);
        }

        return result;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHorde(@PathVariable Long id) {
        hordeService.deletehorde(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
