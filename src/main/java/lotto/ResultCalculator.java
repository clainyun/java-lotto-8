package lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultCalculator {

    // 각 로또의 결과를 집계
    public static Map<Rank, Integer> calculateResults(List<Lotto> purchasedLottos, WinningNumbers winningNumbers) {
        Map<Rank, Integer> results = new HashMap<>();

        for (Lotto lotto : purchasedLottos) {
            int matchCount = getMatchCount(lotto, winningNumbers.getWinningLotto());
            boolean matchBonus = lotto.getNumbers().contains(winningNumbers.getBonusNumber());

            Rank rank = Rank.valueOf(matchCount, matchBonus);
            results.put(rank, results.getOrDefault(rank, 0) + 1); // 기존 등수 개수에 +1 (처음 등장 시 기본값 0)
        }
        return results;
    }

    // 두 로또 간 일치 개수 계산
    private static int getMatchCount(Lotto lotto, Lotto winningLotto) {
        // count()의 반환값은 long이므로 int로 변환
        return (int) lotto.getNumbers().stream().filter(winningLotto.getNumbers()::contains).count();
    }

    // 총 수익률 계산 (소수점 계산 포함)
    public static double calculateProfitRate(Map<Rank, Integer> results, int purchaseAmount) {
        long totalPrize = results.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue()).sum();

        return ((double) totalPrize / purchaseAmount) * 100;
    }
}
