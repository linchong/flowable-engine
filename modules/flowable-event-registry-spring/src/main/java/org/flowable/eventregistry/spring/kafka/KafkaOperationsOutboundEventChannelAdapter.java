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
package org.flowable.eventregistry.spring.kafka;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.eventregistry.api.OutboundEvent;
import org.flowable.eventregistry.api.OutboundEventChannelAdapter;
import org.springframework.kafka.core.KafkaOperations;

/**
 * @author Filip Hrisafov
 */
public class KafkaOperationsOutboundEventChannelAdapter implements OutboundEventChannelAdapter<String> {

    protected KafkaOperations<Object, Object> kafkaOperations;
    protected KafkaPartitionProvider partitionProvider;
    protected String topic;
    protected String key;

    public KafkaOperationsOutboundEventChannelAdapter(KafkaOperations<Object, Object> kafkaOperations, KafkaPartitionProvider partitionProvider, String topic, String key) {
        this.kafkaOperations = kafkaOperations;
        this.partitionProvider = partitionProvider;
        this.topic = topic;
        this.key = key;
    }

    @Override
    public void sendEvent(OutboundEvent<String> event) {
        try {
            String rawEvent = event.getBody();
            Map<String, Object> headerMap = event.getHeaders();
            List<Header> headers = new ArrayList<>();
            for (String headerKey : headerMap.keySet()) {
                Object headerValue = headerMap.get(headerKey);
                if (headerValue != null) {
                    headers.add(new RecordHeader(headerKey, headerValue.toString().getBytes(StandardCharsets.UTF_8)));
                }
            }

            Integer partition = partitionProvider == null ? null : partitionProvider.determinePartition(event);

            ProducerRecord<Object, Object> producerRecord = new ProducerRecord<>(topic, partition, key, rawEvent, headers);
            kafkaOperations.send(producerRecord).get();
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FlowableException("Sending the event was interrupted", e);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof RuntimeException) {
                throw (RuntimeException) e.getCause();
            } else {
                throw new FlowableException("failed to send event", e.getCause());
            }
        }
    }

    @Override
    public void sendEvent(String rawEvent, Map<String, Object> headerMap) {
        throw new UnsupportedOperationException("Outbound processor should never call this");
    }
}
