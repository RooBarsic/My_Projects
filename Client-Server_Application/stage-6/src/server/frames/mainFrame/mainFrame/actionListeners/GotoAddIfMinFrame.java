package server.frames.mainFrame.mainFrame.actionListeners;

import server.frames.addIfMin.AddIfMinFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GotoAddIfMinFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        new AddIfMinFrame();
    }
}