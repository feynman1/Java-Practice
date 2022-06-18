package com.hspjava.exercise07;

import java.util.ArrayList;

public class ex7 {
    public static void main(String[] args) {
        news new1 = new news("asdasda");
        news new2 = new news("oiyui");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new1);
        arrayList.add(new2);
        for (int i = arrayList.size()-1; i >=0; i--) {
        }
    }
}

class news{
    private String title;
    private String content;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public news(String title) {
        this.title = title;
    }

    public String toString(){
        System.out.println(title);
        return title;
    }
}
