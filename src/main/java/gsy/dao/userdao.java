package gsy.dao;

import gsy.domain.user;

import java.util.List;

public interface userdao {
    /**
     * 查询所有操作
     * @return
     */
    List<user> findAll();

    List<user> addNew();


}
