package com.test;

public class HelloWord {
    private String talk;

    private UpUP upup;

    public void say() {
        System.out.println(this.talk);
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }

    public UpUP getUpup() {
        return upup;
    }

    public void setUpup(UpUP upup) {
        this.upup = upup;
    }
}
