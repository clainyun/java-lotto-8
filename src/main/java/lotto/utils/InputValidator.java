package lotto.utils;

public class InputValidator {
    private static final int LOTTO_PRICE = 1000;

    public static int validatePurchaseAmount(String input) {
        try {
            int amount = Integer.parseInt(input);
            if (amount < LOTTO_PRICE || amount % LOTTO_PRICE != 0) {
                throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위의 양수여야 합니다.");
            }
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }
}
