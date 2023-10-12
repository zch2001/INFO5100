// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void main(String[] args) {
        Character character = new Character();
        character.ID = "Character";
        Hero hero = new Hero();
        hero.ID = "ZCH";
        Enermy enermy = new Enermy();
        enermy.ID = "AAA";
        character.move();
        hero.move();
        hero.move(10);
        enermy.move();
        enermy.move(10);
        if(character instanceof Enermy){
            enermy = (Enermy) character;
        }
        else {
            System.out.println("111");
        }
        enermy.move(1);
        character = enermy;
        character.move();

        }
    }
