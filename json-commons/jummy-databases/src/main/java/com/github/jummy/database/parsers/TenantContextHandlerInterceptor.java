package com.github.jummy.database.parsers;

import cn.hutool.core.util.StrUtil;
import com.github.jummy.context.BaseContextConstants;
import com.github.jummy.context.BaseContextHandler;
import com.github.jummy.utils.StrHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: jummy
 * @create_date: 14/09/2021 10:44 上午
 * @desc:
 */
@Slf4j
@AllArgsConstructor
public class TenantContextHandlerInterceptor extends HandlerInterceptorAdapter {

    private String databaseName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            log.info("not exec!!! url={}", request.getRequestURL());
            return super.preHandle(request, response, handler);
        }
        BaseContextHandler.setDatabase(databaseName);
        BaseContextHandler.setTenant(getHeader(request, BaseContextConstants.TENANT));
        return super.preHandle(request, response, handler);
    }

    private String getHeader(HttpServletRequest request, String name) {
        String value = request.getHeader(name);
        if (StrUtil.isEmpty(value)) {
            value = request.getParameter(name);
        }
        if (StrUtil.isEmpty(value)) {
            return StrUtil.EMPTY;
        }
        return StrHelper.decode(value);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
