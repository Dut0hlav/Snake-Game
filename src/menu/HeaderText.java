package menu;
import javax.swing.*;
import java.awt.*;
import static utils.Constants.BACKGROUND_COLOR;

public class HeaderText extends JTextField {
    public HeaderText(String text, int size){
        super(text);
        setColumns(50);
        this.setHorizontalAlignment(CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setFont(new Font("Arial", Font.BOLD, size));
        this.setEditable(false);
        this.setBackground(BACKGROUND_COLOR);
    }
}
