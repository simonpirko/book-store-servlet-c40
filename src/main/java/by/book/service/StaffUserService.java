package by.book.service;

import by.book.dao.UserDao;
import by.book.dao.inmemory.InMemoryUserDao;
import by.book.entity.Role;
import by.book.entity.User;
import by.book.exception.DaoException;
import by.book.exception.InvalidRequestException;
import by.book.exception.NotFoundException;
import by.book.exception.ServerErrorException;

import java.util.ArrayList;
import java.util.List;

public class StaffUserService {
    private UserDao userDao = new InMemoryUserDao();

    public List<User> getAll(String role) {

        // проверяем что роль передана
        Role getRole = null;
        if(role != null && !role.trim().equals("Выберите роль")) {
            getRole = Role.valueOf(role);
        }
        if(getRole != null) {
            List<User> resUserList = new ArrayList<>();
            for(User user : userDao.getAll()) {
                if(user.getRole() == getRole) {
                    resUserList.add(user);
                }
            }
            return resUserList;
        } else {
            // если не передана отдаем весь список
            return userDao.getAll();
        }
    }

    public User getOne(String id) throws NotFoundException, InvalidRequestException {
        int idInt = validaAndTransformStringToInt(id);

        try {
            User user = userDao.getById(idInt);
            return user;
        } catch (DaoException e) {
            throw new NotFoundException();
        }
    }

    public void update(String id, String role) throws InvalidRequestException, ServerErrorException, NotFoundException {
        // Валидируем
        int idInt =  validaAndTransformStringToInt(id);
        validationParam(role);
        Role newRole = Role.valueOf(role);

        if (newRole == null) throw new InvalidRequestException("Invalid role");

        User findUser;
        try {
            findUser = userDao.getById(idInt);
        } catch (DaoException e) {
            throw new NotFoundException();
        }

        if(!newRole.equals(findUser.getRole())) {
            userDao.updateRole(findUser.getId(), newRole);
        }
    }

    private int validaAndTransformStringToInt(String param) throws InvalidRequestException {
        if(param == null || param.trim() == "") throw new InvalidRequestException();

        int paramInt;
        try {
            paramInt = Integer.parseInt(param);
        } catch (NumberFormatException e) {
            throw new InvalidRequestException();
        }
        return paramInt;
    }

    private void validationParam(String param) throws InvalidRequestException {
        if(param == null || param.trim() == "") throw new InvalidRequestException();
    }
}
