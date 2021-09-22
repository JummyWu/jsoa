package com.github.jummy.log.utils;

import com.github.jummy.log.annotation.SysLog;
import org.aspectj.lang.JoinPoint;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

/**
 * @author: jummy
 * @create_date: 15/09/2021 9:23 上午
 * @desc:
 */
public class LogUtil {

    public static String getControllerMethodDescription(JoinPoint point) {
        try {
            // 获取连接点目标类名
            String targetName = point.getTarget().getClass().getName();
            //获取链接点签名的方法
            String methodName = point.getSignature().getName();
            // 获取连接点参数
            Object[] args = point.getArgs();
            // 根据连接点类的名字获取指定类
            Class targetClass = Class.forName(targetName);
            //获取类的方法
            Method[] methods = targetClass.getMethods();
            String description = "";
            for (Method method: methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == args.length) {
                        description = method.getAnnotation(SysLog.class).value();
                        break;
                    }
                }
            }
            return description;
        } catch (ClassNotFoundException e) {
            return "";
        }
    }

    /**
     * 获取堆栈信息
     *
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

}
