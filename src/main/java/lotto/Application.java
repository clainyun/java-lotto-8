package lotto;

import java.util.List;
import java.util.Map;
import lotto.utils.InputParser;
import lotto.utils.InputValidator;
import lotto.utils.LottoGenerator;

public class Application {

    public static void main(String[] args) {
        new Application().run();
    }

    // 로또 프로그램 전체 실행 흐름 제어
    private void run() {
        int purchaseAmount = readPurchaseAmount(); // 1. 구입 금액 입력 및 검증
        List<Lotto> purchasedLottos = LottoGenerator.generateLottos(purchaseAmount); // 2. 로또 발행
        OutputView.printPurchasedLottos(purchasedLottos); // 3. 구매 내역 출력

        WinningNumbers winningLotto = readWinningNumbers(); // 4. 당첨 번호 및 보너스 번호 입력
        Map<Rank, Integer> results = ResultCalculator.calculateResults(purchasedLottos, winningLotto); // 5. 당첨 결과 계산

        OutputView.printStatistics(results); // 6. 당첨 통계 출력
        double profitRate = ResultCalculator.calculateProfitRate(results, purchaseAmount); // 7. 수익률 계산
        OutputView.printProfitRate(profitRate); // 8. 수익률 출력
    }

    // 구입 금액 입력 및 검증 (잘못된 입력 시 재입력)
    private int readPurchaseAmount() {
        while (true) {
            try {
                String input = InputView.readPurchaseAmount();
                return InputValidator.validatePurchaseAmount(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // [ERROR] 출력 후 재입력
            }
        }
    }

    // 당첨 번호 및 보너스 번호 입력
    private WinningNumbers readWinningNumbers() {
        List<Integer> winningNumbers = readWinningNumbersList();
        return readBonusNumber(winningNumbers);
    }

    // 당첨 번호 입력 및 검증
    private List<Integer> readWinningNumbersList() {
        while (true) {
            try {
                String input = InputView.readWinningNumbers();
                List<Integer> numbers = InputParser.parseWinningNumbers(input);
                new Lotto(numbers); // Lotto를 이용해 당첨 번호 유효성 검증
                return numbers;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // [ERROR] 출력 후 재입력
            }
        }
    }

    // 보너스 번호 입력 및 검증
    private WinningNumbers readBonusNumber(List<Integer> winningNumbers) {
        while (true) {
            try {
                String input = InputView.readBonusNumber();
                int bonus = InputParser.parseBonusNumber(input);
                return new WinningNumbers(winningNumbers, bonus);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // [ERROR] 출력 후 재입력
            }
        }
    }
}
