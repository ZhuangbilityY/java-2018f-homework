package CalabashBrosVSMobs;


import java.util.Random;

interface SortingMethods{
    public void bubbleSort(boolean printMessage);
    public void dichotomySort(boolean printMessage);
}
class CalabashBros implements SortingMethods{//七个葫芦娃的集合，提供这个集体的方法，如排序的方法
    private CalabashKid [] calabashBros;
    private Formation formation;
    private int broNum;//葫芦娃数量
    CalabashBros(){//初始化葫芦娃
        broNum=7;
        formation=Formation.LONGSNAKE;
        calabashBros=new CalabashKid[]{new CalabashKid("大娃","红色",1),
                new CalabashKid("二娃","橙色",2),
                new CalabashKid("三娃","黄色",3),
                new CalabashKid("四娃","绿色",4),
                new CalabashKid("五娃","青色",5),
                new CalabashKid("六娃","蓝色",6),
                new CalabashKid("七娃","紫色",7)};
        reArrange(false);
    }
    void reArrange(boolean printMessage){//随机排列七个葫芦娃
        Random rand=new Random();
        for(int i=0;i<100;i++){
            int a=rand.nextInt(7),b=rand.nextInt(7);
            change(a,b,printMessage);
        }
        if(printMessage)
            System.out.println("rearrange complete");
    }
    public void bubbleSort(boolean printMessage){//冒泡排序
        if(printMessage==true)
            System.out.println("Bubble Sorting:");
        for(int i=0;i<6;i++){
            for(int j=0;j<6-i;j++) {
                if(calabashBros[j].getSortWeight()>calabashBros[j+1].getSortWeight()){
                    change(j,j+1,printMessage);
                }
            }
        }
        if(printMessage) {
            System.out.println("Bubble Sort Complete!");
            printName();
        }
    }
    public void dichotomySort(boolean printMessage){//二分法插入排序
        if(printMessage)
            System.out.println("Dichotomy Sorting:");
        for(int i=1;i<7;i++){
            dichotomySortEach(0,i-1,i,printMessage);
        }
        if(printMessage) {
            System.out.println("Dichotomy Sorting Complete!");
            printColor();
        }
    }
    private void dichotomySortEach(int start, int end, int pos, boolean printMessage){//find the right place to insert calabashBros[pos]
        if(start==end){
            if(calabashBros[pos].getSortWeight()>calabashBros[start].getSortWeight()){
                dichotomySortInsert(start+1,pos,printMessage);
            }else{
                dichotomySortInsert(start,pos,printMessage);
            }
        }else{
            int mid=(start+end)/2;
            if(calabashBros[pos].getSortWeight()>calabashBros[mid].getSortWeight()){
                dichotomySortEach(mid+1,end,pos,printMessage);
            }else{
                dichotomySortEach(start,mid,pos,printMessage);
            }
        }
    }
    private void dichotomySortInsert(int start, int pos, boolean printMessage){//insert the kid at calabashBros[pos] to calabashBros[start]
        CalabashKid tempKid=calabashBros[pos];
        while(pos>start){
            change(pos,pos-1,printMessage);
            pos--;
        }
    }
    private void change(int i, int j, boolean printMesssage){//switch two kids' position and show the change
        if(printMesssage)
            System.out.println(calabashBros[i].getName()+" at pos "+i+" <-> "+calabashBros[j].getName()+" at pos "+j);
        CalabashKid tempKid=calabashBros[i];
        calabashBros[i]=calabashBros[j];
        calabashBros[j]=tempKid;
    }
    public void printName(){//print kids' names in array's sequence
        System.out.println("Names:");
        for(int i=0;i<7;i++){
            System.out.println("the no."+i+" kid's name is "+calabashBros[i].getName());
        }
    }
    public void printColor(){//print kids' color in array's sequence
        System.out.println("Colors:");
        for(int i=0;i<7;i++){
            System.out.println("the no."+i+" kid's color is "+calabashBros[i].getColor());
        }
        System.out.print("\n");
    }
    public CalabashKid getCalabashKid(int num) {
        return calabashBros[num];
    }
    public int getBroNum() {
        return broNum;
    }
    public Formation getFormation(){
        return formation;
    }
    public void changeOnePos(int x, int y, int num){
        calabashBros[num].changePos(x,y);
    }
}

class CalabashKid extends GoodBeing{//需要使用extends，放弃enum使用class，在calabashbros中定义七个葫芦娃
    private String color;
    private int sortWeight;//每个葫芦娃排行地位，即排序的权重
    CalabashKid(String name, String color, int sortWeight){
        this.color=color;
        this.name=name;
        pos=new Position(0,0);
        this.sortWeight=sortWeight;
    }
    public final String getColor(){//get kid's color
        return color;
    }
    public int getSortWeight(){
        return sortWeight;
    }
}




