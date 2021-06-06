package com.yootk.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yootk.vo.Dept;
import com.yootk.vo.Emp;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FlagMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("【FlagMetaObjectHandler.insertFill()】目标类型：{}", metaObject.getOriginalObject().getClass().getName());
        if (Dept.class.equals(metaObject.getOriginalObject().getClass())) { // 当前操作的是部门类
            this.setFieldValByName("flag", "【INSERT-FILL】muyan", metaObject);
        } else if (Emp.class.equals(metaObject.getOriginalObject().getClass())) {   // 当前操作的是雇员类型
            this.setFieldValByName("flag", "【INSERT-FILL】yootk", metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("【FlagMetaObjectHandler.updateFill()】目标类型：{}", metaObject.getOriginalObject().getClass().getName());
        if (Dept.class.equals(metaObject.getOriginalObject().getClass())) { // 当前操作的是部门类
            this.setFieldValByName("flag", "【UPDATE-FILL】muyan", metaObject);
        } else if (Emp.class.equals(metaObject.getOriginalObject().getClass())) {   // 当前操作的是雇员类型
            this.setFieldValByName("flag", "【UPDATE-FILL】yootk", metaObject);
        }
    }
}
