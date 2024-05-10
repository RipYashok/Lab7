package clientChannel;

import commands.*;

import java.util.HashMap;
import java.util.Map;

public class CreateCommands {

    public static Map commands(){
        Add add = new Add();
        Exit exit = new Exit();
        Show show = new Show();
        Help help = new Help();
        Clear clear = new Clear();
        Remove_by_id removeById = new Remove_by_id();
        Info info = new Info();
        Remove_last removeLast = new Remove_last();
        Average_of_distance averageOfDistance = new Average_of_distance();
        Min_by_coordinates minByCoordinates = new Min_by_coordinates();
        Shuffle shuffle = new Shuffle();
        Count_less_then_distance countLessThenDistance = new Count_less_then_distance();
//        Execute_script executeScript = new Execute_script();
        Map<String, Command> commandsList = new HashMap<>();
        commandsList.put(add.getTitle(), add);
        commandsList.put(exit.getTitle(), exit);
        commandsList.put(show.getTitle(), show);
        commandsList.put(help.getTitle(), help);
        commandsList.put(clear.getTitle(), clear);
        commandsList.put(removeById.getTitle(), removeById);
        commandsList.put(info.getTitle(), info);
        commandsList.put(removeLast.getTitle(), removeLast);
        commandsList.put(averageOfDistance.getTitle(), averageOfDistance);
        commandsList.put(minByCoordinates.getTitle(), minByCoordinates);
        commandsList.put(shuffle.getTitle(), shuffle);
        commandsList.put(countLessThenDistance.getTitle(), countLessThenDistance);
//        commandsList.put(executeScript.getTitle(), executeScript);
        return commandsList;
    }

}
