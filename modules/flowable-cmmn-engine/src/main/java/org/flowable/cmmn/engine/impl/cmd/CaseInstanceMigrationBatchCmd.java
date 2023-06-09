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

package org.flowable.cmmn.engine.impl.cmd;

import org.flowable.batch.api.Batch;
import org.flowable.cmmn.api.migration.CaseInstanceMigrationDocument;
import org.flowable.cmmn.engine.CmmnEngineConfiguration;
import org.flowable.cmmn.engine.impl.migration.CaseInstanceMigrationManager;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.impl.interceptor.Command;
import org.flowable.common.engine.impl.interceptor.CommandContext;

public class CaseInstanceMigrationBatchCmd implements Command<Batch> {

    protected CmmnEngineConfiguration cmmnEngineConfiguration;
    
    protected String caseDefinitionId;
    protected String caseDefinitionKey;
    protected int caseDefinitionVersion;
    protected String caseDefinitionTenantId;
    protected CaseInstanceMigrationDocument caseInstanceMigrationDocument;

    public CaseInstanceMigrationBatchCmd(CaseInstanceMigrationDocument caseInstanceMigrationDocument, String caseDefinitionId,
            CmmnEngineConfiguration cmmnEngineConfiguration) {
        
        if (caseDefinitionId == null) {
            throw new FlowableException("Must specify a case definition id to migrate");
        }
        if (caseInstanceMigrationDocument == null) {
            throw new FlowableException("Must specify a case instance migration document to migrate");
        }
        this.caseDefinitionId = caseDefinitionId;
        this.caseInstanceMigrationDocument = caseInstanceMigrationDocument;
        this.cmmnEngineConfiguration = cmmnEngineConfiguration;
    }

    public CaseInstanceMigrationBatchCmd(String caseDefinitionKey, int caseDefinitionVersion, String caseDefinitionTenantId, 
            CaseInstanceMigrationDocument caseInstanceMigrationDocument, CmmnEngineConfiguration cmmnEngineConfiguration) {
        
        if (caseDefinitionKey == null) {
            throw new FlowableException("Must specify a case definition id to migrate");
        }
        if (caseDefinitionTenantId == null) {
            throw new FlowableException("Must specify a case definition tenant id to migrate");
        }
        if (caseInstanceMigrationDocument == null) {
            throw new FlowableException("Must specify a case instance migration document to migrate");
        }
        this.caseDefinitionKey = caseDefinitionKey;
        this.caseDefinitionVersion = caseDefinitionVersion;
        this.caseDefinitionTenantId = caseDefinitionTenantId;
        this.caseInstanceMigrationDocument = caseInstanceMigrationDocument;
        this.cmmnEngineConfiguration = cmmnEngineConfiguration;
    }

    @Override
    public Batch execute(CommandContext commandContext) {
        CaseInstanceMigrationManager migrationManager = cmmnEngineConfiguration.getCaseInstanceMigrationManager();

        if (caseDefinitionId != null) {
            return migrationManager.batchMigrateCaseInstancesOfCaseDefinition(caseDefinitionId, caseInstanceMigrationDocument, commandContext);
        } else if (caseDefinitionKey != null && caseDefinitionVersion >= 0) {
            return migrationManager.batchMigrateCaseInstancesOfCaseDefinition(caseDefinitionKey, caseDefinitionVersion, caseDefinitionTenantId, caseInstanceMigrationDocument, commandContext);
        } else {
            throw new FlowableException("Cannot migrate case(es), not enough information");
        }
    }
}
