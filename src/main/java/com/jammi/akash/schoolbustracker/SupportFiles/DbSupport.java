package com.jammi.akash.schoolbustracker.SupportFiles;

import com.jammi.akash.schoolbustracker.CustomClass.App;
import com.jammi.akash.schoolbustracker.SupportFiles.CommonMethods;
import com.jammi.akash.schoolbustracker.SupportFiles.DbSQLiteHelper;

class DbSupport {

    final public static int STATUS_SUCCESS = 1, STATUS_FAILURE = 0, STATUS_INCOMLETE = -1;


    public DbSQLiteHelper getDbModel() {
        return CommonMethods.getDbModel(App.getAppContext());
    }

}
