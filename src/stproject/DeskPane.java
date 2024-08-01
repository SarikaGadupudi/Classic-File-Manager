/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package STProject;

import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author sarik
 */
public class DeskPane extends JDesktopPane {

    public void cascadeFrames() {
        JInternalFrame[] frames = this.getAllFrames();
        int x = 20;
        int y = 20;
        int offset = 20; // The offset between each frame

        for (int i = frames.length - 1; i >= 0; i--) {
            JInternalFrame frame = frames[i];
            if (!frame.isIcon()) { // If the frame isn't minimized
                try {
                    frame.setMaximum(false); // Un-maximize the frame if it's maximized
                    // frame.reshape(x, y, frame.getWidth(), frame.getHeight()); // Move the frame
                    frame.setLocation(x, y);
                } catch (PropertyVetoException e) {
                    e.printStackTrace();
                }

                x += offset;
                y += offset;
            }
        }
    }

}
