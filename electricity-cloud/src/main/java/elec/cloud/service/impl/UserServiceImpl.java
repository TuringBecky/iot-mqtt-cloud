package elec.cloud.service.impl;

import elec.cloud.mapper.UserMapper;
import elec.cloud.entity.User;
import elec.cloud.redis.RedisUtils;
import elec.cloud.result.Result;
import elec.cloud.result.SignInAck;
import elec.cloud.service.UserService;
import elec.cloud.utils.UserUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<SignInAck> signIn(String username, String password) {
        User user = new User();
        user.setUsername(username);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        user = this.userMapper.selectOne(queryWrapper);
        if (user == null) {
            return new Result(HttpStatus.BAD_REQUEST.value(), "用户名不存在", null);
        }
        if (!password.equals(user.getPassword())) {
            return new Result(HttpStatus.BAD_REQUEST.value(), "密码错误", null);
        }
        SignInAck signInAck = UserUtils.getAck(user);
        this.redisUtils.set(signInAck.getCacheKey(), signInAck.getSecretKey(), 1800);
        return new Result(HttpStatus.OK.value(), "登录成功", signInAck);
    }

    @Override
    public Result<SignInAck> signUp(User user) {
        this.userMapper.insert(user);
        return null;
    }
}