package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Autowired UserServiceImpl_2 userServiceImpl_2;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   //@Transactional(propagation = Propagation.REQUIRES_NEW)
   @Override
   public User findUser(Long id) {
      User newUser = userServiceImpl_2.findUser(id);
      return userServiceImpl_2.findUserByCar(newUser.getCar().getSeries(), newUser.getCar().getModel());
   }

   @Transactional
   @Override
   public User findUserByCar(int series, String model) {
      return userDao.findUserByCar(series, model);
   }
}
