package com.pkc.socialmultiplication.book.multiplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public final class Multiplication {

    @Id
    @GeneratedValue
    @Column(name = "MULTIPLICATION_ID")
    private Long id;

    private final int factorA;

    private final int factorB;

    private int result;

    public int getFactorA() {
        return factorA;
    }

    public int getFactorB() {
        return factorB;
    }

    public int getResult() {
        return result;
    }


    public Multiplication(int factorA, int factorB){
        this.factorA=factorA;
        this.factorB=factorB;
        this.result=factorA*factorB;
    }

    public Multiplication() {
        this(0, 0);
    }

    @Override
    public String toString() {
        return "Multiplication{" +
                "factorA=" + factorA +
                ", factorB=" + factorB +
                ", result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Multiplication that = (Multiplication) o;
        return factorA == that.factorA && factorB == that.factorB && result == that.result && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, factorA, factorB, result);
    }
}
