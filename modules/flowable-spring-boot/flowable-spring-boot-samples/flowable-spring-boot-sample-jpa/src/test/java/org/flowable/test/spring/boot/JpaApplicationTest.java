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
package org.flowable.test.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManagerFactory;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import flowable.Application;

/**
 * @author Filip Hrisafov
 */
@SpringBootTest(classes = Application.class)
public class JpaApplicationTest {

    @Autowired
    private SpringProcessEngineConfiguration processEngineConfiguration;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void usesJpaEntityManager() {
        assertThat(processEngineConfiguration.getJpaEntityManagerFactory())
            .as("Process engine configuration jpa entity manager factory")
            .isEqualTo(entityManagerFactory)
            .isInstanceOf(EntityManagerFactory.class);
    }
}
