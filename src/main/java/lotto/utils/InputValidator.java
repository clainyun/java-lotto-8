package lotto.utils;

public class InputValidator {
    private static final int LOTTO_PRICE = 1000;

    private static final String ERROR_NEGATIVE_AMOUNT = "[ERROR] 구입 금액은 양수여야 합니다.";
    private static final String ERROR_NOT_THOUSAND_UNIT = "[ERROR] 구입 금액은 1000원 단위여야 합니다.";
    private static final String ERROR_NOT_NUMBER = "[ERROR] 구입 금액은 숫자여야 합니다.";

    // 구입 금액 검증
    public static int validatePurchaseAmount(String input) {
        try {
            int amount = Integer.parseInt(input);

            if (amount <= 0) {
                throw new IllegalArgumentException(ERROR_NEGATIVE_AMOUNT);
            }
            if (amount % LOTTO_PRICE != 0) {
                throw new IllegalArgumentException(ERROR_NOT_THOUSAND_UNIT);
            }

            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER);
        }
    }
}
