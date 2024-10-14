import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        Scanner Input = new Scanner(System.in);
        int harga, diskon, total;

        System.out.print("Harga :");
        harga = Input.nextInt();
        System.out.print("Diskon :");
        diskon = Input.nextInt();

        if (harga > 0) {
            total = harga - (harga * diskon / 100);
            System.out.println("Total :" + total);
        }
    }
}
