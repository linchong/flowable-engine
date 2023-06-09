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
package org.flowable.common.engine.impl.util;

/**
 * @author Filip Hrisafov
 */
public class ExceptionUtil {

    public static <E extends Throwable> void sneakyThrow(Throwable t) throws E {
        throw (E) t;
    }

    public static boolean containsCause(Throwable chain, Class<? extends Throwable> exceptionType) {
        Throwable exception = chain;
        while (exception != null) {
            if (exceptionType.isInstance(exception)) {
                return true;
            }
            exception = exception.getCause();
        }
        return false;
    }

}
