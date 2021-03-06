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

package org.apache.shardingsphere.transaction.xa.bitronix.manager;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import bitronix.tm.recovery.RecoveryException;
import bitronix.tm.resource.ResourceRegistrar;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.shardingsphere.transaction.xa.spi.SingleXAResource;
import org.apache.shardingsphere.transaction.xa.spi.XATransactionManagerProvider;

import javax.sql.XADataSource;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 * Bitronix transaction manager provider.
 */
@Getter
public final class BitronixXATransactionManagerProvider implements XATransactionManagerProvider {
    
    private BitronixTransactionManager transactionManager;
    
    @Override
    public void init() {
        transactionManager = TransactionManagerServices.getTransactionManager();
    }
    
    @SneakyThrows(RecoveryException.class)
    @Override
    public void registerRecoveryResource(final String dataSourceName, final XADataSource xaDataSource) {
        ResourceRegistrar.register(new BitronixRecoveryResource(dataSourceName, xaDataSource));
    }
    
    @Override
    public void removeRecoveryResource(final String dataSourceName, final XADataSource xaDataSource) {
        ResourceRegistrar.unregister(new BitronixRecoveryResource(dataSourceName, xaDataSource));
    }
    
    @SneakyThrows({SystemException.class, RollbackException.class})
    @Override
    public void enlistResource(final SingleXAResource singleXAResource) {
        transactionManager.getTransaction().enlistResource(singleXAResource);
    }
    
    @Override
    public void close() {
        transactionManager.shutdown();
    }
    
    @Override
    public String getType() {
        return "Bitronix";
    }
}
