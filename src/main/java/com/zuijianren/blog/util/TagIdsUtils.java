package com.zuijianren.blog.util;

public class TagIdsUtils {
    public static String[] getTagIds(String tagIds){
        String[] split = tagIds.split(",");
        return split;
    }
    public static void main(String[] args) {
        String tagIds = "1591429247893,1591440969162";
        String[] split = tagIds.split(",");
        for (String s : split) {
            System.out.println(s);
        }
    }
}
