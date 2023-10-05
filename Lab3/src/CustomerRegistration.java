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

    public CustomerRegistration() {
        frame = new JFrame("Customer Registration");
        mainPanel = new JPanel(new GridLayout(6, 2));
        //change height and weight
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


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
        buttonPanel.add(submitButton);
        mainPanel.add(buttonPanel);

        // Add the mainPanel to the frame
        frame.add(mainPanel);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



        photoLabel = new JLabel();








    }



    public static void main(String[] args) {
        new CustomerRegistration();
    }
}
