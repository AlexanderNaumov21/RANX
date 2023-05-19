import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class example {
    public static void main(String args[]) {
        JFrame a = new JFrame("Электроэнергия");

        a.setSize(300, 230);
        a.setLayout(null); // абсолютное позиционирование — вы должны выполнять всю работу в своем коде, без помощи менеджера Layout
        a.setLocationRelativeTo(null); // позиционируем окно по центру
        a.setVisible(true); // указываем видимость - тру
// -------лэйблы --------------
        JLabel x1y1_l;
        x1y1_l = new JLabel("Показания счётчика");
        x1y1_l.setBounds(90, 10, 120, 20);
        a.add(x1y1_l);

        JLabel x1y2_l;
        x1y2_l = new JLabel("Предыдущие");
        x1y2_l.setBounds(10, 40, 120, 20);
        a.add(x1y2_l);

        JLabel x1y3_l;
        x1y3_l = new JLabel("Текущее");
        x1y3_l.setBounds(10, 70, 120, 20);
        a.add(x1y3_l);

        JLabel x1y4_l;
        x1y4_l = new JLabel("Цена за кВт");
        x1y4_l.setBounds(10, 100, 120, 20);
        a.add(x1y4_l);

        JLabel x1y6_l;
        x1y6_l = new JLabel();
        x1y6_l.setBounds(10, 160, 120, 20);
        a.add(x1y6_l);
// -------поля ввода --------------
        JTextField x2y2_f = new JTextField();
        x2y2_f.setBounds(140, 40, 120, 20);
        a.add(x2y2_f);

        JTextField x2y3_f = new JTextField();
        x2y3_f.setBounds(140, 70, 120, 20);
        a.add(x2y3_f);

        JTextField x2y4_f = new JTextField();
        x2y4_f.setBounds(140, 100, 120, 20);
        a.add(x2y4_f);
// -------кнопки --------------
        JButton x1y5_b = new JButton("Вычислить");
        x1y5_b.setBounds(10, 130, 120, 20);

        x1y5_b.addActionListener(new ActionListener() { //добавляем нового слушателя
            // Используем переопределение
            @Override // @Override — проверяет, переопределён ли метод. Вызывает ошибку компиляции / интерпретации, если метод не найден в родительском классе или интерфейсе;

            // Добавляем событие нажатия на кнопку e — название события
            public void actionPerformed(ActionEvent e) {
                try {
                    double value1 = Double.parseDouble(x2y2_f.getText());
                    double value2 = Double.parseDouble(x2y3_f.getText());
                    double value3 = Double.parseDouble(x2y4_f.getText());
                    double value0 = (value2 - value1 ) * value3;

                    x1y6_l.setText(String.valueOf(value0));

                    Date date0 = new Date();

                    connect("INSERT INTO electr(pred, tek, c_kvt, ccym, date)     VALUES ("+value1+", "+value2+", "+value3+","+value0+", "+ "\"" + date0+ "\"" + ")");

                } catch (Exception ex) {
                    x1y6_l.setText("Ошибка ввода");

                }

            }
        });

        a.add(x1y5_b);

        JButton x2y5_b = new JButton("История");
        x2y5_b.setBounds(140, 130, 120, 20);
        x2y5_b.addActionListener(new ActionListener() {
            // Используем переопределение
            @Override
            // Добавляем событие нажатия на кнопку e — название события
            public void actionPerformed(ActionEvent e) {

                connect("SELECT * FROM electr");

            }
        });
        a.add(x2y5_b);

    }

    public static void connect( String tx) {
        Connection conn = null;
        Statement statement = null; // Создает объект SQLServerStatement для отправки инструкций SQL в базу данных.
        ResultSet resultSet = null; // сет полученных значений после запроса
        try {
            String url = "jdbc:sqlite:E:/java/untitled/src/sqlite.db"; // путь до файла БД
            //создать соединение с базой данных
            conn = DriverManager.getConnection(url); // конектимся с БД по url
            System.out.println("Connection to SQLite has been established.");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(tx ); // передаём код самого запроса


            if (resultSet.next() == true) { // если нам возвращён результат
                String line_str = new String();
                line_str = "пред.пок.|тек.пок.|рубЗАкВт|итого|дата\n";
                while (resultSet.next()) { // то генерируем строку (текст)
                    //pred, tek, c_kvt, ccym, date

                    line_str += resultSet.getString("pred") + "|" + resultSet.getString("tek") + "|" + resultSet.getString("c_kvt") + "|" + resultSet.getString("ccym") + "|" + resultSet.getString("date") + '\n';
                }
                form2 fr2 = new form2(line_str); // Создаём экземпляр объекта form2(новое окно)
                fr2.setVisible(true);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally { // закрываем соединение с БД
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
