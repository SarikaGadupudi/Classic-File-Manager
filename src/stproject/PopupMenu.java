package STProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class PopupMenu extends JPopupMenu {
    private JList<File> fileList;
    File file;
    ReName rename = new ReName();
    Copy copyObj = new Copy();
    Paste pasteObj = new Paste();
    Delete deleteObj = new Delete();
    

    public PopupMenu(JList<File> fileList) {
        
        this.fileList = fileList;
        
        
        JMenuItem renameMenuItem = new JMenuItem("Rename");
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        
      

        
        
        renameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = fileList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    File selectedFile = fileList.getModel().getElementAt(selectedIndex);
                    // Add your code to open the selected file here
                    rename.ReName(selectedFile, fileList);
                }
            }
        });
        
        copyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = fileList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    File selectedFile = fileList.getModel().getElementAt(selectedIndex);
                    // Add your code to open the selected file here
                    copyObj.Copy(selectedFile, fileList);
                }
            }
        });
        
        pasteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = fileList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    File selectedFile = fileList.getModel().getElementAt(selectedIndex);
                    // Add your code to open the selected file here
                    pasteObj.Paste(selectedFile, fileList);
                }
            }
        });

        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = fileList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    File selectedFile = fileList.getModel().getElementAt(selectedIndex);
                    // Add your code to delete the selected file here
                    deleteObj.Delete(selectedFile, fileList);
                }
            }
        });

        
        add(renameMenuItem);
        add(copyMenuItem);
        add(pasteMenuItem);
        add(deleteMenuItem);
    }
}
