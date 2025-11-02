package lotto.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {

    // 예외 메시지 상수 정의
    private static final String ERROR_INVALID_WINNING_FORMAT = "[ERROR] 당첨 번호 입력 형식이 올바르지 않습니다.";
    private static final String ERROR_WINNING_NOT_NUMBER = "[ERROR] 당첨 번호는 숫자만 입력해야 합니다.";
    private static final String ERROR_BONUS_EMPTY = "[ERROR] 보너스 번호를 입력해야 합니다.";
    private static final String ERROR_BONUS_NOT_NUMBER = "[ERROR] 보너스 번호는 숫자여야 합니다.";

    public static List<Integer> parseWinningNumbers(String input) {
        try {
            return Arrays.stream(input.split(",", -1)) // -1 -> 마지막 빈 항목도 포함
                    .map(String::trim).map(s -> {
                        if (s.isEmpty()) { // 예: "1,2,3,4,5,6," -> 마지막 항목 ""
                            throw new IllegalArgumentException(ERROR_INVALID_WINNING_FORMAT);
                        }
                        return Integer.parseInt(s); // 숫자 아닌 경우 NumberFormatException 발생
                    }).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_WINNING_NOT_NUMBER);
        }
    }

    public static int parseBonusNumber(String input) {
        try {
            String trimmed = input.trim();
            if (trimmed.isEmpty()) {
                throw new IllegalArgumentException(ERROR_BONUS_EMPTY);
            }
            return Integer.parseInt(trimmed);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_BONUS_NOT_NUMBER);
        }
    }
}
