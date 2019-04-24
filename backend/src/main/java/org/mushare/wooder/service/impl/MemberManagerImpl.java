package org.mushare.wooder.service.impl;

import org.mushare.wooder.bean.MemberBean;
import org.mushare.wooder.domain.Group;
import org.mushare.wooder.domain.Member;
import org.mushare.wooder.service.MemberManager;
import org.mushare.wooder.service.common.BaseManager;
import org.mushare.wooder.service.common.Result;
import org.mushare.wooder.service.common.ResultCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberManagerImpl extends BaseManager implements MemberManager {

    @Override
    public Result add(String email, String name, String password, String groupId) {
        Optional<Group> group = groupDao.findById(groupId);
        if (!group.isPresent()) {
            return Result.error(ResultCode.GroupIdError);
        }
        Member member = memberDao.getByEmail(email);
        if (member != null) {
            return Result.error(ResultCode.MemberEmailRegistered);
        }
        member = new Member();
        member.setCreatedAt(System.currentTimeMillis());
        member.setUpdatedAt(member.getCreatedAt());
        member.setEmail(email);
        member.setName(name);
        member.setPassword(password);
        member.setGroup(group.get());
        memberDao.save(member);
        return Result.success();
    }

    @Override
    public Result<MemberBean> login(String email, String password) {
        return null;
    }

}