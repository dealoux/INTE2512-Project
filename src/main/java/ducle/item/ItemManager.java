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

    public void addRecord(Record item){
        recordList.put(item.id, item);
    }

    public void addDvd(DVD item){
        dvdList.put(item.id, item);
    }

    public void addGame(Game item){
        gameList.put(item.id, item);
    }

    public void addItem(Item item){
        if(item instanceof Record){
            addRecord((Record) item);
        }
        else if(item instanceof DVD){
            addDvd((DVD) item);
        }
        else if(item instanceof Game){
            addGame((Game) item);
        }
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

    public void removeRecord(String id){
        recordList.remove(id);
    }

    public void removeDvd(String id){
        dvdList.remove(id);
    }

    public void removeGame(String id){
        gameList.remove(id);
    }

    public void removeItem(Item item){
        if(item instanceof Record){
            removeRecord(item.id);
        }
        else if(item instanceof DVD){
            removeDvd(item.id);
        }
        else if(item instanceof Game){
            removeGame(item.id);
        }
    }

    public void removeItem(String id){
        removeItem(searchItem(id));
    }
}
