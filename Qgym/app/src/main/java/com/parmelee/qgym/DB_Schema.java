package com.parmelee.qgym;

import android.provider.BaseColumns;

/**
 * Created by mparm920 on 7/27/14.
 */
public class DB_Schema {

    public static abstract class Gymnast implements BaseColumns {
        public static final String TABLE_NAME = "tGymnast";
        public static final String FIRST_NAME = "First_Name";
        public static final String LAST_NAME = "Last_Name";
        public static final String EMAIL = "email";
        public static final String PHONE = "Phone_Num";
        public static final String DIVISION = "Division";
    }

    public static abstract class Meet implements BaseColumns {
        public static final String TABLE_NAME = "tMeet";
        public static final String MEET_NAME = "Meet_Name";
        public static final String DATE = "Date_of_Meet";
    }

    public static abstract class Scores implements BaseColumns {
        public static final String TABLE_NAME = "tScores";
        public static final String GYMNAST_ID = "Gymnast_Id";
        public static final String MEET_ID = "Meet_Id";
        public static final String CLASS = "Class";
        public static final String VAULT = "Vault";
        public static final String BARS = "Bars";
        public static final String BEAM = "Beam";
        public static final String FLOOR = "Floor";
        public static final String ALL_AROUND = "All_Around";
        public static final String NOTES = "Notes";
    }

    public static abstract class Configuration implements BaseColumns {
        public static final String TABLE_NAME = "tConfiguration";
        public static final String VAULT_QUAL = "Vault_QUAL";
        public static final String BARS_QUAL = "BARS_QUAL";
        public static final String BEAM_QUAL = "Beam_QUAL";
        public static final String FLOOR_QUAL = "Floor_QUAL";
        public static final String BACKGROUND_COLOR = "Background_Color";
        public static final String ACCENT_COLOR = "Accent_Color";
    }

}
