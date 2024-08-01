
package STProject;

import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JList;
import javax.swing.JOptionPane;


public class ReName {
    private JList<File> fileList;
    File file;
    
    public void ReName(File selectedFile, JList<File> fileList)
    {
       String newName = JOptionPane.showInputDialog(fileList, "Enter new name:", "Rename", JOptionPane.PLAIN_MESSAGE);
        if (newName != null && !newName.trim().isEmpty()) {
            File newFile = new File(selectedFile.getParentFile(), newName);
            if (selectedFile.renameTo(newFile)) {
                // Update the list model if needed
            } else {
                JOptionPane.showMessageDialog(fileList, "Error renaming the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    }
    

