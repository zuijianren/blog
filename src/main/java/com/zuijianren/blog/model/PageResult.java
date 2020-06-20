package com.zuijianren.blog.model;

import java.util.ArrayList;
import java.util.List;

/**
 * admin下使用
 * 查询list的结果封装
 * 默认当前第一页，每页显示五条数据
 */
public class  PageResult <T>{
    //默认
    private int page = 1;           //当前页数，默认为1
    private int size = 5;           //页面大小，即每页的数量，默认为5
    private int start = (page-1)*size;          //起始记录。默认为0
    private String sort = "title";      //排序依据，默认根据 title 排序
    private Boolean desc = false;       //默认正序
    //需要外部设置填充
    private List<T> list = new ArrayList<>();       //所有对象
    //可根据数据填充
    private int totalElements;  //总数
    private Boolean first;    //是否是第一页
    private Boolean last;     //是否是最后一页
    private int totalPages;     //总页数

    //无参构造方法
    public PageResult() {
    }

    //自定义的无参set方法
    public void setStart(){
        start = (page-1)*size;
    }

    public void setTotalPages(){
        if(totalElements%size == 0){
            totalPages = totalElements/size;
        }else{
            totalPages = totalElements/size+1;
        }
    }

    public void setFirst(){
        if(page <= 1){
            first = true;
        }else{
            first = false;
        }
    }

    public void setLast(){
        if(page >= totalPages){
            last = true;
        }else{
            last = false;
        }
    }

    //填充参数
    public void fill(){
        setStart();
        setTotalPages();
        setFirst();
        setLast();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Boolean getDesc() {
        return desc;
    }

    public void setDesc(Boolean desc) {
        this.desc = desc;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
