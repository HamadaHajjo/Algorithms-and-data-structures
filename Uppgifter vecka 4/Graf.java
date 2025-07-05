import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Graf {
    int Di;
    int[][] TD;
    boolean[] Exs;
    double Dist = 0;
    public int[] Side;

    ArrayList<String> F = new ArrayList<>();
    ArrayList<Integer> Nodes = new ArrayList<>();
    ArrayList<Integer> Xs = new ArrayList<>();
    ArrayList<Integer> Ys = new ArrayList<>();
    ArrayList<Integer> radius = new ArrayList<>();

    public Graf(File file) throws FileNotFoundException{
        Scanner sc = new Scanner(file);
        while (sc.hasNext()){
            F.add(sc.nextLine());

        }

        for (int i = 0; i < F.size(); i += 4){
            Nodes.add(Integer.parseInt(F.get(i).substring(6)));
        }

        for (int i = 1; i < F.size(); i += 4){
            Xs.add(Integer.parseInt(F.get(i).substring(3)));
        }

        for (int i = 2; i < F.size(); i += 4){
            Ys.add(Integer.parseInt(F.get(i).substring(3)));
        }

        for (int i = 3; i < F.size(); i += 4){
            radius.add(Integer.parseInt(F.get(i).substring(3)));
        }
        this.Di = Nodes.size();
        TD = new int[Di][Di];
        Side = new int[TD.length];

        Exs = new boolean[Di];
        for(int i = 0; i < Di; i++){
            Exs[i] = false;
        }
    }

    public void display(){
        double Hypdis;
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0, 1000);
        for(int i = 0; i < Nodes.size(); i++){
            StdDraw.circle(Xs.get(i), Ys.get(i), radius.get(i));
        }

        for(int i = 0; i < Nodes.size(); i++){
            for(int j = i + 1; j < Nodes.size(); j++){
                Hypdis = Math.sqrt(Math.abs((Xs.get(i) - Xs.get(j)) *
                (Xs.get(i) - Xs.get(j))) + Math.abs((Ys.get(i) - Ys.get(j))
                *(Ys.get(i) - Ys.get(j))));
                if(Hypdis < radius.get(i) + radius.get(j)){
                    StdDraw.line(Xs.get(i), Ys.get(i), Xs.get(j), Ys.get(j));
                    this.addEdge(Nodes.get(i), Nodes.get(j));
                }
            }
        }
    }

    public void addEdge(int source, int destination){
        TD[source][destination] = 1;
        TD[destination][source] = 1;
    }

    public void DiFS(int source){
        Exs[source] = true;
        for(int i = 0; i < TD.length; i++){
            if(TD[source][i] == 1 && !Exs[i]){
                DiFS(i);
            }
        }
    }

    public boolean connected(int source, int destination){
        DiFS(source);
        return Exs[destination];
    }

    public void Farthest(int source){
        DataBuffer<Integer> data = new DataBuffer<>(TD.length);
        Exs[source] = true;
        data.enqueue(source);
        while (!data.isEmpty()){
            int current = data.dequeue();
            for(int i = 0; i < TD.length; i++){
                if(TD[current][i] == 1 && !Exs[i]){
                    Side[i] = current;
                    Exs[i] = true;
                    data.enqueue(Nodes.get(i));
                }
            }
        }
    }

    public int ShDi(int source, int destination){
        Farthest(source);
        int cntr = 0;
        int i = destination;
        while(i != source){
            cntr++;
            i = Side[i];
        }
        return cntr;
    }

    public int Net(){
        int cntr = 0;
        for(int i = 0; i < Nodes.size(); i++){
            if(!Exs[i]){
                Farthest(Nodes.get(i));
                cntr++;
            }
        }
        return cntr;
    }

    public double Dist(int source){
        Farthest(source);
        double Hypdis;
        for(int i = 0; i < Exs.length; i++){
            if(Exs[i]){
                Hypdis = Math.sqrt(Math.abs((Xs.get(i) - Xs.get(source)) * (Xs.get(i)
                - Xs.get(source))) + Math.abs((Ys.get(i) - Ys.get(source))
                * (Ys.get(i) - Ys.get(source))));
                if (Hypdis > Dist){
                    Dist = Hypdis;
                }
            }
        }
        return Dist;
    }

    public static void main(String[] args) throws FileNotFoundException{
        Graf G = new Graf(new File("C:\\Users\\Hamad\\Desktop\\AODS1\\Uppgifter vecka 4"));
        G.display();

    }
}