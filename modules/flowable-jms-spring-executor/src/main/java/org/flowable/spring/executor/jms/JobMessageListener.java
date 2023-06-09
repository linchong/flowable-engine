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
package org.flowable.spring.executor.jms;

import jakarta.jms.Message;
import jakarta.jms.TextMessage;

import org.flowable.job.service.JobServiceConfiguration;
import org.flowable.job.service.impl.asyncexecutor.ExecuteAsyncRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Joram Barrez
 */
public class JobMessageListener implements jakarta.jms.MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobMessageListener.class);

    protected JobServiceConfiguration jobServiceConfiguration;

    @Override
    public void onMessage(final Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String jobId = textMessage.getText();

                ExecuteAsyncRunnable executeAsyncRunnable = new ExecuteAsyncRunnable(jobId, jobServiceConfiguration, jobServiceConfiguration.getJobEntityManager(), null);
                executeAsyncRunnable.run();

            }
        } catch (Exception e) {
            LOGGER.error("Exception when handling message from job queue", e);
        }
    }

    public JobServiceConfiguration getJobServiceConfigurationn() {
        return jobServiceConfiguration;
    }

    public void setJobServiceConfiguration(JobServiceConfiguration jobServiceConfiguration) {
        this.jobServiceConfiguration = jobServiceConfiguration;
    }

}
