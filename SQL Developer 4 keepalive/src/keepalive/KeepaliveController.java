package keepalive;

import oracle.ide.Context;
import oracle.ide.Ide;
import oracle.ide.controller.Controller;
import oracle.ide.controller.IdeAction;

import oracle.javatools.dialogs.MessageDialog;

import javax.swing.JOptionPane;

public class KeepaliveController implements Controller {

    private static boolean started = false;
    private Thread keeper = null;

    @Override
    public boolean handleEvent(IdeAction ideAction, Context context) {
        if (!started) {
            String timeout = null;
            timeout = JOptionPane.showInputDialog(null, "Please input the desired timeout (in seconds)");
            started = true;
            ConnectionPinger conn = new ConnectionPinger(timeout);
            keeper = new Thread(conn);
            keeper.start();
        } else {
            started = false;
            keeper.interrupt();
        }
        return true;
    }

    @Override
    public boolean update(IdeAction ideAction, Context context) {
        return true;
    }
}