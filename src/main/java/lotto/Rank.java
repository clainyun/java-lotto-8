package lotto;

public enum Rank {

    // 당첨 등수 정의 (일치 개수, 보너스 번호 일치 여부, 상금)
    FIRST(6, false, 2_000_000_000), SECOND(5, true, 30_000_000), THIRD(5, false, 1_500_000), FOURTH(4, false,
            50_000), FIFTH(3, false, 5_000), MISS(0, false, 0);

    private final int matchCount;
    private final boolean matchBonus;
    private final int prize;

    Rank(int matchCount, boolean matchBonus, int prize) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
    }

    // 일치 개수와 보너스 여부로 등수 판별
    public static Rank valueOf(int matchCount, boolean matchBonus) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5 && matchBonus) {
            return SECOND;
        }
        if (matchCount == 5) {
            return THIRD;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return MISS;
    }

    // 등수별 상금 반환
    public int getPrize() {
        return prize;
    }
}
