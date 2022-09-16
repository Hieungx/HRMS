package com.auth.domain.utils;

import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class DataUtils {
    public static List<String> getRoles (String roles){
        List<String> listRole = new ArrayList<>();
        if (!roles.contains(",")) {
            listRole.add(roles);
        } else {
            int dot = roles.split(",").length;
            for (int i = 0; i < dot; i++) {
                listRole.add(roles.split(",")[i]);
            }
        }
        if (ObjectUtils.isEmpty(listRole) || listRole.size() == 0) {
            return null;
        } else {
            return listRole;
        }
    }
}
