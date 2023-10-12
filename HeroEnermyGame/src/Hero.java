public class Hero extends Character{

    @Override
    public void move(){
//        super.move();
        System.out.println(ID+"moving");

    }
    public void move(int a){
        System.out.println(ID+"moving in"+a+"km/h");
    }
}
