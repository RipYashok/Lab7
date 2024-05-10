package commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import model.Route;
import sereverChannel.UserManager;
import sereverChannel.utils.User;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

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


    public String execute(List<Route> collection, String value, Route route, User user, Connection connection) throws IOException {
        return UserManager.register(user, connection);
    }

}
