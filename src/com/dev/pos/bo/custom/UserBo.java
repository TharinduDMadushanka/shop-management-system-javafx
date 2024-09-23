package com.dev.pos.bo.custom;

import com.dev.pos.bo.SuperBO;
import com.dev.pos.dto.UserDTO;

public interface UserBo extends SuperBO {

    public boolean saveUser(UserDTO userDTO) throws Exception;
    public UserDTO findUser(String email) throws Exception;

}
