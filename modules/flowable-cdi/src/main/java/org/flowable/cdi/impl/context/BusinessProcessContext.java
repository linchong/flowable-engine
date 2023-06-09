/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.cdi.impl.context;

import java.lang.annotation.Annotation;

import jakarta.enterprise.context.spi.Context;
import jakarta.enterprise.context.spi.Contextual;
import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanManager;

import org.flowable.cdi.BusinessProcess;
import org.flowable.cdi.annotation.BusinessProcessScoped;
import org.flowable.cdi.impl.util.ProgrammaticBeanLookup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the BusinessProcessContext-scope.
 * 
 * @author Daniel Meyer
 */
@SuppressWarnings("unchecked")
public class BusinessProcessContext implements Context {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BusinessProcessContext.class);

    private final BeanManager beanManager;

    public BusinessProcessContext(BeanManager beanManager) {
        this.beanManager = beanManager;
    }

    protected BusinessProcess getBusinessProcess() {
        return ProgrammaticBeanLookup.lookup(BusinessProcess.class, beanManager);
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return BusinessProcessScoped.class;
    }

    @Override
    public <T> T get(Contextual<T> contextual) {
        Bean<T> bean = (Bean<T>) contextual;
        String variableName = bean.getName();

        BusinessProcess businessProcess = getBusinessProcess();
        Object variable = businessProcess.getVariable(variableName);
        if (variable != null) {
            if (LOGGER.isDebugEnabled()) {
                if (businessProcess.isAssociated()) {
                    LOGGER.debug("Getting instance of bean '{}' from Execution[{}]", variableName, businessProcess.getExecutionId());
                } else {
                    LOGGER.debug("Getting instance of bean '{}' from transient bean store", variableName);
                }
            }

            return (T) variable;
        } else {
            return null;
        }

    }

    @Override
    public <T> T get(Contextual<T> contextual, CreationalContext<T> arg1) {

        Bean<T> bean = (Bean<T>) contextual;
        String variableName = bean.getName();

        BusinessProcess businessProcess = getBusinessProcess();
        Object variable = businessProcess.getVariable(variableName);
        if (variable != null) {
            if (LOGGER.isDebugEnabled()) {
                if (businessProcess.isAssociated()) {
                    LOGGER.debug("Getting instance of bean '{}' from Execution[{}]", variableName, businessProcess.getExecutionId());
                } else {
                    LOGGER.debug("Getting instance of bean '{}' from transient bean store", variableName);
                }
            }

            return (T) variable;
        } else {

            if (LOGGER.isDebugEnabled()) {
                if (businessProcess.isAssociated()) {
                    LOGGER.debug("Creating instance of bean '{}' in business process context representing Execution[{}]", variableName, businessProcess.getExecutionId());
                } else {
                    LOGGER.debug("Creating instance of bean '{}' in transient bean store", variableName);
                }
            }

            T beanInstance = bean.create(arg1);
            businessProcess.setVariable(variableName, beanInstance);
            return beanInstance;
        }

    }

    @Override
    public boolean isActive() {
        // we assume the business process is always 'active'. If no
        // task/execution is
        // associated, temporary instances of @BusinessProcessScoped beans are
        // cached in the
        // conversation / request
        return true;
    }

}
