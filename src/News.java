import java.util.*;

public class News{

    private double newsEffect;
    private String information; // the actual info to be printed
    private String[] tags; // list of effected tickers

    public News(double effect, String info, String[] t){
	newsEffect = effect;
	information = info;
	tags = t;
    }

    public double getEffect(){return goodNews;}
    public String getInfo(){return information;}
    public String[] getTags(){return tags;}

}
