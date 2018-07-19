package com.jufan.util;

import java.util.ArrayList;
import java.util.List;

public class DatasourceConfig {
    public static List<String> listTest = new ArrayList<String>();
    public static List<String> listPro = new ArrayList<String>();
    //测试
    public static String tongdunTestId="e71f64b4a9324b98b5355c0a3b64f397";
    public static String xhtdTestId="f783d8f7a5014e6788194ac169e7f892";
    public static String moxieTestId="d06843e8a19140989ea66af1e808b910";
    //生产
    public static String tongdunProId="e71f64b4a9324b98b5355c0a3b64f397";
    public static String xhtdProId="0065f83d354c4f97a3b0541df8ed8a23";
    public static String moxieProId="2c863832b20242b8974aaba1781123b5";

    static {

        listTest.add(tongdunTestId);
        listTest.add(xhtdTestId);
        listTest.add(moxieTestId);

        listPro.add(tongdunProId);
        listPro.add(xhtdProId);
        listPro.add(moxieProId);
    }


}
