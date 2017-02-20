package com.company;

import java.security.InvalidParameterException;

/**
 * Created by hackeru on 2/20/2017.
 */
public class CoolNode implements Comparable {
    int value;
    int row;
    int col;

    public CoolNode(int value, int row, int col) {
        this.value = value;
        this.col = col;
        this.row = row;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof CoolNode) {
            CoolNode c = ((CoolNode) o);
            if (value < c.value)
                return -1;
            else if(value==c.value)
                return 0;
            return 1;
        }
        throw new InvalidParameterException();
    }
}
