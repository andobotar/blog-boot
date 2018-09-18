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

package com.progmasters.blog.controller;

import com.progmasters.blog.domain.Comment;
import com.progmasters.blog.domain.dto.CommentFormDetails;
import com.progmasters.blog.service.CommentService;
import com.progmasters.blog.validator.CommentFormDetailsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private CommentService commentService;
    private CommentFormDetailsValidator commentFormDetailsValidator;

    @Autowired
    public CommentController(CommentService commentService,
                             CommentFormDetailsValidator commentFormDetailsValidator) {
        this.commentService = commentService;
        this.commentFormDetailsValidator = commentFormDetailsValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(commentFormDetailsValidator);
    }

    @PostMapping
    public ResponseEntity createComment(@Valid @RequestBody CommentFormDetails commentFormDetails) {
        logger.info("New comment is created");

        Comment commentCreated = commentService.createComment(commentFormDetails);
        if (commentCreated != null) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
