package com.dev.pos.dao;

import com.dev.pos.bo.SuperBO;
import com.dev.pos.dao.impl.UserDAOImpl;
import com.dev.pos.util.Enum.DaoType;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDao(DaoType daoType) {
        switch (daoType) {
            case USER:
                return new UserDAOImpl();

            default:
                return null;
        }
    }

}
