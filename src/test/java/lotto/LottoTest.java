package lotto;

import lotto.utils.InputParser;
import lotto.utils.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 6개보다 적으면 예외가 발생한다.")
    @Test
    void 로또_번호의_개수가_6개보다_적으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1보다 작으면 예외가 발생한다.")
    @Test
    void 로또_번호가_1보다_작으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 45보다 크면 예외가 발생한다.")
    @Test
    void 로또_번호가_45보다_크면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 1보다 작으면 예외가 발생한다.")
    @Test
    void 보너스_번호가_1보다_작으면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 45보다 크면 예외가 발생한다.")
    @Test
    void 보너스_번호가_45보다_크면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다.")
    @Test
    void 구입_금액이_1000원_단위가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> InputValidator.validatePurchaseAmount("1500"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 숫자가 아니면 예외가 발생한다.")
    @Test
    void 구입_금액이_숫자가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> InputValidator.validatePurchaseAmount("abc"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호 입력 형식이 올바르지 않으면 예외가 발생한다.")
    @Test
    void 당첨_번호_입력_형식이_올바르지_않으면_예외가_발생한다() {
        assertThatThrownBy(() -> InputParser.parseWinningNumbers("1,2,3,4,5,6,"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호가 숫자가 아니면 예외가 발생한다.")
    @Test
    void 당첨_번호가_숫자가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> InputParser.parseWinningNumbers("1,2,3,4,5,abc"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 숫자가 아니면 예외가 발생한다.")
    @Test
    void 보너스_번호가_숫자가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> InputParser.parseBonusNumber("abc"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 정상적으로 생성된다.")
    @Test
    void 로또_번호가_정상적으로_생성된다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("당첨 번호와 보너스 번호가 정상적으로 생성된다.")
    @Test
    void 당첨_번호와_보너스_번호가_정상적으로_생성된다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        assertThat(winningNumbers.getWinningLotto().getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(winningNumbers.getBonusNumber()).isEqualTo(7);
    }

    @DisplayName("구입 금액이 1000원 단위로 정상 검증된다.")
    @Test
    void 구입_금액이_1000원_단위로_정상_검증된다() {
        assertThat(InputValidator.validatePurchaseAmount("1000")).isEqualTo(1000);
        assertThat(InputValidator.validatePurchaseAmount("5000")).isEqualTo(5000);
        assertThat(InputValidator.validatePurchaseAmount("10000")).isEqualTo(10000);
    }

    @DisplayName("당첨 번호가 정상적으로 파싱된다.")
    @Test
    void 당첨_번호가_정상적으로_파싱된다() {
        List<Integer> numbers = InputParser.parseWinningNumbers("1,2,3,4,5,6");
        assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("보너스 번호가 정상적으로 파싱된다.")
    @Test
    void 보너스_번호가_정상적으로_파싱된다() {
        assertThat(InputParser.parseBonusNumber("7")).isEqualTo(7);
    }
}
