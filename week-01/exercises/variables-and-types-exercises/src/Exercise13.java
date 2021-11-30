public class Exercise13 {

    public static void main(String[] args) {

        int purchaseQuantity = 2;
        boolean hasCoupon = true;
        boolean hasDiscount = purchaseQuantity >= 6 || hasCoupon;

        System.out.println(hasDiscount);

    }
}
