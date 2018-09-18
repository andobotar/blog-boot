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

package com.progmasters.blog.validator;

import com.progmasters.blog.domain.dto.PostFormDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PostFormDetailsValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return PostFormDetails.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PostFormDetails postFormDetails = (PostFormDetails) o;

        if (postFormDetails.getTitle() == null || postFormDetails.getTitle().isEmpty()) {
            errors.rejectValue("title", "postFormDetails.title.empty");
        }
        if (postFormDetails.getPostBody() == null || postFormDetails.getPostBody().isEmpty()) {
            errors.rejectValue("postBody", "postFormDetails.postBody.empty");
        }
    }
}
