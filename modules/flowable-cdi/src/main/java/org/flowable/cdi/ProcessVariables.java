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
package org.flowable.cdi;

import java.util.Map;

import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.flowable.cdi.annotation.ProcessVariable;
import org.flowable.cdi.impl.ProcessVariableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Allows to access the process variables of a managed process instance. A process instance can be managed, using the {@link BusinessProcess}-bean.
 * 
 * @author Daniel Meyer
 */
public class ProcessVariables {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessVariables.class);

    @Inject
    private BusinessProcess businessProcess;
    @Inject
    private ProcessVariableMap processVariableMap;

    protected String getVariableName(InjectionPoint ip) {
        String variableName = ip.getAnnotated().getAnnotation(ProcessVariable.class).value();
        if (variableName.length() == 0) {
            variableName = ip.getMember().getName();
        }
        return variableName;
    }

    @Produces
    @ProcessVariable
    protected Object getProcessVariable(InjectionPoint ip) {
        String processVariableName = getVariableName(ip);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Getting process variable '{}' from ProcessInstance[{}].", processVariableName, businessProcess.getProcessInstanceId());
        }

        return businessProcess.getVariable(processVariableName);
    }

    @Produces
    @Named
    protected Map<String, Object> processVariables() {
        return processVariableMap;
    }

}
