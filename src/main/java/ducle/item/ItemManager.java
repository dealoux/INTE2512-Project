package ducle.item;

import java.util.HashMap;

public class ItemManager {
    private HashMap<String, Record> recordList;
    private HashMap<String, DVD> dvdList;
    private HashMap<String, Game> gameList;

    public ItemManager(){
        recordList = new HashMap<>();
        dvdList = new HashMap<>();
        gameList = new HashMap<>();
    }

    public HashMap<String, Record> getRecordList() {
        return recordList;
    }

    public HashMap<String, DVD> getDvdList() {
        return dvdList;
    }

    public HashMap<String, Game> getGameList() {
        return gameList;
    }

    public String addRecord(Record item){
        recordList.put(item.getId(), item);
        return "Added record " + item.getId();
    }

    public String addDvd(DVD item){
        dvdList.put(item.getId(), item);
        return "Added DVD " + item.getId();
    }

    public String addGame(Game item){
        gameList.put(item.getId(), item);
        return "Added game " + item.getId();
    }

    public String addItem(Item item){
        String result = "";

        if(item instanceof Record){
            result = addRecord((Record) item);
        }
        else if(item instanceof DVD){
            result = addDvd((DVD) item);
        }
        else if(item instanceof Game){
            result = addGame((Game) item);
        }

        return result;
    }

    public Item searchItem(String id){
        Item result = recordList.get(id);

        if(result == null){
            result = dvdList.get(id);
        }

        if(result == null){
            result = gameList.get(id);
        }

        return result;
    }

    public String removeRecord(String id){
        String result;
        Record item = recordList.remove(id);

        if(item != null){
            result = "Removed record " + item.getId();
        }
        else{
            result = "Could not find any record with id " + id;
        }

        return result;
    }

    public String removeDvd(String id){
        String result;
        DVD item = dvdList.remove(id);

        if(item != null){
            result = "Removed DVD " + item.getId();
        }
        else{
            result = "Could not find any DVD with id " + id;
        }

        return result;
    }

    public String removeGame(String id){
        String result;
        Game item = gameList.remove(id);

        if(item != null){
            result = "Removed Game " + item.getId();
        }
        else{
            result = "Could not find any Game with id " + id;
        }

        return result;
    }

    public String removeItem(Item item){
        String result = "";

        if(item instanceof Record){
            result = removeRecord(item.getId());
        }
        else if(item instanceof DVD){
            result = removeDvd(item.getId());
        }
        else if(item instanceof Game){
            result = removeGame(item.getId());
        }

        return result;
    }

    public String removeItem(String id){
        String result;
        Item item = searchItem(id);

        if (item != null){
            result = removeItem(item);
        }
        else{
            result = "Could not find any item with id " + id;
        }

        return  result;
    }
}
