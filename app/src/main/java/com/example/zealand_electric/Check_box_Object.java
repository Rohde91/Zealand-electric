package com.example.zealand_electric;

public class Check_box_Object {
    private int category_position;
    private int question_position;
    private int answerOnCheckBox;
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Check_box_Object(int category_position,int question_position, String note) {
        this.category_position = category_position;
        this.question_position = question_position;
        this.note = note;
    }

    public Check_box_Object(int category_position, int question_position) {
        this.category_position = category_position;
        this.question_position = question_position;
    }

    public Check_box_Object(int category_position, int question_position, int answerOnCheckBox) {
        this.category_position = category_position;
        this.question_position = question_position;
        this.answerOnCheckBox = answerOnCheckBox;
    }

    public Check_box_Object(int category_position, int question_position, int answerOnCheckBox, String note) {
        this.category_position = category_position;
        this.question_position = question_position;
        this.answerOnCheckBox = answerOnCheckBox;
        this.note = note;
    }

    public int getCategory_position() {
        return category_position;
    }

    public void setCategory_position(int category_position) {
        this.category_position = category_position;
    }

    public int getQuestion_position() {
        return question_position;
    }

    public void setQuestion_position(int question_position) {
        this.question_position = question_position;
    }

    public int getAnswerOnCheckBox() {
        return answerOnCheckBox;
    }

    public void setAnswerOnCheckBox(int answerOnCheckBox) {
        this.answerOnCheckBox = answerOnCheckBox;
    }

    @Override
    public String toString() {
        return "Check_box_Object{" +
                "category_position=" + category_position +
                ", question_position=" + question_position +
                ", note='" + note + '\'' +
                '}';
    }
}
