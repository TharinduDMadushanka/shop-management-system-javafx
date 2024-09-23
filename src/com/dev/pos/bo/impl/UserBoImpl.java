package com.dev.pos.bo.impl;

import com.dev.pos.bo.custom.UserBo;
import com.dev.pos.dao.DaoFactory;
import com.dev.pos.dao.custom.UserDAO;
import com.dev.pos.dto.UserDTO;
import com.dev.pos.entity.User;
import com.dev.pos.util.Enum.DaoType;

public class UserBoImpl implements UserBo {

    UserDAO userDAO = (UserDAO) DaoFactory.getInstance().getDao(DaoType.USER);

    @Override
    public boolean saveUser(UserDTO userDTO) throws Exception {
        return userDAO.save(new User(
                userDTO.getEmail(),
                userDTO.getPassword()
        ));
    }

    @Override
    public UserDTO findUser(String email) throws Exception {
        User user = userDAO.find(email);

        if (user != null) {
            return new UserDTO(
                    user.getEmail(),
                    user.getPassword()
            );
        }
        return null;
    }
}
