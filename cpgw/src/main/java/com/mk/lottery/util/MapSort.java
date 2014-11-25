package com.mk.lottery.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by mk on 2014/8/16.
 */
public class MapSort {

    public static List compareMap(List mappingList){
        //通过比较器实现比较排序
        Collections.sort(mappingList, new Comparator<Map.Entry<String, String>>() {

            @Override
            public int compare(Map.Entry<String, String> mapping1,
                               Map.Entry<String, String> mapping2) {
                return mapping1.getValue().compareTo(mapping2.getValue());
            }

        });
        return mappingList;
    }
}
