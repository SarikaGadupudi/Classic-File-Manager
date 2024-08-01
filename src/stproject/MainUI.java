/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package STProject;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

public class MainUI extends JFrame {

    JLabel statusBar;
    JFrame frame = new JFrame();
    JPanel panel2 = new JPanel(new BorderLayout());
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel toolbarpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
    JPanel panel3 = new JPanel(new BorderLayout());
    JMenuBar menubar = new JMenuBar();
    JToolBar toolbar = new JToolBar();
    //StatusBar statusbarclass = new StatusBar();
    JMenuItem menuItem;
    //DesktopPane dp=new DesktopPane();
    DeskPane deskPane;
    private int frameCount = 0; // Add this instance variable to your class
    private static JList<File> fileList;
    JInternalFrame focusedFrame;
    JDesktopPane focusedFrame1;
    static String selectedDrive = "C:\\";
    

    public MainUI() {
        this.deskPane = new DeskPane();
        
        initialize();
    }

    private void initialize() {
        frame.setLayout(new BorderLayout());
        frame.setTitle("CECS 544 Project - SarikaGadupudi, MeghanaKomatineni");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buildMenuBar();
        buildToolBAR();
        //add menu and toolbar to inner panel called mainpanel
        mainPanel.add(menubar, BorderLayout.NORTH);
        mainPanel.add(toolbarpanel, BorderLayout.SOUTH);

        buildStatusBar();
        frame.add(statusBar, BorderLayout.SOUTH);

        createNewFrame(deskPane);
        panel2.add(deskPane, BorderLayout.CENTER);

        //frame.add(deskPane);
        //add inner panel(mainpanel) to the outer panel
        panel2.add(mainPanel, BorderLayout.NORTH);

        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);

        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private void buildMenuBar() {
        JMenu fileMenu = new JMenu("File");
        JMenu treeMenu = new JMenu("Tree");
        final JMenu windowMenu = new JMenu("Window");
        final JMenu helpMenu = new JMenu("Help");
        menubar.add(fileMenu);
        menubar.add(treeMenu);
        menubar.add(windowMenu);
        menubar.add(helpMenu);
        menuItem = new JMenuItem("Rename");
        fileMenu.add(menuItem);
        menuItem = new JMenuItem("Copy");
        fileMenu.add(menuItem);
        menuItem = new JMenuItem("Delete");
        fileMenu.add(menuItem);
        menuItem = new JMenuItem("Run");
        fileMenu.add(menuItem);
        menuItem = new JMenuItem("Exit");
        fileMenu.add(menuItem);
        menuItem = new JMenuItem("ExpandBranch");
        treeMenu.add(menuItem);
        menuItem = new JMenuItem("CollapseBranch");
        treeMenu.add(menuItem);
        menuItem = new JMenuItem("New");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("NewWindow");
                createNewFrame(deskPane);
            }
        });
        windowMenu.add(menuItem);
        menuItem = new JMenuItem("Cascade");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cascade");
                deskPane.cascadeFrames();
            }
        });
        windowMenu.add(menuItem);
        menuItem = new JMenuItem("Help");
        helpMenu.add(menuItem);
        menuItem = new JMenuItem("About");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cascade");
                showDailogBox();
            }
        });
        helpMenu.add(menuItem);
    }

    private void showDailogBox() {
        JOptionPane.showMessageDialog(frame, "This is our project\nCECS 544 - SarikaGadupudi, MeghanaKomatineni");
    }

    private void buildStatusBar() {
        statusBar = new JLabel("Current Drive: ");
        statusBar.setPreferredSize(new Dimension(getWidth(), 20));
        //frame.add(statusBar, BorderLayout.SOUTH);
    }


    private void createNewFrame(JDesktopPane desktopPane) {

        JTree treeStruct = createTreeView();
        JPanel panelLeft = new JPanel();
        JPanel panelRight = new JPanel();
        fileList = new JList<>();
        JScrollPane scrollLeft = new JScrollPane(treeStruct);
        JScrollPane scrollRight = new JScrollPane(fileList);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollLeft, scrollRight);
        splitPane.setDividerLocation(400); // Set the initial divider location

        JInternalFrame iFrame = new JInternalFrame(selectedDrive, true, true, true, true);
        //iFrame.setBounds(60, 100, 500, 260);
        iFrame.setBounds(20 * frameCount, 100 + (20 * frameCount), 500, 300); // Set the bounds as per your preference
        iFrame.add(splitPane);
        iFrame.setVisible(true);
        desktopPane.add(iFrame);
        desktopPane.setVisible(true);
        // Add the InternalFrameListener to handle frame selection event
        iFrame.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                focusedFrame = (JInternalFrame) e.getSource();
                updateDriveInfoLabel();
            }
        });

        // Add a tree selection listener to update the internal frame title
        treeStruct.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                File selectedFile = (File) e.getPath().getLastPathComponent();
                String title = selectedFile.isDirectory() ? selectedFile.getAbsolutePath() : selectedFile.getParent();
                iFrame.setTitle(title);
                focusedFrame = iFrame;
                updateDriveInfoLabel();
            }
        });
        try {
            iFrame.setSelected(true);
            focusedFrame = iFrame;
            updateDriveInfoLabel();
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
        frameCount += 2; // Increment the frame count each time a new frame is created
    }

    private void updateDriveInfoLabel() {
        if (focusedFrame != null) {
            // Get the title of the focused internal frame
            String frameTitle = focusedFrame.getTitle();

            // Extract the drive information (e.g., "C:\\") from the title
            int driveInfoEndIndex = frameTitle.indexOf("\\");
            if (driveInfoEndIndex != -1) {
                String driveInfo = frameTitle.substring(0, driveInfoEndIndex + 1);
                statusBar.setText("Current Drive: " + driveInfo);

                //statusBar.setPreferredSize(new Dimension(getWidth(), 20));
            }
        }
    }

    private void buildToolBAR() {
        JButton simple = new JButton("Simple");
        JButton details = new JButton("Details");
        //JComboBox combox = new JComboBox();
        JComboBox<String> combox = new JComboBox<>();
        combox.addItem("C:\\");
        combox.addItem("G:\\");
        combox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDrive = (String) combox.getSelectedItem();
                System.out.println(selectedDrive);
                System.out.println("Drive display");
                createNewFrame(deskPane);
            }
        });
        toolbarpanel.add(combox);
        toolbarpanel.add(simple);
        toolbarpanel.add(details);
        combox.setPreferredSize(new Dimension(160, 27));
        simple.setPreferredSize(new Dimension(90, 27));
        details.setPreferredSize(new Dimension(90, 27));
    }   

    
    private JTree createTreeView() {
        File root = new File(selectedDrive);
        System.out.println(selectedDrive);
        FileSystemTreeModel model = new FileSystemTreeModel(root);
        JTree tree = new JTree(model);

        // Add a tree selection listener to the tree
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                File selectedFile = (File) e.getPath().getLastPathComponent();
                if (selectedFile.isDirectory()) {
                    updateFileList(selectedFile);
                }
            }
        });
        return tree;
    }

    // Method to update the file list
    private void updateFileList(File directory) {
        File[] files = directory.listFiles();
        fileList.setListData(files != null ? files : new File[0]);
    }

    public class FileSystemTreeModel implements TreeModel {

        private File root;

        public FileSystemTreeModel(File root) {
            this.root = root;
        }

        @Override
        public Object getRoot() {
            return root;
        }

        @Override
        public Object getChild(Object parent, int index) {
            File directory = (File) parent;
            String[] children = directory.list();
            if (children != null && index >= 0 && index < children.length) {
                return new File(directory, children[index]);
            }
            return null;
        }

        @Override
        public int getChildCount(Object parent) {
            File file = (File) parent;
            if (file.isDirectory()) {
                String[] fileList = file.list();
                if (fileList != null) {
                    return fileList.length;
                }
            }
            return 0;
        }

        @Override
        public boolean isLeaf(Object node) {
            return ((File) node).isFile();
        }

        @Override
        public void valueForPathChanged(TreePath path, Object newValue) {
            // Not used
        }

        @Override
        public int getIndexOfChild(Object parent, Object child) {
            File directory = (File) parent;
            File file = (File) child;
            String[] children = directory.list();
            for (int i = 0; i < children.length; i++) {
                if (file.getName().equals(children[i])) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public void addTreeModelListener(TreeModelListener l) {
            // Not used
        }

        @Override
        public void removeTreeModelListener(TreeModelListener l) {
            // Not used
        }
    }

}
