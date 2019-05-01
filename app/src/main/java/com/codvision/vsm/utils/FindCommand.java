package com.codvision.vsm.utils;

import android.content.Context;

public class FindCommand {
    private Context context;

    public FindCommand(Context context) {
        this.context = context;
    }

    public String findCommand(String string) {
        String result = "抱歉，我还没有理解你的话，等我先学会呢";
        int type = getSimilar(string);
        switch (type) {
            case 1:
                result = "明天的计划是好好做毕业设计";
                break;
            case 2:
                result = "你才是傻子" ;
                break;
            default:
                break;
        }
        return result;
    }

    private int getSimilar(String string) {
        int type = 0;
        if (string.contains("明天") && string.contains("计划")) {
            return 1;
        }else if (string.contains("你") && string.contains("傻子")){
            return 2;
        }
        return type;
    }
}
