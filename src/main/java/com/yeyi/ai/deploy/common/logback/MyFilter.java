package com.yeyi.ai.deploy.common.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class MyFilter extends Filter<ILoggingEvent> {

    Level level;

    @Override
    public FilterReply decide(ILoggingEvent event) {

        if (!this.isStarted()) {
            return FilterReply.NEUTRAL;
        } else {
//            System.out.println("输出日志服务");
            return event.getLevel().isGreaterOrEqual(this.level) ? FilterReply.NEUTRAL : FilterReply.DENY;
        }
    }


    public void setLevel(String level) {
        this.level = Level.toLevel(level);
    }

    public void start() {
        if (this.level != null) {
            super.start();
        }

    }
}
