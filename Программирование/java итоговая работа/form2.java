import javax.swing.*;

public class form2 extends JFrame {
    public form2(String str_b) { // принимаем строку и добавляем её в окно № 2
        JFrame b = new JFrame("История");

        JTextArea textArea = new JTextArea();
        textArea.append(str_b);
        b.add(new JScrollPane(textArea));

        b.setSize(400, 600);
        b.setLocationRelativeTo(null); // позиционирование
        b.setVisible(true);
    }
}
