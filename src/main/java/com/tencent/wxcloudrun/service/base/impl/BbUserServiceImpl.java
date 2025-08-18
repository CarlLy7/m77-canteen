package com.tencent.wxcloudrun.service.base.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.model.BbUser;
import com.tencent.wxcloudrun.service.base.BbUserService;
import com.tencent.wxcloudrun.mapper.BbUserMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class BbUserServiceImpl extends ServiceImpl<BbUserMapper, BbUser>
    implements BbUserService{

}




