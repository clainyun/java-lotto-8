package lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultCalculator {
    public static Map<Rank, Integer> calculateResults(List<Lotto> purchasedLottos, WinningNumbers winningNumbers) {
        Map<Rank, Integer> results = new HashMap<>();

        for (Lotto lotto : purchasedLottos) {
            int matchCount = getMatchCount(lotto, winningNumbers.getWinningLotto());
            boolean matchBonus = lotto.getNumbers().contains(winningNumbers.getBonusNumber());

            Rank rank = Rank.valueOf(matchCount, matchBonus);
            results.put(rank, results.getOrDefault(rank, 0) + 1);
        }
        return results;
    }

    private static int getMatchCount(Lotto lotto, Lotto winningLotto) {
        return (int) lotto.getNumbers().stream().filter(winningLotto.getNumbers()::contains)
                .count(); // count의 반환값은 long 이므로 int로 변환
    }

    public static double calculateProfitRate(Map<Rank, Integer> results, int purchaseAmount) {
        long totalPrize = results.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue()).sum();

        return ((double) totalPrize / purchaseAmount) * 100;
    }
}
