import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerRegistration {
    private JFrame frame;
    private JPanel mainPanel;
    private JTextField firstNameField, lastNameField, ageField, emailField;
    private JTextArea messageArea;
    private JButton submitButton, uploadButton;
    private JLabel photoLabel;
    private ImageIcon photo;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private ButtonGroup genderGroup;



    public CustomerRegistration() {
        frame = new JFrame("Customer Form");
        //cant work
        //mainPanel = new JPanel(new GridLayout(6, 2));

        //change height and weight
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(Box.createVerticalStrut(50));

        // First Name
        JPanel firstNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        firstNamePanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField(20);
        firstNamePanel.add(firstNameField);
        mainPanel.add(firstNamePanel);

        // Last Name
        JPanel lastNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lastNamePanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField(20);
        lastNamePanel.add(lastNameField);
        mainPanel.add(lastNamePanel);

        //gender
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        genderPanel.add(new JLabel("Gender:"));
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        mainPanel.add(genderPanel);

        // Age
        JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        agePanel.add(new JLabel("Age:"));
        ageField = new JTextField(20);
        agePanel.add(ageField);
        mainPanel.add(agePanel);

        // Email
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        emailPanel.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        emailPanel.add(emailField);
        mainPanel.add(emailPanel);

        // Message
        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        messagePanel.add(new JLabel("Message:"));
        messageArea = new JTextArea(5, 20);
        messagePanel.add(messageArea);
        mainPanel.add(messagePanel);

        //Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        uploadButton = new JButton("Upload Photo");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    photo = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
                    photoLabel.setIcon(photo);
                }
            }
        });
        buttonPanel.add(uploadButton);


        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gender = maleRadioButton.isSelected() ? "Male" : (femaleRadioButton.isSelected() ? "Female" : "Not specified");
                if (isValidInput()&&isValidInput1()&&isValidEmail(emailField.getText())) {
                    JOptionPane.showMessageDialog(frame, "Details:\n" +
                                    "First Name: " + firstNameField.getText() + "\n" +
                                    "Last Name: " + lastNameField.getText() + "\n" +
                                    "gender:" + gender + "\n" +
                                    "Age: " + ageField.getText() + "\n" +
                                    "Email: " + emailField.getText() + "\n" +
                                    "Message: " + messageArea.getText(),
                            "Customer Details", JOptionPane.INFORMATION_MESSAGE, photo);
                }
            }
        });
        buttonPanel.add(submitButton);

        //photo label
        photoLabel = new JLabel();
        buttonPanel.add(photoLabel);

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalStrut(50));

        // Add the mainPanel to the frame
        frame.add(mainPanel);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



    }

    private boolean isValidInput() {
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || ageField.getText().isEmpty() || emailField.getText().isEmpty() || messageArea.getText().isEmpty() || (!maleRadioButton.isSelected()&!femaleRadioButton.isSelected())) {
            JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Add more validations as needed
        return true;
    }

    private boolean isValidInput1() {
        if (!firstNameField.getText().matches("[a-zA-Z\u4e00-\u9fa5]+")) {
            JOptionPane.showMessageDialog(frame, "First Name should contain only letters or Chinese characters!");
            return false;
        }

        if (!lastNameField.getText().matches("[a-zA-Z\u4e00-\u9fa5]+")) {
            JOptionPane.showMessageDialog(frame, "Last Name should contain only letters or Chinese characters!");
            return false;
        }

        try {
            Integer.parseInt(ageField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Age should be a valid integer!");
            return false;
        }

        if (!isValidEmail(emailField.getText())) {
            JOptionPane.showMessageDialog(frame, "Email is not valid!");
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static void main(String[] args) {
        new CustomerRegistration();
    }
}
