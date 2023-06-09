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
package org.flowable.eventregistry.spring.jms;

import java.util.Map;

import jakarta.jms.TextMessage;

import org.flowable.eventregistry.api.OutboundEventChannelAdapter;
import org.springframework.jms.core.JmsOperations;

/**
 * @author Filip Hrisafov
 */
public class JmsOperationsOutboundEventChannelAdapter implements OutboundEventChannelAdapter<String> {

    protected JmsOperations jmsOperations;
    protected String destination;
    protected JmsMessageCreator<String> messageCreator;

    public JmsOperationsOutboundEventChannelAdapter(JmsOperations jmsOperations, String destination) {
        this(jmsOperations, destination, (event, headerMap, session) -> {
            TextMessage textMessage = session.createTextMessage(event);
            for (String headerKey : headerMap.keySet()) {
                textMessage.setObjectProperty(headerKey, headerMap.get(headerKey));
            }
            return textMessage;
        });
    }

    public JmsOperationsOutboundEventChannelAdapter(JmsOperations jmsOperations, String destination, JmsMessageCreator<String> messageCreator) {
        this.jmsOperations = jmsOperations;
        this.destination = destination;
        this.messageCreator = messageCreator;
    }

    @Override
    public void sendEvent(String rawEvent, Map<String, Object> headerMap) {
        jmsOperations.send(destination, session -> getMessageCreator().toMessage(rawEvent, headerMap, session));
    }

    public JmsOperations getJmsOperations() {
        return jmsOperations;
    }

    public void setJmsOperations(JmsOperations jmsOperations) {
        this.jmsOperations = jmsOperations;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public JmsMessageCreator<String> getMessageCreator() {
        return messageCreator;
    }

    public void setMessageCreator(JmsMessageCreator<String> messageCreator) {
        this.messageCreator = messageCreator;
    }
}
