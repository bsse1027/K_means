import java.awt.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Distance {

    public double Distance(Color a,Color b){


            double dist = sqrt(pow((a.getRed()-b.getRed()),2) + pow((a.getGreen()-b.getGreen()),2) + pow((a.getBlue()-b.getBlue()),2));

            return dist;



    }
}
