package br.com.ey.msheroi.config.datasource.connection;

public class SqlserverDefinitions {

    private SqlserverDefinitions(){/* default */}

    public static final String CONTEXT_BASE = "sqlserver";

    public static final String BEAN_NAME_DATASOURCE = "datasourceSqlserver";
    public static final String BEAN_NAME_TRANSACTION_MANAGER = "transactionManagerSqlserver";
    public static final String BEAN_NAME_ENTITY_MANAGER_FACTORY = "entityManagerFactorySqlserver";

    public static final String REPO_PACKAGES = "br.com.ey.msheroi.repository";
    public static final String ENTITY_PACKAGES = "br.com.ey.msheroi.common.vo";

}
