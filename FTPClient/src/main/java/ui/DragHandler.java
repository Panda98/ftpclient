package ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

/**
 * @author yiner
 * @since 2019-04-23
 * @link https://blog.csdn.net/ycb1689/article/details/7736507
 */
class DnDUtils {
    public static String showActions(int action) {
        String actions = "";
        if ((action & (DnDConstants.ACTION_LINK | DnDConstants.ACTION_COPY_OR_MOVE)) == 0) {
            return "None";
        }

        if ((action & DnDConstants.ACTION_COPY) != 0) {
            actions += "Copy ";
        }

        if ((action & DnDConstants.ACTION_MOVE) != 0) {
            actions += "Move ";
        }

        if ((action & DnDConstants.ACTION_LINK) != 0) {
            actions += "Link";
        }

        return actions;
    }

    public static boolean isDebugEnabled() {
        return debugEnabled;
    }

    public static void debugPrintln(String s) {
        if (debugEnabled) {
            System.out.println(s);
        }
    }

    private static boolean debugEnabled = (System.getProperty("DnDExamples.debug") != null);

}

public class DragHandler implements DropTargetListener{

    protected boolean acceptableType; // Indicates whether data is acceptable

//    private String filePath;

    private MainClient delegate;

    public DragHandler(MainClient delegate){
        this.delegate = delegate;
    }
//
//    public String getFilePath() {
//        return filePath;
//    }
//
//    public void setFilePath(String filePath) {
//        this.filePath = filePath;
//    }

    // interface methods
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        DnDUtils.debugPrintln("dragEnter, drop action = "
                + DnDUtils.showActions(dtde.getDropAction()));

        // Get the type of object being transferred and determine
        // whether it is appropriate.
        checkTransferType(dtde);
        // Accept or reject the drag.
        acceptOrRejectDrag(dtde);
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        DnDUtils.debugPrintln("DropTarget dragOver, drop action = "
                + DnDUtils.showActions(dtde.getDropAction()));

        // Accept or reject the drag
        acceptOrRejectDrag(dtde);
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        DnDUtils.debugPrintln("DropTarget dropActionChanged, drop action = "
                + DnDUtils.showActions(dtde.getDropAction()));

        // Accept or reject the drag
        acceptOrRejectDrag(dtde);
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        DnDUtils.debugPrintln("DropTarget dragExit");
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        DnDUtils.debugPrintln("DropTarget drop, drop action = "
                + DnDUtils.showActions(dtde.getDropAction()));

        // Check the drop action
        if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
            // Accept the drop and get the transfer data
            dtde.acceptDrop(dtde.getDropAction());
            Transferable transferable = dtde.getTransferable();

            try {
                boolean result = dropFile(transferable);

                dtde.dropComplete(result);
                DnDUtils.debugPrintln("Drop completed, success: " + result);
            } catch (Exception e) {
                DnDUtils.debugPrintln("Exception while handling drop " + e);
                dtde.dropComplete(false);
            }
        } else {
            DnDUtils.debugPrintln("Drop target rejected drop");
            dtde.rejectDrop();
        }
    }


    // util methods
    protected boolean acceptOrRejectDrag(DropTargetDragEvent dtde) {
        int dropAction = dtde.getDropAction();
        int sourceActions = dtde.getSourceActions();
        boolean acceptedDrag = false;

        DnDUtils.debugPrintln("/tSource actions are "
                + DnDUtils.showActions(sourceActions) + ", drop action is "
                + DnDUtils.showActions(dropAction));

        // Reject if the object being transferred
        // or the operations are not acceptable.
        if (!acceptableType
                || (sourceActions & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
            DnDUtils.debugPrintln("Drop target rejecting drag");
            dtde.rejectDrag();
        } else if ((dropAction & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
            // Not offering copy or move - suggest a copy
            DnDUtils.debugPrintln("Drop target offering COPY");
            dtde.acceptDrag(DnDConstants.ACTION_COPY);
            acceptedDrag = true;
        } else {
            // Offering an acceptable operation: accept
            DnDUtils.debugPrintln("Drop target accepting drag");
            dtde.acceptDrag(dropAction);
            acceptedDrag = true;
        }

        return acceptedDrag;
    }

    protected void checkTransferType(DropTargetDragEvent dtde) {
        // Only accept a list of files
        acceptableType = dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor);

        DnDUtils.debugPrintln("File type acceptable - " + acceptableType);
    }

    // This method handles a drop for a list of files
    protected boolean dropFile(Transferable transferable) throws IOException,
            UnsupportedFlavorException, MalformedURLException {
        List fileList = (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
        File transferFile = (File) fileList.get(0);
        final URI transferURL = transferFile.toURI();
        DnDUtils.debugPrintln("File URL is " + transferURL);
//        setFilePath(transferURL.getPath());
        String path = transferURL.getPath();
        delegate.dragFilePerform(path);
        return true;
    }


}