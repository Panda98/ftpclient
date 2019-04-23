package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author yiner
 * @since 2019-04-22
 */

enum State {
    IDLE, WORKING, STOPPING
}

public class File {
    private String name;
    private String type;
    private Double progress;
    private State state;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        String oldPath = this.path;
        this.path = path;
        setName(pareseName(path));
        changeSupport.firePropertyChange("path", oldPath, path);
    }

    private String path;

    public File (String path, String type) {
        this.path = path;
        this.name = pareseName(path);
        this.type = type;
        this.progress = 0.0;
        this.state = State.IDLE;
    }

    /**
     * Parse the name from the path.
     * @param path the absolute path of file/directory
     * @return the name of the file/directory
     */
    private String pareseName(String path) {
        String[] arr = path.split("/");
        int len = arr.length;
        return arr[len-1];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        String oldType = this.type;
        this.type = type;
        changeSupport.firePropertyChange("type", oldType, type);
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        Double oldProgress = this.progress;
        this.progress = progress;
        changeSupport.firePropertyChange("progress", oldProgress, progress);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        State oldState = this.state;
        this.state = state;
        changeSupport.firePropertyChange("state", oldState, state);
    }

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
