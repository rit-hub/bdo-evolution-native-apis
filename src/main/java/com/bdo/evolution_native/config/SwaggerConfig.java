package com.bdo.evolution_native.config;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.model.ErrorResponse;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This is for swagger configuration
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        final TypeResolver typeResolver = new TypeResolver();
        return new Docket(DocumentationType.SWAGGER_2)
            .additionalModels(
                typeResolver.resolve(ErrorResponse.class)
            )
            .apiInfo(getApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(EvolutionConstantUtils.BASE_PACKAGE))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
            .title(EvolutionConstantUtils.API_TITLE)
            .description(EvolutionConstantUtils.API_TITLE)
            .version(EvolutionConstantUtils.VERSION)
            .build();
    }

    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(
        final WebEndpointsSupplier webEndpointsSupplier, final ServletEndpointsSupplier servletEndpointsSupplier,
        final ControllerEndpointsSupplier controllerEndpointsSupplier,
        final EndpointMediaTypes endpointMediaTypes, final CorsEndpointProperties corsProperties,
        final WebEndpointProperties webEndpointProperties, final Environment environment) {
        final List<ExposableEndpoint<?>> allEndpoints = new ArrayList<>();
        final Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        final String basePath = webEndpointProperties.getBasePath();
        final EndpointMapping endpointMapping = new EndpointMapping(basePath);
        final boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment,
            basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes,
            corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath),
            shouldRegisterLinksMapping, null);
    }

    private boolean shouldRegisterLinksMapping(final WebEndpointProperties webEndpointProperties,
                                               final Environment environment,
                                               final String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath)
            || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }
}
