/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.servicecomb.foundation.common.config;

import java.io.IOException;
import java.util.Properties;

public class PaaSPropertyPlaceholderConfigurer
        extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer {

    // 以逗号分隔
    private String configId;

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    @Override
    protected void loadProperties(Properties props) throws IOException {
        for (String id : configId.split(",")) {
            try {
                Properties config = ConfigMgr.INSTANCE.getConfig(id);
                props.putAll(config);
            } catch (Exception e) {
                throw new IOException(e);
            }
        }
    }
}
