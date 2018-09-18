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

package com.progmasters.blog.service;

import com.progmasters.blog.domain.Post;
import com.progmasters.blog.domain.dto.PostDetails;
import com.progmasters.blog.domain.dto.PostFormDetails;
import com.progmasters.blog.domain.dto.PostListItem;
import com.progmasters.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(PostFormDetails postFormDetails) {
        Post post = new Post(postFormDetails);
        return postRepository.save(post);
    }

    public List<PostListItem> getPostListItems() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostListItem> postListItems =
                posts.stream().map(PostListItem::new).collect(Collectors.toList());
        return postListItems;
    }

    public PostDetails getPostDetailsById(Long id) {
        Post post = postRepository.getOne(id);
        if (post != null) {
            return new PostDetails(postRepository.getOne(id));
        }
        return null;
    }
}
