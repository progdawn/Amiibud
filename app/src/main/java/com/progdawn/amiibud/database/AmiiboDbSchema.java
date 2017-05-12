package com.progdawn.amiibud.database;

/**
 * Created by Dawn Myers on 5/11/2017.
 */

public class AmiiboDbSchema {

    public static final class AmiiboTable{
        public static final String NAME = "amiibos";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String SERIES = "series";
            public static final String DATE = "date";
        }
    }
}
