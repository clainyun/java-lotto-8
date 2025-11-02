package lotto;

import java.util.List;
import java.util.Map;

import lotto.utils.InputParser;
import lotto.utils.InputValidator;
import lotto.utils.LottoGenerator;

public class Application {
    public static void main(String[] args) {

        // 1. 구입 금액 입력 및 검증
        int purchaseAmount;
        while (true) {
            try {
                String inputAmount = InputView.readPurchaseAmount();
                purchaseAmount = InputValidator.validatePurchaseAmount(inputAmount);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 2. 로또 발행
        List<Lotto> purchasedLottos = LottoGenerator.generateLottos(purchaseAmount);
        OutputView.printPurchasedLottos(purchasedLottos);

        // 3. 당첨 번호 입력 및 검증
        List<Integer> winningNumbers;
        while (true) {
            try {
                String winningInput = InputView.readWinningNumbers();
                winningNumbers = InputParser.parseWinningNumbers(winningInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 4. 보너스 번호 입력 및 검증
        int bonusNumber;
        WinningNumbers winningLotto;
        while (true) {
            try {
                String bonusInput = InputView.readBonusNumber();
                bonusNumber = InputParser.parseBonusNumber(bonusInput);
                winningLotto = new WinningNumbers(winningNumbers, bonusNumber);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 5. 당첨 내역 계산
        Map<Rank, Integer> results = ResultCalculator.calculateResults(purchasedLottos, winningLotto);

        // 6. 결과 출력
        OutputView.printStatistics(results);
        double profitRate = ResultCalculator.calculateProfitRate(results, purchaseAmount);
        OutputView.printProfitRate(profitRate);
    }
}
