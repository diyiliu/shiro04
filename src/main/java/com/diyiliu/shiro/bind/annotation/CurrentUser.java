package com.diyiliu.shiro.bind.annotation;

import com.diyiliu.shiro.bind.Constants;

import java.lang.annotation.*;

/**
 * Description: CurrentUser
 * Author: DIYILIU
 * Update: 2015-10-28 16:21
 */

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default Constants.CURRENT_USER;
}
