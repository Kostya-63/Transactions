package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl_2{

    @Autowired
    private UserDao userDao;

    @Transactional
    public User findUser(Long id) {
        User newUser = userDao.findUser(id);
        return userDao.findUserByCar(newUser.getCar().getSeries(), newUser.getCar().getModel());
    }

    @Transactional
    public User findUserByCar(int series, String model) {
        return userDao.findUserByCar(series, model);
    }
}