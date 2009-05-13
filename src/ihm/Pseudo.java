/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ihm;

import idc.Config;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




/**
 *
 * @author sim
 */


public class Pseudo extends JDialog {

    private JLabel nomLabel;
    private JTextField nom;
    private String pseudo;

	public Pseudo(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(350, 120);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.initComponent();

	}

    public String getPseudo() {
        return pseudo;
    }

 
    private void initComponent(){
        
        JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(220, 60));
		nom = new JTextField(Config.nickname);
		nom.setPreferredSize(new Dimension(100, 25));
		nomLabel = new JLabel("Entrez votre pseudo :");
		panNom.add(nomLabel);
		panNom.add(nom);

        JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panNom);

        JPanel control = new JPanel();
        control.setBackground(Color.white);
		JButton okBouton = new JButton("OK");

		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
                pseudo = new String(nom.getText());
                setVisible(false);
			}
		});
		
		control.add(okBouton);

        this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);

        this.setVisible(true);


    }


}

