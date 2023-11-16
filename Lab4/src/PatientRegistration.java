import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.toedter.calendar.JDateChooser;
import model.User;
import java.util.List;
import java.util.ArrayList;

import java.util.Calendar;

class Patient {
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String email;
    private String message;
    private String patientType;
    private Date registrationDate;

    // Constructor, getters and setters
}

public class PatientRegistration {

    private JFrame frame;
    private JPanel cardPanel, registerPanel, viewPanel;
    private JTextField firstNameField, lastNameField, ageField, emailField;
    private JTextArea messageArea, viewArea;
    private JButton submitButton, uploadButton, backButton;
    private JLabel photoLabel;
    private ImageIcon photo;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private ButtonGroup genderGroup;
    private JComboBox<String> patientTypeComboBox;
    private JDateChooser dateChooser;
    private CardLayout cardLayout;
    private JLabel firstNameDisplay, lastNameDisplay, ageDisplay, emailDisplay, messageDisplay, genderDisplay, patientTypeDisplay, dateDisplay, photoDisaplay;
    private DateFormat dateFormat = DateFormat.getDateInstance();
    private JButton createButton, viewButton;



    public PatientRegistration() {
        frame = new JFrame("Patient Registration");
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        frame = new JFrame("Patient Registration");
        frame.setLayout(new BorderLayout()); // Use BorderLayout for the frame

        // Top panel with create and view buttons
        JPanel topPanel = new JPanel();
        createButton = new JButton("Create");
        viewButton = new JButton("View");
        topPanel.add(createButton);
        topPanel.add(viewButton);
        frame.add(topPanel, BorderLayout.NORTH);

        // Registration panel
        registerPanel = new JPanel(new GridLayout(10, 2));

        // First name
        registerPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField(20);
        registerPanel.add(firstNameField);

        // Last name
        registerPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField(20);
        registerPanel.add(lastNameField);

        // Gender
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        registerPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        registerPanel.add(genderPanel);

        // Age
        registerPanel.add(new JLabel("Age:"));
        ageField = new JTextField(20);
        registerPanel.add(ageField);

        // Email
        registerPanel.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        registerPanel.add(emailField);

        // Message
        registerPanel.add(new JLabel("Message:"));
        messageArea = new JTextArea(5, 20);
        registerPanel.add(new JScrollPane(messageArea));

        // Patient type
        registerPanel.add(new JLabel("Patient Type:"));
        String[] types = {"Inpatient", "Outpatient"};
        patientTypeComboBox = new JComboBox<>(types);
        registerPanel.add(patientTypeComboBox);

        // Registration date (Bonus)
        registerPanel.add(new JLabel("Registration Date:"));
        dateChooser = new JDateChooser();
        registerPanel.add(dateChooser);

        JPanel TextPanel = new JPanel();
        JLabel TextLabel = new JLabel("Upload Photo");
        TextPanel.add(TextLabel);
        registerPanel.add(TextPanel);



        uploadButton = new JButton("Upload");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    photo = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
                    photoLabel.setIcon(photo);
                    photoLabel.revalidate();
                    photoLabel.repaint();
                }
            }
        });


        // Submit button
        JPanel buttonPanel = new JPanel();
        submitButton = new JButton("Submit");
//        submitButton.addActionListener(e -> {
//            if (validateInputs()) {
//                cardLayout.show(cardPanel, "viewPanel");
//                String gender = maleRadioButton.isSelected() ? "Male" : (femaleRadioButton.isSelected() ? "Female" : "Not specified");
//                firstNameDisplay.setText("First Name: " + firstNameField.getText());
//                lastNameDisplay.setText("Last Name: " + lastNameField.getText());
//                genderDisplay.setText("Gender: " + gender);
//                ageDisplay.setText("Age: " + ageField.getText());
//                emailDisplay.setText("Email: " + emailField.getText());
//                messageDisplay.setText("Message: " + messageArea.getText());
//                patientTypeDisplay.setText("Patient Type: " + patientTypeComboBox.getSelectedItem().toString());
//                dateDisplay.setText("Date: " + dateFormat.format(dateChooser.getDate()));
//            }
//    });
            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    // 获取文本框内容并拼接
                    String firstName = firstNameField.getText(); // 替换为你的firstName文本框名称
                    String lastName = lastNameField.getText();   // 替换为你的lastName文本框名称
                    String fullName = firstName + " " + lastName;    // 拼接成全名

                    int age = Integer.parseInt(ageField.getText()); // 从年龄文本框获取年龄

                    // 创建User对象
                    User newUser = new User(fullName, age); // 假设User类接受全名和年龄

                    // 添加用户到数据库
                    try {
                        DatabaseConnector.addUser(newUser);  // 调用DatabaseConnector类的静态方法
                        JOptionPane.showMessageDialog(null, "用户添加成功！");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "错误：" + e.getMessage());
                    }
                }
            });


        buttonPanel.add(uploadButton);
        buttonPanel.add(submitButton);
        registerPanel.add(buttonPanel);

        //Photo
        photoLabel = new JLabel();
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        photoLabel.setPreferredSize(new Dimension(100, 100)); // set your desired width and height
        registerPanel.add(photoLabel);

        viewPanel = new JPanel();
        setupViewPanel(); // 确保这个调用在添加按钮的 ActionListener 之前

        // Add action listeners to buttons
        createButton.addActionListener(e -> cardLayout.show(cardPanel, "registerPanel"));
        viewButton.addActionListener(e -> {
            setupViewPanel();
            cardLayout.show(cardPanel, "viewPanel");
        });

