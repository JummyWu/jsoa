package com.github.jummy.database.parsers;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.SqlInfo;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.TableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.github.jummy.context.BaseContextHandler;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Collection;
import java.util.Map;

/**
 * @author: jummy
 * @create_date: 14/09/2021 10:43 上午
 * @desc:
 */
@Data
@Accessors(chain = true)
public class DynamicTableNameParser implements ISqlParser {
    private static final String ADMIN = "admin";

    private Map<String, ITableNameHandler> tableNameHandlerMap;

    private ITableNameHandler defaultTableNameHandler = (metaObject, sql, tableName) -> {
        String tenantCode = BaseContextHandler.getTenant();
        if (StrUtil.isEmpty(tenantCode) || ADMIN.equals(tenantCode)) {
            return tableName;
        }
        return BaseContextHandler.getDatabase(tenantCode) + StrUtil.DOT + tableName;
    };

    @Override
    public SqlInfo parser(MetaObject metaObject, String sql) {
        if (allowProcess(metaObject)) {
            Collection<String> tables = new TableNameParser(sql).tables();
            if (CollectionUtils.isNotEmpty(tables)) {
                boolean sqlParsed = false;
                String parsedSql = sql;
                for (final String table : tables) {
                    ITableNameHandler tableNameHandler = defaultTableNameHandler;
                    if (CollectionUtils.isNotEmpty(tableNameHandlerMap)) {
                        tableNameHandler = tableNameHandlerMap.get(table);
                    }
                    if (tableNameHandler != null) {
                        parsedSql = tableNameHandler.process(metaObject, parsedSql, table);
                        sqlParsed = true;
                    }
                    if (sqlParsed) {
                        return SqlInfo.newInstance().setSql(parsedSql);
                    }
                }
            }
        }
        return null;
    }

    /**
     * 判断是否允许执行
     * <p>例如：逻辑删除只解析 delete , update 操作</p>
     *
     * @param metaObject 元对象
     * @return true
     */
    public boolean allowProcess(MetaObject metaObject) {
        return true;
    }
}
