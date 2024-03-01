import javax.swing.*;
import java.awt.*;

public class RendererContacte extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Contact) {
            Contact contact = (Contact) value;
            setText(contact.getNume() + " - " + contact.getNumarTelefon()); // Personalizează textul după cum dorești
            setIcon(new ImageIcon(getClass().getResource("/path_to_your_icon.png"))); // Setează iconița
        }
        return this;
    }
}