//        // View panel
//        viewPanel = new JPanel(new GridLayout(10,2));
//        viewArea = new JTextArea();
//        viewArea.setEditable(false);
//
//
//        backButton = new JButton("Back to Registration");
//        backButton.addActionListener(e -> cardLayout.show(cardPanel, "registerPanel"));
//        viewPanel.add(backButton, BorderLayout.SOUTH);
//        firstNameDisplay = new JLabel("First Name: ");
//        viewPanel.add(firstNameDisplay);
//
//        lastNameDisplay = new JLabel("Last Name: ");
//        viewPanel.add(lastNameDisplay);
//
//        genderDisplay = new JLabel("Gender: ");
//        viewPanel.add(genderDisplay);
//
//        ageDisplay = new JLabel("Age: ");
//        viewPanel.add(ageDisplay);
//
//        emailDisplay = new JLabel("Email: ");
//        viewPanel.add(emailDisplay);
//
//        messageDisplay = new JLabel("Message: ");
//        viewPanel.add(messageDisplay);
//
//        patientTypeDisplay = new JLabel("Patient Type: ");
//        viewPanel.add(patientTypeDisplay);
//
//        dateDisplay = new JLabel("Date: ");
//        viewPanel.add(dateDisplay);
//
//        viewPanel.add(photoLabel);
//
        cardPanel.add(registerPanel, "registerPanel");
        frame.add(cardPanel);
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private boolean validateInputs() {
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                ageField.getText().isEmpty() || emailField.getText().isEmpty() ||
                dateChooser.getDate() == null ||
                (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())) {

            JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!firstNameField.getText().matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(frame, "First Name should only contain letters!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!lastNameField.getText().matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(frame, "Last Name should only contain letters!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int age = Integer.parseInt(ageField.getText());
            if (age < 0 || age > 120) {
                JOptionPane.showMessageDialog(frame, "Age should be between 0 and 120!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Age should be a valid integer!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailField.getText());
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(frame, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

//public void setupViewPanel() {
//        // 使用 DatabaseConnector 类获取用户数据
//        List<User> users = DatabaseConnector.getAllusers();
//
//        // 创建表格模型并定义列名
//        String[] columnNames = {"ID", "Name", "Age"};
//        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
//
//        // 遍历用户列表，将每个用户的数据添加到表格模型中
//        for (User user : users) {
//            model.addRow(new Object[]{user.getId(), user.getName(), user.getAge()});
//        }
//
//        // 创建表格并设置模型
//        JTable table = new JTable(model);
//
//        // 创建包含表格的滚动面板
//        JScrollPane scrollPane = new JScrollPane(table);
//
//        // 创建面板并添加滚动面板
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.add(scrollPane, BorderLayout.CENTER);
//
//        // 添加面板到窗体或其他容器
//        // 假设有一个名为frame的JFrame实例
//        frame.add(panel);
//
//        // 刷新窗体以显示新添加的面板
//        frame.validate();
//
//}

    public void setupViewPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        List<User> users = DatabaseConnector.getAllusers();

        // 创建表格
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Age"}, 0);
        for (User user : users) {
            model.addRow(new Object[]{user.getId(), user.getName(), user.getAge()});
        }
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.WEST);

        // 创建右侧面板
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JTextField nameTextField = new JTextField(20);
        JTextField ageTextField = new JTextField(20);
        JButton deleteButton = new JButton("Delete");
        JButton editButton = new JButton("Edit");
        rightPanel.add(new JLabel("Name:"));
        rightPanel.add(nameTextField);
        rightPanel.add(new JLabel("Age:"));
        rightPanel.add(ageTextField);
        rightPanel.add(deleteButton);
        rightPanel.add(editButton);
        panel.add(rightPanel, BorderLayout.EAST);

        // 表格选择监听器
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    nameTextField.setText(table.getValueAt(selectedRow, 1).toString());
                    ageTextField.setText(table.getValueAt(selectedRow, 2).toString());
                }
            }
        });

        // 删除按钮监听器
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int userId = (int) table.getValueAt(selectedRow, 0);
                User userToDelete = new User(userId, null, -1); // 根据实际情况创建 User 对象
                DatabaseConnector.deleteUser(userToDelete);
                model.removeRow(selectedRow); // 从表格中移除行
            }
        });

        // 编辑按钮监听器
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int userId = (int) table.getValueAt(selectedRow, 0);
                String userName = nameTextField.getText();
                int userAge = Integer.parseInt(ageTextField.getText());
                User newUser = new User(userId, userName, userAge); // 根据实际情况创建 User 对象
                DatabaseConnector.editUser(new User(userId, null, -1), newUser); // 传入旧User和新User
                model.setValueAt(userName, selectedRow, 1);
                model.setValueAt(userAge, selectedRow, 2);
            }
        });

//        frame.add(panel);
//        frame.setSize(800, 400);
//        frame.setVisible(true);
        cardPanel.add(panel, "viewPanel"); // 确保这一行在方法的末尾
    }

    public static void main(String[] args) {
        new PatientRegistration();
    }
}
