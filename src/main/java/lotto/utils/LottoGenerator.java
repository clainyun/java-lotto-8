package lotto.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.Lotto;
import camp.nextstep.edu.missionutils.Randoms;

public class LottoGenerator {
    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private static final int LOTTO_COUNT = 6;

    // 전체 로또 발행 (구입 금액 기준)
    public static List<Lotto> generateLottos(int purchaseAmount) {
        int count = purchaseAmount / LOTTO_PRICE;
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(generateSingleLottoNumbers()));
        }
        return lottos;
    }

    // 단일 로또 번호 생성 및 정렬
    private static List<Integer> generateSingleLottoNumbers() {
        List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(LOTTO_MIN, LOTTO_MAX, LOTTO_COUNT));
        Collections.sort(numbers);
        return numbers;
    }
}
