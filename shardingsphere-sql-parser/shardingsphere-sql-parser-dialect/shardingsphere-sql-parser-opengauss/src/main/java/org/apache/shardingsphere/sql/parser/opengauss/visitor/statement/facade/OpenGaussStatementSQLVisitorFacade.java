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

package org.apache.shardingsphere.sql.parser.opengauss.visitor.statement.facade;

import org.apache.shardingsphere.sql.parser.api.visitor.type.DALSQLVisitor;
import org.apache.shardingsphere.sql.parser.api.visitor.type.DCLSQLVisitor;
import org.apache.shardingsphere.sql.parser.api.visitor.type.DDLSQLVisitor;
import org.apache.shardingsphere.sql.parser.api.visitor.type.DMLSQLVisitor;
import org.apache.shardingsphere.sql.parser.api.visitor.type.RLSQLVisitor;
import org.apache.shardingsphere.sql.parser.api.visitor.type.TCLSQLVisitor;
import org.apache.shardingsphere.sql.parser.opengauss.visitor.statement.impl.OpenGaussDALStatementSQLVisitor;
import org.apache.shardingsphere.sql.parser.opengauss.visitor.statement.impl.OpenGaussDCLStatementSQLVisitor;
import org.apache.shardingsphere.sql.parser.opengauss.visitor.statement.impl.OpenGaussDDLStatementSQLVisitor;
import org.apache.shardingsphere.sql.parser.opengauss.visitor.statement.impl.OpenGaussDMLStatementSQLVisitor;
import org.apache.shardingsphere.sql.parser.opengauss.visitor.statement.impl.OpenGaussTCLStatementSQLVisitor;
import org.apache.shardingsphere.sql.parser.spi.SQLVisitorFacade;

/**
 * Statement SQL Visitor facade for openGauss.
 */
public final class OpenGaussStatementSQLVisitorFacade implements SQLVisitorFacade {
    
    @Override
    public Class<? extends DMLSQLVisitor> getDMLVisitorClass() {
        return OpenGaussDMLStatementSQLVisitor.class;
    }
    
    @Override
    public Class<? extends DDLSQLVisitor> getDDLVisitorClass() {
        return OpenGaussDDLStatementSQLVisitor.class;
    }
    
    @Override
    public Class<? extends TCLSQLVisitor> getTCLVisitorClass() {
        return OpenGaussTCLStatementSQLVisitor.class;
    }
    
    @Override
    public Class<? extends DCLSQLVisitor> getDCLVisitorClass() {
        return OpenGaussDCLStatementSQLVisitor.class;
    }
    
    @Override
    public Class<? extends DALSQLVisitor> getDALVisitorClass() {
        return OpenGaussDALStatementSQLVisitor.class;
    }
    
    @Override
    public Class<? extends RLSQLVisitor> getRLVisitorClass() {
        return null;
    }
    
    @Override
    public String getDatabaseType() {
        return "openGauss";
    }
    
    @Override
    public String getVisitorType() {
        return "STATEMENT";
    }
}
