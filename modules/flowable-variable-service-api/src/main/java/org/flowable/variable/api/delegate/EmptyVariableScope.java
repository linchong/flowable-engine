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
package org.flowable.variable.api.delegate;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.flowable.variable.api.persistence.entity.VariableInstance;

/**
 * Null object implementation of the VariableScope.
 *
 * @see VariableScope#empty()
 */
class EmptyVariableScope implements VariableScope {
    static final EmptyVariableScope INSTANCE = new EmptyVariableScope();

    public Map<String, Object> getVariables() {
        return Collections.emptyMap();
    }

    public Map<String, Object> getVariablesLocal() {
        return Collections.emptyMap();
    }

    public Map<String, Object> getVariables(Collection<String> variableNames) {
        return Collections.emptyMap();
    }

    public Map<String, Object> getVariables(Collection<String> variableNames, boolean fetchAllVariables) {
        return Collections.emptyMap();
    }

    public Map<String, Object> getVariablesLocal(Collection<String> variableNames) {
        return Collections.emptyMap();
    }

    public Map<String, Object> getVariablesLocal(Collection<String> variableNames, boolean fetchAllVariables) {
        return Collections.emptyMap();
    }

    public Object getVariable(String variableName) {
        return null;
    }

    public Object getVariable(String variableName, boolean fetchAllVariables) {
        return null;
    }

    public Object getVariableLocal(String variableName) {
        return null;
    }

    public Object getVariableLocal(String variableName, boolean fetchAllVariables) {
        return null;
    }

    public <T> T getVariable(String variableName, Class<T> variableClass) {
        return null;
    }

    public <T> T getVariableLocal(String variableName, Class<T> variableClass) {
        return null;
    }

    public Set<String> getVariableNames() {
        return Collections.emptySet();
    }

    public Set<String> getVariableNamesLocal() {
        return null;
    }

    public Map<String, VariableInstance> getVariableInstances() {
        return null;
    }

    public Map<String, VariableInstance> getVariableInstances(Collection<String> variableNames) {
        return null;
    }

    public Map<String, VariableInstance> getVariableInstances(Collection<String> variableNames, boolean fetchAllVariables) {
        return null;
    }

    public Map<String, VariableInstance> getVariableInstancesLocal() {
        return null;
    }

    public Map<String, VariableInstance> getVariableInstancesLocal(Collection<String> variableNames) {
        return null;
    }

    public Map<String, VariableInstance> getVariableInstancesLocal(Collection<String> variableNames, boolean fetchAllVariables) {
        return null;
    }

    public VariableInstance getVariableInstance(String variableName) {
        return null;
    }

    public VariableInstance getVariableInstance(String variableName, boolean fetchAllVariables) {
        return null;
    }

    public VariableInstance getVariableInstanceLocal(String variableName) {
        return null;
    }

    public VariableInstance getVariableInstanceLocal(String variableName, boolean fetchAllVariables) {
        return null;
    }

    public void setVariable(String variableName, Object value) {
        throw new UnsupportedOperationException("Empty object, no variables can be set");
    }

    public void setVariable(String variableName, Object value, boolean fetchAllVariables) {
        throw new UnsupportedOperationException("Empty object, no variables can be set");
    }

    public Object setVariableLocal(String variableName, Object value) {
        throw new UnsupportedOperationException("Empty object, no variables can be set");
    }

    public Object setVariableLocal(String variableName, Object value, boolean fetchAllVariables) {
        throw new UnsupportedOperationException("Empty object, no variables can be set");
    }

    public void setVariables(Map<String, ? extends Object> variables) {
        throw new UnsupportedOperationException("Empty object, no variables can be set");
    }

    public void setVariablesLocal(Map<String, ? extends Object> variables) {
        throw new UnsupportedOperationException("Empty object, no variables can be set");
    }

    public boolean hasVariables() {
        return false;
    }

    public boolean hasVariablesLocal() {
        return false;
    }

    public boolean hasVariable(String variableName) {
        return false;
    }

    public boolean hasVariableLocal(String variableName) {
        return false;
    }

    public void createVariableLocal(String variableName, Object value) {
        throw new UnsupportedOperationException("Empty object, no variables can be created");
    }

    public void createVariablesLocal(Map<String, ? extends Object> variables) {
        throw new UnsupportedOperationException("Empty object, no variables can be created");
    }

    public void removeVariable(String variableName) {
        throw new UnsupportedOperationException("Empty object, no variables can be removed");
    }

    public void removeVariableLocal(String variableName) {
        throw new UnsupportedOperationException("Empty object, no variables can be removed");
    }

    public void removeVariables() {
        throw new UnsupportedOperationException("Empty object, no variables can be removed");
    }

    public void removeVariablesLocal() {
        throw new UnsupportedOperationException("Empty object, no variables can be removed");
    }

    public void removeVariables(Collection<String> variableNames) {
        throw new UnsupportedOperationException("Empty object, no variables can be removed");
    }

    public void removeVariablesLocal(Collection<String> variableNames) {
        throw new UnsupportedOperationException("Empty object, no variables can be removed");
    }

    public void setTransientVariablesLocal(Map<String, Object> transientVariables) {
        throw new UnsupportedOperationException("Empty object, no variables can be set");
    }

    public void setTransientVariableLocal(String variableName, Object variableValue) {
        throw new UnsupportedOperationException("Empty object, no variables can be set");
    }

    public void setTransientVariables(Map<String, Object> transientVariables) {
        throw new UnsupportedOperationException("Empty object, no variables can be set");
    }

    public void setTransientVariable(String variableName, Object variableValue) {
        throw new UnsupportedOperationException("Empty object, no variables can be set");
    }

    public Object getTransientVariableLocal(String variableName) {
        return null;
    }

    public Map<String, Object> getTransientVariablesLocal() {
        return null;
    }

    public Object getTransientVariable(String variableName) {
        return null;
    }

    public Map<String, Object> getTransientVariables() {
        return null;
    }

    public void removeTransientVariableLocal(String variableName) {
        throw new UnsupportedOperationException("Empty object, no variables can be removed");
    }

    public void removeTransientVariablesLocal() {
        throw new UnsupportedOperationException("Empty object, no variables can be removed");
    }

    public void removeTransientVariable(String variableName) {
        throw new UnsupportedOperationException("Empty object, no variables can be removed");
    }

    public void removeTransientVariables() {
        throw new UnsupportedOperationException("Empty object, no variables can be removed");
    }

    public String getTenantId() {
        return null;
    }
}
