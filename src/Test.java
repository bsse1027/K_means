import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Test implements Serializable {

    public static void main(String[] args) throws IOException {


        BufferedImage Image,testImage;

        testImage = ImageIO.read(new File("C:\\IIT Files\\5th Semester\\puzzle.jpg"));
        Image = ImageIO.read(new File("C:\\IIT Files\\5th Semester\\puzzle.jpg"));

        //BufferedWriter Output =new BufferedWriter(new FileWriter("C:\\IIT Files\\5th Semester\\Codes\\out.txt"));

        int height,width,k=5;

        height=Image.getHeight();
//        System.out.println(height);
        width=Image.getWidth();
//        System.out.println(width);

        ArrayList<Color> Colors = new ArrayList<Color>();
        ArrayList<Color> newColors = new ArrayList<Color>();
        ArrayList<Color> Centroids = new ArrayList<Color>();
        ArrayList<Color> CentroidsCopy = new ArrayList<Color>();

        for (int i=0;i<height*width;i++){

            newColors.add(i,new Color(255,255,255));


        }


        int ClusterMap[]=new int[height*width];


        Distance dis = new Distance();
        /*firstRead++;
        firstInit++;*/

        //Read Image




        for(int i=0;i<width;i++){

                for(int j=0;j<height;j++){

                    Color imgColor= new Color(Image.getRGB(i,j));

                    Colors.add(imgColor);

                }



        }



        //Initialize Centroids




        for(int i=0;i<k;i++){

            for(int j=0;j<1;j++){

                int x = new Random().nextInt(width);
                int y = new Random().nextInt(height);
                //System.out.println("x, y: " + x + ", " + y);
                Color imgColor= new Color(Image.getRGB(x,y));
                Centroids.add(imgColor);
            }
        }





        /*Centroids.add(new Color(0, 0, 0));
        Centroids.add(new Color(255, 255, 255));*/

        while(true){

            //double count=0;

            for (int i=0;i<height*width;i++){

                double temp=Double.POSITIVE_INFINITY;
                double min=0;
                int index=0;

                for(int j=0;j<k;j++){
                    double d=dis.Distance(Colors.get(i),Centroids.get(j));

                    if(d<temp) {

                        //min = d;

                        index=j;
                        temp=d;



                    }

                    ClusterMap[i]=index ;


                }
            }
            //System.out.println(Centroids);



            for(int j=0;j<k;j++)
            {
                double sumR=0,sumG=0,sumB=0,countC=0;
                int avgR,avgG,avgB;
                for(int i=0;i<height*width;i++){

                    if(ClusterMap[i]==j){

                        countC++;

                        Color C=Colors.get(i);
                        sumR=sumR+C.getRed();
                        //System.out.println(sumR);
                        sumG=sumG+C.getGreen();
                        sumB=sumB+C.getBlue();







                    }


                }

                avgB= (int) (sumB/countC);
//            System.out.println(avgB);
                avgG= (int) (sumG/countC);
//            System.out.println(avgG);
                avgR= (int) (sumR/countC);
//            System.out.println(avgR);

                Color nc=new Color(avgR,avgG,avgB);

                Centroids.remove(j);
                Centroids.add(j,nc);
            }

            if(Centroids.equals(CentroidsCopy))
            {

                break;


            }






            CentroidsCopy= (ArrayList<Color>) Centroids.clone();

            //System.out.println(CentroidsCopy);




        }

        System.out.println(Centroids);

        int count=-1,countb=0;

        for(int i=0;i <k;i++){
            for(int j=0;j<height*width;j++){

                if(ClusterMap[j]==i){

                    /*countb++;
                    System.out.println(countb);*/

                   /*Colors.remove(j);
                    Colors.add(j,Centroids.get(i));*/

                    newColors.set(j,Centroids.get(i));


                }

            }

        }

        //System.out.println(newColors.size()+" \n" + width*height);

        for (int i=0;i<width;i++)
        {
            for(int j=0;j<height;j++)
            {
                count++;
                 testImage.getRGB(i,j);
//                System.out.println(count);
                testImage.setRGB(i,j,newColors.get(count).getRGB());
            }


        }

        ImageIO.write(testImage,"jpg",new File("Photos/output.jpg"));


        /*for (int i=0;i<height*width;i++)
        {
            System.out.println(ClusterMap[i]);
        }*/
        //firstInit=-1;
        //firstRead=-1;


    }




}
