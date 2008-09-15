import java.util.*;

public class News{

    private boolean goodNews;
    private String information; // the actual info to be printed
    private String[] tags; // list of effected tickers

    public News(boolean posNeg, String info, String[] t){
	goodNews = posNeg;
	information = info;
	tags = t;
    }

    public boolean getEffect(){return goodNews;}
    public String getInfo(){return information;}
    public String[] getTags(){return tags;}

}
