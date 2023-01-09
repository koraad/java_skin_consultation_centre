

import java.io.Serial;
import java.io.Serializable;

public class Date implements Serializable {

    @Serial
    private static final long serialVersionUID = 2925417448261412915L;
    private int year, month, day;

    public Date(int day, int month, int year){
        if ((year > 1916) && (year<2099) && (month > 0) && (month<13) && (day > 0) && (day<32)){
            setDay(day);
            setMonth(month);
            setYear(year);
        }
        else{
            System.out.println("Invalid Date");
        }
    }


    // setters
    public void setYear(int year){
        if ((year > 1916) && (year<2099)){
            this.year = year;
        }
        else{
            System.out.println("Year not acceptable");
        }
    }
    public void setMonth(int month){
        if ((month > 0) && (month<13)){
            this.month = month;
        }else{
            System.out.println("Not a valid month");
        }
    }
    public void setDay(int day){
        if ((day > 0) && (day<32)){
            this.day = day;
        }else{
            System.out.println("Not a valid day");
        }
    }


    // getters
    public int getYear(){

        return year;
    }
    public int getMonth(){

        return month;
    }
    public int getDay(){

        return day;
    }

    public String toString(){

        return String.format("%02d/%02d/%04d", getDay(), getMonth(), getYear());
    }
}

