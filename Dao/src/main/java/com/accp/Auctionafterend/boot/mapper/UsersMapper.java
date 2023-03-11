package com.accp.Auctionafterend.boot.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.accp.Auctionafterend.boot.pojo.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMapper extends BaseMapper<Users> {

    /**
     * 按用户账户查询用户信息
     * @param usAccount
     * @return
     */
    List<Users> selectByUsAccount(@Param("usAccount") String usAccount);
}
