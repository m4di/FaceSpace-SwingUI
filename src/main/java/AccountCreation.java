import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by Madi on 2017-03-27.
 *
 * Uses JTextField and JButton to accept username
 * Makes POST request to Grails API from Tutorial 1 (has to be running to work)
 * Displays success / error message based on response using JLabel
 *
 */



public class AccountCreation extends JPanel {

    //Sub Components
    private JTextField userNameBox;
    private JLabel created;
    private JButton nextButton;

    //Data
    private String userNameStr;

    public AccountCreation(final Callback callback, JFrame root){
        super();





        this.userNameBox = new JTextField(15);
        this.nextButton = new JButton("Create Account");
        this.created = new JLabel();
        this.nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = nextButton.getText();
                createNewUser(text);
            }
        });



        /*// Add subcomponents to this JPanel
        this.add(new JLabel("Enter a username: "));
        this.add(this.userNameBox);
        this.add(this.nextButton);
        this.add(this.created);*/

        this.setLayout(new GridLayout(8,8));
        this.add(new JLabel("Enter a username: "));

        this.userNameBox.setAlignmentX(3);
        this.add(this.userNameBox);

        this.nextButton.setAlignmentX(8);
        this.add(this.nextButton);

        this.created.setAlignmentX(4);
        this.created.setAlignmentY(2);
        this.add(this.created);

        this.setMinimumSize(new Dimension(300, 350));
        this.setVisible(true);


    }

    public void createNewUser(String name){
        if(callAPI(name).equals(true)) {
            created.setText("Account was created successfully!");
        } else {
            created.setText("Account could not be created :( ");
        }
    }


    // This method calls the REST API and requests a given user's status updates.
    // We are using BLOCKING or SYNCHRONOUS communication in this method. As a result,
    // the current thread will not resume execution until the API has responded or timed out.
    // Without threading, this should be considered a low-quality solution.
    //
    // This method also uses the GSON for JSON parsing (GSON because it's by Google).
    public String callAPI(String name){
        try {

            // Call the API
            URL myURL = new URL("http://localhost:8080/AccountCreation/createNewUser");
            URLConnection api = myURL.openConnection();
            // Call the API (ASYNCHRONOUS)
            api.connect();

            // Set up input stream reader
            BufferedReader in = new BufferedReader(new InputStreamReader(api.getInputStream()));
            String inputLine;
            StringBuilder stringBuilder=new StringBuilder();
            // Read from input steam
            while ((inputLine = in.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            // Close the stream and return the result
            in.close();

        }
        catch (MalformedURLException e) {
            // new URL() failed
            System.out.println("Bad URL...");
        }
        catch (IOException e) {
            // Catches refusals such as 'not found' or 'unauthorized'
            System.out.println("Name is not in data base? ");
        }
        // Default return value
        return "";
    }




}
