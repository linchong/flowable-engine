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
package org.flowable.eventregistry.impl.payload;

import java.util.Collection;
import java.util.stream.Collectors;

import org.flowable.eventregistry.api.InboundEventPayloadExtractor;
import org.flowable.eventregistry.api.model.EventPayloadTypes;
import org.flowable.eventregistry.api.runtime.EventPayloadInstance;
import org.flowable.eventregistry.impl.runtime.EventPayloadInstanceImpl;
import org.flowable.eventregistry.model.EventModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Joram Barrez
 */
public class XmlElementsToMapPayloadExtractor implements InboundEventPayloadExtractor<Document> {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlElementsToMapPayloadExtractor.class);

    @Override
    public Collection<EventPayloadInstance> extractPayload(EventModel eventModel, Document payload) {
        return eventModel.getPayload().stream()
            .filter(parameterDefinition -> parameterDefinition.isFullPayload() || getChildNode(payload, parameterDefinition.getName()) != null)
            .map(payloadDefinition -> new EventPayloadInstanceImpl(payloadDefinition, getPayloadValue(payload,
                    payloadDefinition.getName(), payloadDefinition.getType(), payloadDefinition.isFullPayload())))
            .collect(Collectors.toList());
    }

    protected Object getPayloadValue(Document document, String definitionName, String definitionType, boolean isFullPayload) {
        if (isFullPayload) {
            return document;
        }

        Node childNode = getChildNode(document, definitionName);
        if (childNode != null) {
            String textContent = childNode.getTextContent();

            if (EventPayloadTypes.STRING.equals(definitionType)) {
                return textContent;

            } else if (EventPayloadTypes.BOOLEAN.equals(definitionType)) {
                return Boolean.valueOf(textContent);

            } else if (EventPayloadTypes.INTEGER.equals(definitionType)) {
                return Integer.valueOf(textContent);

            } else if (EventPayloadTypes.DOUBLE.equals(definitionType)) {
                return Double.valueOf(textContent);

            } else if (EventPayloadTypes.LONG.equals(definitionType)) {
                return Long.valueOf(textContent);

            } else {
                LOGGER.warn("Unsupported payload type: {} ", definitionType);
                return textContent;
            }
        }

        return null;
    }

    protected Node getChildNode(Document document, String elementName) {
        NodeList childNodes = null;
        if (document.getChildNodes().getLength() == 1) {
            childNodes = document.getFirstChild().getChildNodes();
        } else {
            childNodes = document.getChildNodes();
        }

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (elementName.equals(node.getNodeName())) {
                return node;
            }
        }
        return null;
    }
}
