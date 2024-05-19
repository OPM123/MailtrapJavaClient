package org.opm;

/**
 * Enum of attachment style
 */
public enum Disposition {

    INLINE,
    ATTACHMENT;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
