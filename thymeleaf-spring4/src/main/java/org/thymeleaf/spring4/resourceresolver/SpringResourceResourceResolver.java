/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.spring4.resourceresolver;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.TemplateProcessingParameters;
import org.thymeleaf.resourceresolver.IResourceResolver;
import org.thymeleaf.util.Validate;

/**
 * <p>
 *   Implementation of {@link org.thymeleaf.resourceresolver.IResourceResolver} that resolves
 *   resources by delegating on Spring's resource resolution mechanism, implemented by the
 *   {@link org.springframework.core.io.ResourceLoader} interface.
 * </p>
 * <p>
 *   This resource resolver accesses the Spring resource resolution mechanism by means of
 *   calls to {@link ApplicationContext#getResource(String)}.
 * </p>
 *
 * @author Daniel Fern&aacute;ndez
 *
 * @since 2.1.0
 *
 */
public final class SpringResourceResourceResolver
        implements IResourceResolver, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(SpringResourceResourceResolver.class);

    public static final String NAME = "SPRING-RESOURCE";

    private ApplicationContext applicationContext = null;



    public SpringResourceResourceResolver() {
        super();
    }


    public String getName() {
        return NAME; 
    }

    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    
    public InputStream getResourceAsStream(
            final TemplateProcessingParameters templateProcessingParameters, final String resourceName) {

        Validate.notNull(resourceName, "Resource name cannot be null");
        Validate.notNull(this.applicationContext,
                "ApplicationContext has not been initialized in resource resolver. TemplateResolver or " +
                "ResourceResolver might not have been correctly configured by the Spring Application Context.");

        try {

            final Resource resource = this.applicationContext.getResource(resourceName);
            if (resource == null) {
                return null;
            }

            return resource.getInputStream();

        } catch (final IOException e) {

            if (logger.isDebugEnabled()) {
                if (logger.isTraceEnabled()) {
                    logger.trace(
                            String.format(
                                    "[THYMELEAF][%s][%s] Resource \"%s\" could not be resolved. This can be normal as " +
                                            "maybe this resource is not intended to be resolved by this resolver. " +
                                            "Exception is provided for tracing purposes: ",
                                    TemplateEngine.threadIndex(), templateProcessingParameters.getTemplateName(),
                                    resourceName),
                            e);
                } else {
                    logger.debug(
                            String.format(
                                    "[THYMELEAF][%s][%s] Resource \"%s\" could not be resolved. This can be normal as " +
                                            "maybe this resource is not intended to be resolved by this resolver. " +
                                            "Exception message is provided: %s: %s",
                                    TemplateEngine.threadIndex(), templateProcessingParameters.getTemplateName(),
                                    resourceName, e.getClass().getName(), e.getMessage()));
                }
            }

            return null;

        }

    }

    
}
