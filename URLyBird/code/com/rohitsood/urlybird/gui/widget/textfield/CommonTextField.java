package com.rohitsood.urlybird.gui.widget.textfield;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;


/**
 * A custom <tt>JTextField</tt> with embellishments to be used throughout the
 * user interface. The <tt>CommonTextField</tt> has the following behavior: On focus
 * the field will become yellow if the field is not disabled. When focus is
 * lost and the field is not disabled then the color is defaulted.
 *
 * @author Rohit Sood
 * @version 1.1
 */
public class CommonTextField extends JTextField {
    /** The default color for the text field. */
    public static final Color DEFAULT_COLOR = Color.WHITE;

    /** The disabled color for the text field. */
    public static final Color DISABLED_COLOR = Color.GRAY;

    /** The selected color for the text field. */
    public static final Color SELECTED_COLOR = Color.YELLOW;

    /** The minimum height for the text field. */
    private static final int MINIMUM_HEIGHT = 20;

    /** The minimum width for the text field. */
    private static final int MINIMUM_WIDTH = 120;

    /**
     * Creates a new CommonTextField object with a pre-defined length.
     *
     * @param len The length of the field.
     */
    public CommonTextField(int len) {
        super(len);
        init();
    }

    /**
     * Creates a new CommonTextField object.
     * Initializes the text field.
     */
    public CommonTextField() {
        super();
        init();
    }

    /**
     * Sets the color of the field to the selected color.
     * Has no effect if the field is not enabled.
     * if <code>isEnabled()</code> evaluates to <tt>false</tt> method simply returns.
     */
    public void setOnFocus() {
        if (isEnabled()) {
            setBackground(SELECTED_COLOR);
        }
    }

	/**
	 * Sets the color of the field to the unselected color.
	 * Has no effect if the field is not enabled.
	 * if <code>isEnabled()</code> evalutates to false method simply returns.
	 */
    public void setOffFocus() {
        if (isEnabled()) {
            setBackground(DEFAULT_COLOR);
        }
    }

    /**
     * Initializes the text field. Sets the default color and enables the
     * field. Adds a focus listener.
     */
    private void init() {
        setDisabledTextColor(DISABLED_COLOR);
        setEnabled(true);
        addFocusListener(new CommonTextFieldFocusListener(this));
        setPreferredSize(new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT));
    }
}
