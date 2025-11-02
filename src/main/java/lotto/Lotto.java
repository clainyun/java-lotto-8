package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private static final String ERROR_INVALID_SIZE = "[ERROR] 로또 번호는 6개여야 합니다.";
    private static final String ERROR_DUPLICATE_NUMBER = "[ERROR] 로또 번호에 중복된 숫자가 있습니다.";
    private static final String ERROR_OUT_OF_RANGE = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    // 전체 유효성 검증
    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
    }

    // 로또 번호 개수 검증
    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_INVALID_SIZE);
        }
    }

    // 로또 번호 중복 검증
    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBER);
        }
    }

    // 로또 번호 범위 검증
    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
            }
        }
    }

    // 로또 번호 반환
    public List<Integer> getNumbers() {
        return numbers;
    }
}
