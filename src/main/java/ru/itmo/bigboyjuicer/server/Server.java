package ru.itmo.bigboyjuicer.server;

import ru.itmo.bigboyjuicer.server.manager.DatabaseManager;

import java.sql.SQLException;

public class Server {

    public static void main(String[] args) {
        try {
            DatabaseManager.settingUpDatabase();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
