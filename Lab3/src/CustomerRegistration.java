import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerRegistration {
    private JFrame frame;
    private JTextField firstNameField, lastNameField, ageField, emailField;
    private JTextArea messageArea;
    private JButton submitButton, uploadButton;
    private JLabel photoLabel;
    private ImageIcon photo;

    public CustomerRegistration() {
        frame = new JFrame("Customer Form");
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());

        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        ageField = new JTextField(20);
        emailField = new JTextField(20);
        messageArea = new JTextArea(5, 20);

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

        photoLabel = new JLabel();

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (true) {
                    JOptionPane.showMessageDialog(frame, "Details:\n" +
                                    "First Name: " + firstNameField.getText() + "\n" +
                                    "Last Name: " + lastNameField.getText() + "\n" +
                                    "Age: " + ageField.getText() + "\n" +
                                    "Email: " + emailField.getText() + "\n" +
                                    "Message: " + messageArea.getText(),
                            "Customer Details", JOptionPane.INFORMATION_MESSAGE, photo);
                }
            }
        });

        frame.add(new JLabel("First Name:"));
        frame.add(firstNameField);
        frame.add(new JLabel("Last Name:"));
        frame.add(lastNameField);
        frame.add(new JLabel("Age:"));
        frame.add(ageField);
        frame.add(new JLabel("Email:"));
        frame.add(emailField);
        frame.add(new JLabel("Message:"));
        frame.add(messageArea);
        frame.add(uploadButton);
        frame.add(photoLabel);
        frame.add(submitButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        new CustomerRegistration();
    }
}
