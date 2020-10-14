package com.company;

import com.company.Menu.MenuUtil;
import com.company.datasource.MySQLDataSource;

public class Main {

    public static void main(String[] args) {
        // connect if connection is null => connect()
        if (MySQLDataSource.connection == null) {
            MySQLDataSource.connect();
        }

        MenuUtil.showMenu();
    }
}
