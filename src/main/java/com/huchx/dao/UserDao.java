package com.huchx.dao;


import com.huchx.annotation.CustomQueries;
import com.huchx.annotation.CustomQuery;
import com.huchx.entity.MUserEntity;
import com.huchx.repository.MyJpaRepository;
@CustomQueries(
        queries = {
                @CustomQuery(
                        name = "findProductByParams",
                        sql ="SELECT md.ID id,md.PID pid,md.DICT_TYPE dictType,md.DICT_NAME dictName,md.DICT_CODE dictCode "+
                                ",md.DICT_VALUE dictValue,md.SORT_BY sortBy,md.DESCRIBE d_describe,md.CREATE_TIME createdTime,md.FLG_DELETED flgDeleted"+
                                " FROM m_dictionary md"+
                                " WHERE md.PID !=0 and md.DICT_TYPE = '1'"+
                                " [AND md.DICT_NAME LIKE :dictName]"

                ),
                @CustomQuery(
                        name="isDictNameUnique",
                        sql="SELECT md.ID id,md.DICT_TYPE AS dictType"+
                                " FROM m_dictionary md"+
                                " WHERE 1 =1"+
                                " [AND md.ID != :id]"+
                                " [AND md.PID = :pId]"+
                                " [AND md.FLG_DELETED = :flgDeleted]"+
                                " [AND md.DICT_NAME = :dictName]"
                ),
                @CustomQuery(// 查询分类中的所有的分类。用以拼接json对象
                        name="selectFoodAllTypeNode",
                        sql="select id,pid,dict_code area_id,DICT_name area_Name from m_dictionary "+
                                " WHERE 1 =1 "+
                                "and  FLG_DELETED = 0 " +
                                "and DICT_TYPE = 'product_type'  and pid !=0 "
                ),
                @CustomQuery(
                        name="isDictCodeUnique",
                        sql="SELECT md.ID id,md.DICT_TYPE AS dictType"+
                                " FROM m_dictionary md"+
                                " WHERE 1 = 1"+
                                " [AND md.ID != :id]"+
                                " [AND md.DICT_CODE = :dictCode]"
                ),
        }
)
public interface UserDao extends MyJpaRepository<MUserEntity,Long> {

    MUserEntity findUserById(Long id);
}
