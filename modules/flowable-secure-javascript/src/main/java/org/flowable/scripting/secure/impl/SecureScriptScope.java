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
package org.flowable.scripting.secure.impl;

import java.util.Map;

import org.flowable.common.engine.api.variable.VariableContainer;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.task.service.delegate.DelegateTask;
import org.mozilla.javascript.Scriptable;

/**
 * @author Joram Barrez
 */
public class SecureScriptScope implements Scriptable {

    private static final String KEYWORD_EXECUTION = "execution";
    private static final String KEYWORD_TASK = "task";

    protected VariableContainer variableContainer;
    protected Map<Object, Object> beans;

    public SecureScriptScope(VariableContainer variableScope, Map<Object, Object> beans) {
        this.variableContainer = variableScope;
        this.beans = beans;
    }

    @Override
    public String getClassName() {
        return variableContainer.getClass().getName();
    }

    @Override
    public Object get(String s, Scriptable scriptable) {
        if (KEYWORD_EXECUTION.equals(s) && variableContainer instanceof DelegateExecution) {
            return variableContainer;
        } else if (KEYWORD_TASK.equals(s) && variableContainer instanceof DelegateTask) {
            return variableContainer;
        } else if (variableContainer.hasVariable(s)) {
            return variableContainer.getVariable(s);
        } else if (beans != null && beans.containsKey(s)) {
            return beans.get(s);
        }
        return null;
    }

    @Override
    public Object get(int i, Scriptable scriptable) {
        return null;
    }

    @Override
    public boolean has(String s, Scriptable scriptable) {
        return variableContainer.hasVariable(s);
    }

    @Override
    public boolean has(int i, Scriptable scriptable) {
        return false;
    }

    @Override
    public void put(String s, Scriptable scriptable, Object o) {
    }

    @Override
    public void put(int i, Scriptable scriptable, Object o) {
    }

    @Override
    public void delete(String s) {
    }

    @Override
    public void delete(int i) {
    }

    @Override
    public Scriptable getPrototype() {
        return null;
    }

    @Override
    public void setPrototype(Scriptable scriptable) {
    }

    @Override
    public Scriptable getParentScope() {
        return null;
    }

    @Override
    public void setParentScope(Scriptable scriptable) {
    }

    @Override
    public Object[] getIds() {
        return null;
    }

    @Override
    public Object getDefaultValue(Class<?> aClass) {
        return null;
    }

    @Override
    public boolean hasInstance(Scriptable scriptable) {
        return false;
    }
}
