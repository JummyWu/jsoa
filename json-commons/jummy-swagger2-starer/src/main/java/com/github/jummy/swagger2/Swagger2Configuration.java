package com.github.jummy.swagger2;

import com.github.xiaoymin.swaggerbootstrapui.configuration.SwaggerBootstrapUIConfiguration;
import com.github.xiaoymin.swaggerbootstrapui.filter.ProductionSecurityFilter;
import com.github.xiaoymin.swaggerbootstrapui.filter.SecurityBasicAuthFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: jummy
 * @create_date: 14/09/2021 4:08 下午
 * @desc:
 */
@ConditionalOnProperty(name = "jummy.swagger.enabled", havingValue = "true", matchIfMissing = true)
@EnableSwagger2
@Import({BeanValidatorPluginsConfiguration.class, SwaggerBootstrapUIConfiguration.class, Swagger2Configuration.JummySecurityConfiguration.class})
public class Swagger2Configuration implements WebMvcConfigurer {

    /**
     * spring boot 不会自动注入swagger的资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META_INF/resources/webjars/");
    }

    @Slf4j
    public static class JummySecurityConfiguration {

        /**
         * 生产环境安全策略
         *
         * @param swaggerProperties
         * @return
         */
        @Bean
        @ConditionalOnBean
        @ConditionalOnProperty(name = "jummy.swagger.production", havingValue = "true")
        public ProductionSecurityFilter productionSecurityFilter(SwaggerProperties swaggerProperties) {
            return new ProductionSecurityFilter(swaggerProperties.getProduction());
        }

        /**
         * 接口文档密码设置
         *
         * @param swaggerProperties
         * @return
         */
        @Bean
        @ConditionalOnBean
        @ConditionalOnProperty(name = "jummy.swagger.basic.enable", havingValue = "true")
        public SecurityBasicAuthFilter securityBasicAuthFilter(SwaggerProperties swaggerProperties) {
            SwaggerProperties.Basic basic = swaggerProperties.getBasic();
            return new SecurityBasicAuthFilter(basic.getEnable(), basic.getUsername(), basic.getPassword());
        }
    }
}
