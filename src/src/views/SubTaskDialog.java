package views;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Date;

public class SubTaskDialog extends JDialog {


            JLabel nameLabel;
            JTextField nameTf;
            JLabel docLabel;
            JTextField docTf;

            JLabel dataDueLabel;
            JTextField dateDueTf;
            JLabel maxScoreLabel;
            JTextField maxScoreTf;


            JLabel weightLabel;
            JTextField weightTf;
            JLabel bonusLabel;
            JTextField bonusTf;


            String name;
            String doc;
            String dateDue;
            double maxScore;
            double weight;
            double bonusPoints;
            JLabel other;
            JLabel group;
            JCheckBox groupCheck;


            public JButton gradeButton;
            public JButton deleteButton;
            public JButton cancelButton;
            public JButton saveButton;

            public SubTaskDialog() {

                //super(parent, "Details", false);
                setAttributes();
                setSize(700, 700);
                setBounds(50, 50, 700, 700);
                setLayout(new GridBagLayout());

                GridBagConstraints gc = new GridBagConstraints();
                gc.gridx = 0;
                gc.gridy = 0;
                gc.weightx = 1;
                gc.weighty = 1;
                gc.fill = GridBagConstraints.NONE;

                Dimension defD = new Dimension(250, 20);
                nameLabel = new JLabel("Subtask's name: ");
                nameTf = new JTextField(name);
                nameTf.setPreferredSize(defD);
                nameLabel.setLabelFor(nameTf);

                docLabel = new JLabel("Date of Creation: ");
                docTf = new JTextField(doc);

                JSpinner dueDateSpinner = new JSpinner( new SpinnerDateModel() );
                JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(dueDateSpinner, "MM/dd/yyyy HH:mm");
                dueDateSpinner.setEditor(timeEditor);
                dueDateSpinner.setValue(new Date());
                dueDateSpinner.setPreferredSize(defD);
                
                dataDueLabel = new JLabel("Date Due: ");
                dateDueTf = new JTextField(dateDue);

                maxScoreLabel = new JLabel("Max Score: ");
                maxScoreTf = new JTextField(Double.toString(maxScore));
                maxScoreTf.setPreferredSize(defD);

                weightLabel = new JLabel("Weight between subtasks: ");
                weightTf = new JTextField(Double.toString(weight));
                weightTf.setPreferredSize(defD);

                bonusLabel = new JLabel("Number of available bonus points: ");
                bonusTf = new JTextField(Double.toString(bonusPoints));
                bonusTf.setPreferredSize(defD);

                other = new JLabel("Other useful information: ");

                groupCheck = new JCheckBox();
                group = new JLabel("Group Project");




                saveButton = new JButton("Save changes");
                deleteButton = new JButton("Delete this Subtask");
                cancelButton = new JButton("Cancel");
                gradeButton = new JButton("Grades");

                gc.anchor = GridBagConstraints.EAST;
                add(nameLabel, gc);
                
                gc.anchor = GridBagConstraints.WEST;
                gc.gridx = 1;                
                add(nameTf, gc);
                
                gc.gridy = 1;
                gc.gridx = 0;
                gc.anchor = GridBagConstraints.EAST;
                add(docLabel, gc);
                
                gc.gridy = 1;
                gc.gridx = 1;
                gc.anchor = GridBagConstraints.WEST;
                add(docTf, gc);
                
                gc.gridy = 2;
                gc.gridx = 0;
                gc.anchor = GridBagConstraints.EAST;
                add(dataDueLabel, gc);
                
                gc.gridy = 2;
                gc.gridx = 1;
                gc.anchor = GridBagConstraints.WEST;
                add(dueDateSpinner, gc);
                
                gc.gridy = 3;
                gc.gridx = 0;
                gc.anchor = GridBagConstraints.EAST;
                add(maxScoreLabel, gc);
                
                gc.gridy = 3;
                gc.gridx = 1;
                gc.anchor = GridBagConstraints.WEST;
                add(maxScoreTf, gc);
                
                gc.gridy = 4;
                gc.gridx = 0;
                gc.anchor = GridBagConstraints.EAST;
                add(weightLabel, gc);
                
                gc.gridy = 4;
                gc.gridx = 1;
                gc.anchor = GridBagConstraints.WEST;
                add(weightTf, gc);
                
                gc.gridy = 5;
                gc.gridx = 0;
                gc.anchor = GridBagConstraints.EAST;
                add(bonusLabel, gc);
                
                gc.gridy = 5;
                gc.gridx = 1;
                gc.anchor = GridBagConstraints.WEST;
                add(bonusTf, gc);
                
                gc.gridy = 6;
                gc.gridx = 0;
                gc.anchor = GridBagConstraints.EAST;
                add(other, gc);
                gc.gridy = 7;
                JPanel comboPanel = new JPanel();
                comboPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                comboPanel.add(groupCheck);
                comboPanel.add(group);
                add(comboPanel, gc);
                gc.gridy = 8;
                add(gradeButton, gc);
                gc.gridy = 9;
                add(saveButton, gc);
                gc.gridy = 10;
                add(deleteButton, gc);
                gc.gridy = 11;
                add(cancelButton, gc);



            }

            public void setNameLabel(String name) {
                this.nameLabel = new JLabel(name);
            }

            public void setWeightLabel(String weight) {
                this.weightLabel = new JLabel("Weight of this task in final grade: " + weight);
            }

            public void setAttributes() {
                 name = "Home work";
                 doc = "05/11/2019";
                 dateDue = "10/11/2019";
                 maxScore = 100;
                 weight = 0.3;
                 bonusPoints = 10;
            }

			public JButton getGradeButton() {
				return gradeButton;
			}

			public void setGradeButton(JButton gradeButton) {
				this.gradeButton = gradeButton;
			}

			public JButton getDeleteButton() {
				return deleteButton;
			}

			public void setDeleteButton(JButton deleteButton) {
				this.deleteButton = deleteButton;
			}

			public JButton getCancelButton() {
				return cancelButton;
			}

			public void setCancelButton(JButton cancelButton) {
				this.cancelButton = cancelButton;
			}

			public JButton getSaveButton() {
				return saveButton;
			}

			public void setSaveButton(JButton saveButton) {
				this.saveButton = saveButton;
			}

    public JTextField getBonusTf() {
        return bonusTf;
    }

    public JTextField getNameTf() {
        return nameTf;
    }

    public JTextField getDateDueTf() {
        return dateDueTf;
    }

    public JCheckBox getGroupCheck() {
        return groupCheck;
    }

    public JTextField getDocTf() {
        return docTf;
    }

    public JTextField getMaxScoreTf() {
        return maxScoreTf;
    }

    public JTextField getWeightTf() {
        return weightTf;
    }


    public void setBonusTf(String bonusTf) {
        this.bonusTf.setText(bonusTf);
    }



    public void setDueTf(String dateDueTf) {
       this.dateDueTf.setText(dateDueTf);
    }

    public void setDocTf(JTextField docTf) {
        this.docTf = docTf;
    }

    public void setMaxScoreTf(String maxScoreTf) {
        this.maxScoreTf.setText(maxScoreTf);
    }

    public void setNameTf(String name) {
        this.nameTf.setText(name);
    }

    public void setWeightTf(String weightTf) {
        this.weightTf.setText(weightTf);
    }

    public void setDocTf(LocalDateTime l) {
                docTf.setText(l.toString());
    }

    public void setGroupCheck(Boolean b) {
                groupCheck.setSelected(b);
    }


}


