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
package org.flowable.test.cmmn.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.flowable.cmmn.model.CmmnModel;
import org.flowable.test.cmmn.converter.util.CmmnXmlConverterTest;

/**
 * @author Joram Barrez
 */
public class DefinitionExtensionAttributesXmlConverterTest {

    @CmmnXmlConverterTest("org/flowable/test/cmmn/converter/case-custom-extension-attribute.cmmn")
    public void convertDefinitionExtensionAttributes(CmmnModel cmmnModel) {

        assertThat(cmmnModel.getDefinitionsAttributeValue("http://flowable.org/cmmn", "customAttribute")).isEqualTo("Hello World");
        assertThat(cmmnModel.getDefinitionsAttributeValue("http://flowable.org/test", "customAttribute")).isEqualTo("Hi");
        assertThat(cmmnModel.getDefinitionsAttributeValue("http://flowable.org/test", "customAttributeTwo")).isEqualTo("there");

    }

}
