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
package org.flowable.http.common.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.flowable.common.engine.api.FlowableIllegalStateException;

/**
 * @author Harsha Teja Kanna.
 */
public class HttpRequest {

    protected String method;
    protected String url;
    protected HttpHeaders httpHeaders;
    protected String body;
    protected String bodyEncoding;
    protected Collection<MultiValuePart> multiValueParts;
    protected int timeout;
    protected boolean noRedirects;
    protected Set<String> failCodes;
    protected Set<String> handleCodes;
    protected boolean ignoreErrors;
    protected boolean saveRequest;
    protected boolean saveResponse;
    protected boolean saveResponseTransient;
    protected boolean saveResponseAsJson;
    protected String prefix;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public String getHttpHeadersAsString() {
        return httpHeaders != null ? httpHeaders.formatAsString() : null;
    }


    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        if (multiValueParts != null && !multiValueParts.isEmpty()) {
            throw new FlowableIllegalStateException("Cannot set both body and multi value parts");
        }
        this.body = body;
    }

    public String getBodyEncoding() {
        return bodyEncoding;
    }

    public void setBodyEncoding(String bodyEncoding) {
        this.bodyEncoding = bodyEncoding;
    }

    public Collection<MultiValuePart> getMultiValueParts() {
        return multiValueParts;
    }

    public void addMultiValuePart(MultiValuePart part) {
        if (body != null) {
            throw new FlowableIllegalStateException("Cannot set both body and multi value parts");
        }
        if (multiValueParts == null) {
            multiValueParts = new ArrayList<>();
        }
        multiValueParts.add(part);
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isNoRedirects() {
        return noRedirects;
    }

    public void setNoRedirects(boolean noRedirects) {
        this.noRedirects = noRedirects;
    }

    public Set<String> getFailCodes() {
        return failCodes;
    }

    public void setFailCodes(Set<String> failCodes) {
        this.failCodes = failCodes;
    }

    public Set<String> getHandleCodes() {
        return handleCodes;
    }

    public void setHandleCodes(Set<String> handleCodes) {
        this.handleCodes = handleCodes;
    }

    public boolean isIgnoreErrors() {
        return ignoreErrors;
    }

    public void setIgnoreErrors(boolean ignoreErrors) {
        this.ignoreErrors = ignoreErrors;
    }

    public boolean isSaveRequest() {
        return saveRequest;
    }

    public void setSaveRequest(boolean saveRequest) {
        this.saveRequest = saveRequest;
    }

    public boolean isSaveResponse() {
        return saveResponse;
    }

    public void setSaveResponse(boolean saveResponse) {
        this.saveResponse = saveResponse;
    }
    
    public boolean isSaveResponseTransient() {
        return saveResponseTransient;
    }

    public void setSaveResponseTransient(boolean saveResponseTransient) {
        this.saveResponseTransient = saveResponseTransient;
    }
    
    public boolean isSaveResponseAsJson() {
        return saveResponseAsJson;
    }

    public void setSaveResponseAsJson(boolean saveResponseAsJson) {
        this.saveResponseAsJson = saveResponseAsJson;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
