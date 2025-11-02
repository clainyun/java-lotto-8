package lotto;

import java.util.List;

public class WinningNumbers {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private static final String ERROR_DUPLICATE_BONUS = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.";
    private static final String ERROR_OUT_OF_RANGE = "[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.";

    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        this.winningLotto = new Lotto(winningNumbers);
        validateBonusNumber(bonusNumber, winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    // 보너스 번호 검증
    private void validateBonusNumber(int bonusNumber, List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BONUS);
        }
        if (bonusNumber < MIN_NUMBER || bonusNumber > MAX_NUMBER) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
    }

    // 당첨 로또 반환
    public Lotto getWinningLotto() {
        return winningLotto;
    }

    // 보너스 번호 반환
    public int getBonusNumber() {
        return bonusNumber;
    }
}
