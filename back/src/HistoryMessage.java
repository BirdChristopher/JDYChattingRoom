import java.util.ArrayList;

public class HistoryMessage {
    private static HistoryMessage instance;
    private ArrayList<String> list;
    private HistoryMessage(){
        this.list=new ArrayList<>();
    }
    public static HistoryMessage getInstance(){
        if(instance==null){
            instance=new HistoryMessage();
        }
        return instance;
    }
    public void addMessage(String s){
        this.list.add(s);
    }
    public ArrayList<String> getList(){
        return this.list;
    }
}
