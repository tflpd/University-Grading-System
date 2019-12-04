package views;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {

	private static final long serialVersionUID = 1L;
     private JTextField idText ;
     private JPasswordField pwdText;
     private JButton signInButton ;
     private JButton cancelButton ;
     private JLabel msgLabel;
	
    public LoginView() {
    	
    	
        JLabel welcomeLabel = new JLabel("Welcome to BU Grading System", SwingConstants.CENTER);
        JLabel userIDLabel = new JLabel("User ID: ", SwingConstants.CENTER);
        JLabel pwdLabel = new JLabel("Password: ", SwingConstants.CENTER);
        idText =new JTextField("c@bu.edu");
        pwdText =new JPasswordField();
        pwdText.setEchoChar('*');
        signInButton = new JButton("Sign In");
        cancelButton = new JButton("cancel");
        msgLabel = new JLabel("");
        
        Panel infoPanel = new Panel();
        Panel buttonPanel = new Panel();
        Panel msgPanel = new Panel();


        infoPanel.setLayout(new GridLayout(2, 2, 30,40));
        infoPanel.add(userIDLabel);
        infoPanel.add(idText);
        infoPanel.add(pwdLabel);
        infoPanel.add(pwdText);

        buttonPanel.add(signInButton);
        buttonPanel.add(cancelButton);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        
        msgPanel.add(msgLabel);
        msgPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        
        this.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        this.add(infoPanel, gbc);
        this.add(buttonPanel, gbc);
        this.add(msgPanel, gbc);
         
    }

	public JTextField getIdText() {
		return idText;
	}

	public void setIdText(JTextField idText) {
		this.idText = idText;
	}

	public JPasswordField getPwdText() {
		return pwdText;
	}

	public void setPwdText(JPasswordField pwdText) {
		this.pwdText = pwdText;
	}

	public JButton getSignInButton() {
		return signInButton;
	}

	public void setSignInButton(JButton signInButton) {
		this.signInButton = signInButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JLabel getMsgLabel() {
		return msgLabel;
	}

	public void setMsgLabel(String msgLabel) {
		this.msgLabel.setText(msgLabel);
	}
    
    
}
