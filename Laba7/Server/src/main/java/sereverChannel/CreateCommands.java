package sereverChannel;

import commands.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс с функцией создания коллекции команд
 */
public class CreateCommands {
    /**
     * Функция создания коллекции команд {@link CreateCommands#commands}
     * @return возвращает коллекцию команд
     */
    public static Map commands(){
        Command command = new Command();
        Add add = new Add();
        Show show = new Show();
        Clear clear = new Clear();
        Remove_by_id removeById = new Remove_by_id();
        Info info = new Info();
        Remove_last removeLast = new Remove_last();
        Average_of_distance averageOfDistance = new Average_of_distance();
        Min_by_coordinates minByCoordinates = new Min_by_coordinates();
        Shuffle shuffle = new Shuffle();
        Count_less_then_distance countLessThenDistance = new Count_less_then_distance();
//        Execute_script executeScript = new Execute_script();
        Help help = new Help();
        Map<String, Command> commandsList = new HashMap<>();
        commandsList.put(command.getTitle(), command);
        commandsList.put(add.getTitle(), add);
        commandsList.put(show.getTitle(), show);
        commandsList.put(clear.getTitle(), clear);
        commandsList.put(removeById.getTitle(), removeById);
        commandsList.put(info.getTitle(), info);
        commandsList.put(removeLast.getTitle(), removeLast);
        commandsList.put(averageOfDistance.getTitle(), averageOfDistance);
        commandsList.put(minByCoordinates.getTitle(), minByCoordinates);
        commandsList.put(shuffle.getTitle(), shuffle);
        commandsList.put(countLessThenDistance.getTitle(), countLessThenDistance);
//        commandsList.put(executeScript.getTitle(), executeScript);
        commandsList.put(help.getTitle(), help);
        return commandsList;
    }

}
