package com.rohitsood.urlybird.gui.widget.button;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.border.LineBorder;


/**
 * Provides embellishments for a <tt>JButton</tt>. Three states exists for the button.<br>
 * 1. Default State <br>
 * 2. Mouse Over State <br>
 * 3. Mouse Pressed State <br>
 * Any other state transitions will default to its default state. Embellishments are provided by color changes on
 * hover, clicks, and default settings. This makes the application more user friendly and thematic.
 *
 * @author Rohit Sood
 * @version 1.2
 */
public class CommonButton extends JButton {
    /** Default state constant. No mouse is over this. */
    static final int DEFAULT_STATE = 1;

    /** Mouse-over state constant. */
    static final int MOUSE_OVER = 2;

    /** Mouse-pressed state constant. */
    static final int MOUSE_PRESSED = 3;

    /** Border for the default state. */
    private static LineBorder defaultBorder;

    /** Border for the mouse-over state. */
    private static LineBorder mouseOverBorder;

    /** Border for the mouse pressed state. */
    private static LineBorder mousePressedBorder;

    /** Border for the disabled state. */
    private static LineBorder disabledBorder;

    /**The factor by which the button width needs to increase.*/
    private static final double WIDTH_FACTOR = 1.5;
	/**The factor by which the button height needs to increase.*/
    private static final double HEIGHT_FACTOR=1.25;

    /** The text of the button */
    private String text;

    /**
     * Construtor for a new <tt>CommonButton</tt> object. Sets the display name of the button.
     *
     * @param text The text of the button.
     */
    public CommonButton(String text) {
        //super(" " + text + " "); //embellishment.
        super(text);
        this.text = text;
        init();
    }

    /**
     * Initialize this component. Adds a listener. Sets the default border.
     */
    private void init() {
        addMouseListener(new CommonButtonMouseListener(this));
        initializeBorders();
        setBorder(defaultBorder);

        Dimension size = getPreferredSize();
        Dimension newSize = new Dimension((int) (size.width * WIDTH_FACTOR), (int)(size.height*HEIGHT_FACTOR));
        setMinimumSize(newSize);
        setMaximumSize(newSize);
        setPreferredSize(newSize);
        setSize(newSize);

        char mnemonic = text.charAt(0);
        setMnemonic(mnemonic);
    }

    /**
     * Makes a state transition. The set state will default if an unknown state is provided. State transitions are
     * legal from any other state.
     *
     * @param state The state to transition to.
     */
    public void setState(int state) {
        //based on the state switch
        switch (state) {
        case MOUSE_OVER:
            setBorder(mouseOverBorder);

            break;

        case MOUSE_PRESSED:
            setBorder(mousePressedBorder);

            break;

        case DEFAULT_STATE:default:
            setBorder(defaultBorder);
        }
    }

    /**
     * Initializes borders. This occurs only once. Border instances are cached.
     */
    private void initializeBorders() {
        if (mouseOverBorder == null) {
            mouseOverBorder = new LineBorder(Color.YELLOW);
        }

        if (mousePressedBorder == null) {
            mousePressedBorder = new LineBorder(Color.GREEN);
        }

        if (defaultBorder == null) {
            defaultBorder = new LineBorder(Color.BLUE);
        }

        if (disabledBorder == null) {
            disabledBorder = new LineBorder(Color.GRAY);
        }
    }
}
