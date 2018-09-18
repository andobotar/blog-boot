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

package com.progmasters.blog.domain.dto;

import com.progmasters.blog.domain.Comment;

import static com.progmasters.blog.config.SpringWebConfig.DATE_TIME_FORMATTER;

public class CommentDetails {

    private String author;
    private String commentBody;
    private String createdAt;

    public CommentDetails(Comment comment) {
        this.author = comment.getAuthor();
        this.commentBody = comment.getCommentBody();
        this.createdAt = DATE_TIME_FORMATTER.format(comment.getCreatedAt());
    }

    public String getAuthor() {
        return author;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
