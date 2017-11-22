package com.application.lotto.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> numbers;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrawNumber that = (DrawNumber) o;
        return Id == that.Id &&
                drawId == that.drawId &&
                Objects.equals(dateOfDraw, that.dateOfDraw) &&
                Objects.equals(drawNumbers, that.drawNumbers) &&
                Objects.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, drawId, dateOfDraw, drawNumbers, numbers);
    }
}
