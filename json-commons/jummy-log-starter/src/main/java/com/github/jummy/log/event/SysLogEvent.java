package com.github.jummy.log.event;

import com.github.jummy.log.entity.OptLogDTO;
import org.springframework.context.ApplicationEvent;

/**
 * @author: jummy
 * @create_date: 15/09/2021 9:11 上午
 * @desc:
 */
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(OptLogDTO source) {
        super(source);
    }
}
