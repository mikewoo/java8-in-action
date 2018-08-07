package com.mikewoo.study.java8.date;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * mybatis中使用Java8的日期LocalDate、LocalDateTime
 * 默认的情况下，在mybatis里面不支持java8的时间、日期。直接使用，会报如下错误, Caused by: java.lang.IllegalStateException: No typehandler found for property createTime
 * 解决方法如下：
 * 1. 直接加入如下依赖
 * <dependency>
 * 	<groupId>org.mybatis</groupId>
 * 	<artifactId>mybatis-typehandlers-jsr310</artifactId>
 * 	<version>1.0.2</version>
 * </dependency>
 * 2. 配置好这个依赖之后，就可以把Entity里面的Date替换成LocalDate、LocalDateTime了，其他的不用改
 * 3. 如果使用的mybatis版本低于3.4.0，则还需要配置如下
 * <typeHandlers>
 * 	<typeHandler handler="org.apache.ibatis.type.InstantTypeHandler" />
 * 	<typeHandler handler="org.apache.ibatis.type.LocalDateTimeTypeHandler" />
 * 	<typeHandler handler="org.apache.ibatis.type.LocalDateTypeHandler" />
 * 	<typeHandler handler="org.apache.ibatis.type.LocalTimeTypeHandler" />
 * 	<typeHandler handler="org.apache.ibatis.type.OffsetDateTimeTypeHandler" />
 * 	<typeHandler handler="org.apache.ibatis.type.OffsetTimeTypeHandler" />
 * 	<typeHandler handler="org.apache.ibatis.type.ZonedDateTimeTypeHandler" />
 * </typeHandlers>
 * @author Eric Gui
 * @date 2018/8/7
 */
public class User {

    private Long id;

    private String name;

    private LocalDate createDate;

    private LocalDateTime createTime;
}
