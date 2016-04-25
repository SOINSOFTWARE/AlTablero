/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.entity;

/**
 * @author Carlos Rodriguez
 * @since 03/02/2016
 * @version 1.0
 */
public class PeriodBO extends AbstractWithCodeBO implements Comparable<PeriodBO> {

    private static final long serialVersionUID = -6503035896094998534L;

    private YearBO year;

    public PeriodBO() {
        super();
    }

    public YearBO getYear() {
        return year;
    }

    public void setYear(YearBO year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "PeriodBO [year=" + year + ", id=" + id + ", code=" + code
                + ", name=" + name + ", creation=" + creation
                + ", updated=" + updated + ", enabled=" + enabled + "]";
    }

    @Override
    public int compareTo(final PeriodBO other) {
         Integer itsCode = Integer.parseInt(this.code);
        Integer otherCode = Integer.parseInt(other.getCode());
        return itsCode.compareTo(otherCode);
    }
}
