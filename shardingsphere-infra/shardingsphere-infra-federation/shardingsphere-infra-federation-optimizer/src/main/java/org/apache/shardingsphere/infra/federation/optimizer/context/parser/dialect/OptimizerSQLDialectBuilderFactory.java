/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.infra.federation.optimizer.context.parser.dialect;

import org.apache.shardingsphere.infra.database.type.DatabaseType;
import org.apache.shardingsphere.spi.ShardingSphereServiceLoader;
import org.apache.shardingsphere.spi.required.RequiredSPIRegistry;
import org.apache.shardingsphere.spi.typed.TypedSPIRegistry;

import java.util.Properties;

/**
 * Optimizer SQL dialect properties builder factory.
 */
public final class OptimizerSQLDialectBuilderFactory {
    
    static {
        ShardingSphereServiceLoader.register(OptimizerSQLDialectBuilder.class);
    }
    
    /**
     * Build optimizer SQL dialect properties.
     * 
     * @param databaseType database type
     * @param props properties to be built
     * @return built properties
     */
    public static Properties build(final DatabaseType databaseType, final Properties props) {
        OptimizerSQLDialectBuilder builder = null == databaseType
                ? RequiredSPIRegistry.getRegisteredService(OptimizerSQLDialectBuilder.class)
                : TypedSPIRegistry.getRegisteredService(OptimizerSQLDialectBuilder.class, databaseType.getName(), props);
        return builder.build();
    }
}
