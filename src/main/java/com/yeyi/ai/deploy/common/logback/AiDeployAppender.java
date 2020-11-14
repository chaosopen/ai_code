package com.yeyi.ai.deploy.common.logback;

import ch.qos.logback.core.UnsynchronizedAppenderBase;
import org.springframework.stereotype.Component;

@Component
public class AiDeployAppender<E> extends UnsynchronizedAppenderBase<E> {


    @Override
    protected void append(E e) {

    }
}
