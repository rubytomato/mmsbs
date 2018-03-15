package com.example.domain.datasource;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.IntegerType;

public class CustomMySQLDialect extends MySQL5Dialect {
    public CustomMySQLDialect() {
        super();
        registerFunction("calculate",
                new StandardSQLFunction("calculate", new IntegerType()));
    }
}
