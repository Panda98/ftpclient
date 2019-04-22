package model;

import com.jformdesigner.S;

/**
 * @author yiner
 * @since 2019-04-22
 */
public class File {
    private String name;
    private String type;

    public File() {

    }

    public File (String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
