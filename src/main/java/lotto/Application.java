package lotto;

import java.util.List;
import java.util.Map;

import lotto.utils.InputParser;
import lotto.utils.InputValidator;
import lotto.utils.LottoGenerator;

public class Application {
    public static void main(String[] args) {
        try {
            // 1. 구입 금액 입력 및 검증
            String inputAmount = InputView.readPurchaseAmount();
            int purchaseAmount = InputValidator.validatePurchaseAmount(inputAmount);

            // 2. 로또 발행
            List<Lotto> purchasedLottos = LottoGenerator.generateLottos(purchaseAmount);
            OutputView.printPurchasedLottos(purchasedLottos);

            // 3. 당첨 번호 입력 및 검증
            String winningInput = InputView.readWinningNumbers();
            List<Integer> winningNumbers = InputParser.parseWinningNumbers(winningInput);

            // 4. 보너스 번호를 입력 및 검증
            String bonusInput = InputView.readBonusNumber();
            int bonusNumber = InputParser.parseBonusNumber(bonusInput);
            WinningNumbers winningLotto = new WinningNumbers(winningNumbers, bonusNumber);

            // 5. 당첨 내역 계산
            Map<Rank, Integer> results = ResultCalculator.calculateResults(purchasedLottos, winningLotto);

            // 6. 결과 출력
            OutputView.printStatistics(results);
            double profitRate = ResultCalculator.calculateProfitRate(results, purchaseAmount);
            OutputView.printProfitRate(profitRate);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
