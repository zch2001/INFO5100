import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.toedter.calendar.JDateChooser;
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



    public PatientRegistration() {
        frame = new JFrame("Patient Registration");
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

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
        submitButton.addActionListener(e -> {
            if (validateInputs()) {
                cardLayout.show(cardPanel, "viewPanel");
                String gender = maleRadioButton.isSelected() ? "Male" : (femaleRadioButton.isSelected() ? "Female" : "Not specified");
                firstNameDisplay.setText("First Name: " + firstNameField.getText());
                lastNameDisplay.setText("Last Name: " + lastNameField.getText());
                genderDisplay.setText("Gender: " + gender);
                ageDisplay.setText("Age: " + ageField.getText());
                emailDisplay.setText("Email: " + emailField.getText());
                messageDisplay.setText("Message: " + messageArea.getText());
                patientTypeDisplay.setText("Patient Type: " + patientTypeComboBox.getSelectedItem().toString());
                dateDisplay.setText("Date: " + dateFormat.format(dateChooser.getDate()));
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

        // View panel
        viewPanel = new JPanel(new GridLayout(10,2));
        viewArea = new JTextArea();
        viewArea.setEditable(false);


        backButton = new JButton("Back to Registration");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "registerPanel"));
        viewPanel.add(backButton, BorderLayout.SOUTH);
        firstNameDisplay = new JLabel("First Name: ");
        viewPanel.add(firstNameDisplay);

        lastNameDisplay = new JLabel("Last Name: ");
        viewPanel.add(lastNameDisplay);

        genderDisplay = new JLabel("Gender: ");
        viewPanel.add(genderDisplay);

        ageDisplay = new JLabel("Age: ");
        viewPanel.add(ageDisplay);

        emailDisplay = new JLabel("Email: ");
        viewPanel.add(emailDisplay);

        messageDisplay = new JLabel("Message: ");
        viewPanel.add(messageDisplay);

        patientTypeDisplay = new JLabel("Patient Type: ");
        viewPanel.add(patientTypeDisplay);

        dateDisplay = new JLabel("Date: ");
        viewPanel.add(dateDisplay);

        viewPanel.add(photoLabel);

        cardPanel.add(registerPanel, "registerPanel");
        cardPanel.add(viewPanel, "viewPanel");
        frame.add(cardPanel);
        frame.setSize(500, 500);
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

    private void setupViewPanel() {
        viewPanel = new JPanel();
        viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));

        firstNameDisplay = new JLabel("First Name: ");
        viewPanel.add(firstNameDisplay);

        lastNameDisplay = new JLabel("Last Name: ");
        viewPanel.add(lastNameDisplay);

        genderDisplay = new JLabel("Gender: ");
        viewPanel.add(genderDisplay);

        ageDisplay = new JLabel("Age: ");
        viewPanel.add(ageDisplay);

        emailDisplay = new JLabel("Email: ");
        viewPanel.add(emailDisplay);

        messageDisplay = new JLabel("Message: ");
        viewPanel.add(messageDisplay);

        patientTypeDisplay = new JLabel("Patient Type: ");
        viewPanel.add(patientTypeDisplay);

        dateDisplay = new JLabel("Date: ");
        viewPanel.add(dateDisplay);
    }



    public static void main(String[] args) {
        new PatientRegistration();
    }
}
