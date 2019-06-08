package com.lgx.springmvc.activity.mapper;

import com.lgx.springmvc.activity.daoentity.ActIdUserEntity;
import org.mapstruct.Mapper;

/**
 * Created by Administrator on 2018/9/5.
 */
@Mapper
public interface ActIdUserMapper {

    ActIdUserEntity findUserById(String username, String password);
}
