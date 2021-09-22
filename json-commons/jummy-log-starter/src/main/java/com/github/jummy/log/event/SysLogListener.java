package com.github.jummy.log.event;

import cn.hutool.core.util.StrUtil;
import com.github.jummy.context.BaseContextHandler;
import com.github.jummy.log.entity.OptLogDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.function.Consumer;

/**
 * @author: jummy
 * @create_date: 15/09/2021 9:12 上午
 * @desc:
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener {

    private String database;
    private Consumer<OptLogDTO> consumer;

    @Async
    @Order
    @EventListener
    public void saveSysLog(SysLogEvent event) {
        OptLogDTO sysLog = (OptLogDTO) event.getSource();
        if (sysLog == null || StrUtil.isEmpty(sysLog.getTenantCode())) {
            log.warn("租户编码不存在, 忽略操作日志=={}", sysLog.getRequestUri());
            return;
        }
        BaseContextHandler.setDatabase(database);
        BaseContextHandler.setTenant(sysLog.getTenantCode());

        consumer.accept(sysLog);
    }
}
