package com.application.lotto.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "DRAW_RESULTS1")
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
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Integer> numbers;

    public DrawNumber(int id, String dateOfDraw, List<Integer> numbers) {
        this.drawId = id;
        this.dateOfDraw = dateOfDraw;
        this.numbers = numbers;
    }

    public DrawNumber() {
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

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

}
