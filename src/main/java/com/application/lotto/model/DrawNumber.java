package com.application.lotto.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DRAW_RESULTS2")
public class DrawNumber {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private int Id;

    @Column(name = "DRAW_ID", unique = true)
    private int drawId;

    @Column(name = "DRAW_DATE")
    private String dateOfDraw;

    @Column(name = "DRAW_NUMBERS")
    private String drawNumbers;

    public DrawNumber(int id, String dateOfDraw, String drawNumbers) {
        this.drawId = id;
        this.dateOfDraw = dateOfDraw;
        this.drawNumbers = drawNumbers;
    }

    public DrawNumber() {
    }

    @Override
    public String toString() {
        return "DrawNumber{" +
                "drawId=" + drawId +
                ", dateOfDraw='" + dateOfDraw + '\'' +
                ", drawNumbers='" + drawNumbers + '\'' +
                '}';
    }

    public int getDrawId() {
        return drawId;
    }

    public void setDrawId(int drawId) {
        this.drawId = drawId;
    }

    public String getDateOfDraw() {
        return dateOfDraw;
    }

    public void setDateOfDraw(String dateOfDraw) {
        dateOfDraw = dateOfDraw;
    }

    public String getDrawNumbers() {
        return drawNumbers;
    }

    public void setDrawNumbers(String drawNumbers) {
        this.drawNumbers = drawNumbers;
    }
}
