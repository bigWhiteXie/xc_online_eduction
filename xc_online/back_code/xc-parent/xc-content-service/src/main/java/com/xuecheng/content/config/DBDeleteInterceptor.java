package com.xuecheng.content.config;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 拦截修改语句，增加更新时间
 *
 * @author l.y
 */
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
)})
public class DBDeleteInterceptor extends AbstractSqlParserHandler implements Interceptor {
    /**
     * 更新时间
     */
    private static final String UPDATE_TIME = "change_date";
    /**
     * 最后修改人
     */
    private static final String LATEST_OPERATOR = "change_people";
 
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        this.sqlParser(metaObject);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (SqlCommandType.UPDATE == mappedStatement.getSqlCommandType() && StatementType.CALLABLE != mappedStatement.getStatementType()) {
 
            BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
            //获取已经构造好的SQL
            String sql = boundSql.getSql();
            //获取映射的参数
            List<ParameterMapping> mappings = new ArrayList(boundSql.getParameterMappings());
            //假如参数中不包含要构造的参数，手动写入
            if (sql.indexOf(UPDATE_TIME) == -1) {
                //写入更新时间
                sql = sql.replace("SET", "SET " + UPDATE_TIME + " = '" + LocalDateTime.now() + "',");
                metaObject.setValue("delegate.boundSql.sql", sql);
                metaObject.setValue("delegate.boundSql.parameterMappings", mappings);
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return target instanceof StatementHandler ? Plugin.wrap(target, this) : target;
    }
}