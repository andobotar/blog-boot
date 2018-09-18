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

import com.progmasters.blog.domain.dto.PostDetails;
import com.progmasters.blog.domain.dto.PostFormDetails;
import com.progmasters.blog.domain.dto.PostListItem;
import com.progmasters.blog.service.PostService;
import com.progmasters.blog.validator.PostFormDetailsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private PostService postService;
    private PostFormDetailsValidator postFormDetailsValidator;

    @Autowired
    public PostController(PostService postService,
                          PostFormDetailsValidator postFormDetailsValidator) {
        this.postService = postService;
        this.postFormDetailsValidator = postFormDetailsValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(postFormDetailsValidator);
    }

    @PostMapping
    public ResponseEntity createPost(@Valid @RequestBody PostFormDetails postFormDetails) {
        logger.info("New post is created");

        postService.createPost(postFormDetails);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostListItem>> getPostList() {
        logger.info("Post list page is requested");

        return new ResponseEntity<>(postService.getPostListItems(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetails> getPost(@PathVariable("id") Long id) {
        PostDetails postDetails = postService.getPostDetailsById(id);
        if (postDetails != null) {
            return new ResponseEntity<>(postDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
