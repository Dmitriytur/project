package ua.nure.tur.db.dao.mysql;

import ua.nure.tur.db.dao.SubscriptionDAO;

import java.sql.Connection;

public class MysqlSubscriptionDAO implements SubscriptionDAO {

    private Connection connection;

    public MysqlSubscriptionDAO(Connection connection) {
        this.connection = connection;
    }


}
