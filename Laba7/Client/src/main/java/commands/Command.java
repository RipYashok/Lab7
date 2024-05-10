package commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import model.Route;

import java.io.IOException;
import java.util.Scanner;

public class Command {
    private String title;
    private String description;
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Route execute(Scanner scanner) throws IOException {
        return null;
    }

}
