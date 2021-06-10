package com.ts.Model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Statistics {
	
    private BigDecimal sum;
  
    private BigDecimal avg;
   
    private BigDecimal max;
    
    private BigDecimal min;
    
    private Long count;

    public Statistics() {
        sum = BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_UP);
        avg = BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_UP);
        max = BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_UP);
        min = BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_UP);
        count = 0L;
    }

    public Statistics(BigDecimal sum, BigDecimal avg, BigDecimal max, BigDecimal min, Long count) {
        super();
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }


    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Statics{" +
                "sum=" + sum +
                ", avg=" + avg +
                ", max=" + max +
                ", min=" + min +
                ", count=" + count +
                '}';
    }
}
