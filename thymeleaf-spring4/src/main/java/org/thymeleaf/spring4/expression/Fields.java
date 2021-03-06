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
package org.thymeleaf.spring4.expression;

import java.util.List;

import org.thymeleaf.Configuration;
import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.spring4.util.DetailedError;
import org.thymeleaf.spring4.util.FieldUtils;



/**
 * 
 * @author Daniel Fern&aacute;ndez
 * @author Tobias Gafner
 * 
 * @since 1.0
 *
 */
public final class Fields {

    private final Configuration configuration;
    private final IProcessingContext processingContext;
    
    /**
     *
     * @return the result
     * @since 2.1.0
     */
    public boolean hasAnyErrors() {
        return FieldUtils.hasAnyErrors(this.configuration, this.processingContext);
    }
    
    /**
     *
     * @return the result
     * @since 2.1.0
     */
    public boolean hasErrors() {
        return FieldUtils.hasAnyErrors(this.configuration, this.processingContext);
    }
    
    public boolean hasErrors(final String field) {
        return FieldUtils.hasErrors(this.configuration, this.processingContext, field);
    }
    
    /**
     * 
     * @return the result
     * @since 2.1.0
     */
    public boolean hasGlobalErrors() {
        return FieldUtils.hasGlobalErrors(this.configuration, this.processingContext);
    }

    /**
     * 
     * @return the result
     * @since 2.1.0
     */
    public List<String> allErrors() {
        return FieldUtils.errors(this.configuration, this.processingContext);
    }

    /**
     * 
     * @return the result
     * @since 2.1.0
     */
    public List<String> errors() {
        return FieldUtils.errors(this.configuration, this.processingContext);
    }

    public List<String> errors(final String field) {
        return FieldUtils.errors(this.configuration, this.processingContext, field);
    }
    
    /**
     * 
     * @return the result
     * @since 2.1.0
     */
    public List<String> globalErrors() {
        return FieldUtils.globalErrors(this.configuration, this.processingContext);
    }

    
    public String idFromName(final String fieldName) {
        return FieldUtils.idFromName(fieldName);
    }



    /**
     * 
     * @return the result
     * @since 2.1.2
     */
    public List<DetailedError> detailedErrors() {
        return FieldUtils.detailedErrors(this.configuration, this.processingContext);
    }


    
    public Fields(final Configuration configuration, final IProcessingContext processingContext) {
        super();
        this.configuration = configuration;
        this.processingContext = processingContext;
    }

    
}
