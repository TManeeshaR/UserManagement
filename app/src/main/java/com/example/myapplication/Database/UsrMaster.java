package com.example.myapplication.Database;

import android.provider.BaseColumns;

public final class UsrMaster {
    private UsrMaster(){}

    protected static class Usr implements BaseColumns{

        public static final String TABLE_NAME="users";
        public static final String COLUMN_NAME_FIRSTNAME="fname";
        public static final String COLUMN_NAME_LASTNAME="lastname";
        public static final String COLUMN_NAME_USERNAME="username";
        public static final String COLUMN_NAME_PASSWORD="password";
        public static final String COLUMN_NAME_CONFIRMPASSWORD="confirmPassword";
        public static final String COLUMN_NAME_ADDRESS="address";
        public static final String COLUMN_NAME_CONTACTNUMBER="contactNumber";
    }
}
