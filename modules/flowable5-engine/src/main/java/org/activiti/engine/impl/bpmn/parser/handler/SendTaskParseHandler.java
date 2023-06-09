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
package org.activiti.engine.impl.bpmn.parser.handler;

import org.activiti.engine.impl.bpmn.behavior.WebServiceActivityBehavior;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.constants.BpmnXMLConstants;
import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.ImplementationType;
import org.flowable.bpmn.model.SendTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Joram Barrez
 */
public class SendTaskParseHandler extends AbstractExternalInvocationBpmnParseHandler<SendTask> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendTaskParseHandler.class);

    @Override
    public Class<? extends BaseElement> getHandledType() {
        return SendTask.class;
    }

    @Override
    protected void executeParse(BpmnParse bpmnParse, SendTask sendTask) {

        ActivityImpl activity = createActivityOnCurrentScope(bpmnParse, sendTask, BpmnXMLConstants.ELEMENT_TASK_SEND);

        activity.setAsync(sendTask.isAsynchronous());
        activity.setExclusive(!sendTask.isNotExclusive());

        if (StringUtils.isNotEmpty(sendTask.getType())) {
            if ("mail".equalsIgnoreCase(sendTask.getType())) {
                activity.setActivityBehavior(bpmnParse.getActivityBehaviorFactory().createMailActivityBehavior(sendTask));
            } else if ("camel".equalsIgnoreCase(sendTask.getType())) {
                activity.setActivityBehavior(bpmnParse.getActivityBehaviorFactory().createCamelActivityBehavior(sendTask, bpmnParse.getBpmnModel()));
            }

            // for web service
        } else if (ImplementationType.IMPLEMENTATION_TYPE_WEBSERVICE.equalsIgnoreCase(sendTask.getImplementationType()) &&
                StringUtils.isNotEmpty(sendTask.getOperationRef())) {

            WebServiceActivityBehavior webServiceActivityBehavior = bpmnParse.getActivityBehaviorFactory().createWebServiceActivityBehavior(sendTask, bpmnParse.getBpmnModel());
            activity.setActivityBehavior(webServiceActivityBehavior);

        } else {
            LOGGER.warn("One of the attributes 'type' or 'operation' is mandatory on sendTask {}", sendTask.getId());
        }
    }

}
