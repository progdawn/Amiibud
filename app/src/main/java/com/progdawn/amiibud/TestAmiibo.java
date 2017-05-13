package com.progdawn.amiibud;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawn Myers on 5/13/2017.
 */

public class TestAmiibo {

    private String mName;
    private String mSeries;

    public TestAmiibo(String name, String series) {
        mName = name;
        mSeries = series;
    }

    public String getName() {
        return mName;
    }

    public String getSeries() {
        return mSeries;
    }

    private static int lastContactId = 0;

    public static List<TestAmiibo> createAmiibosList(int numAmiibos) {
        List<TestAmiibo> amiibos = new ArrayList<TestAmiibo>();

        for (int i = 1; i <= numAmiibos; i++) {
            amiibos.add(new TestAmiibo("Amiibo " + ++lastContactId, "asdf"));
        }

        return amiibos;
    }
}
