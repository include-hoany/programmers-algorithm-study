package com.hoany.programmers.sort;

//         문제 H-Index

//         H-Index는 과학자의 생산성과 영향력을 나타내는 지표입니다. 어느 과학자의 H-Index를 나타내는 값인 h를 구하려고 합니다.
//         위키백과1에 따르면, H-Index는 다음과 같이 구합니다.
//
//        어떤 과학자가 발표한 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고
//        나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 이 과학자의 H-Index입니다.
//
//        어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 매개변수로 주어질 때,
//        이 과학자의 H-Index를 return 하도록 solution 함수를 작성해주세요.
//
//        제한사항
//        과학자가 발표한 논문의 수는 1편 이상 1,000편 이하입니다.
//        논문별 인용 횟수는 0회 이상 10,000회 이하입니다.

//        입출력 예
//        citations	return
//        [3, 0, 6, 1, 5]	3

//        입출력 예 설명
//        이 과학자가 발표한 논문의 수는 5편이고, 그중 3편의 논문은 3회 이상 인용되었습니다.
//        그리고 나머지 2편의 논문은 3회 이하 인용되었기 때문에 이 과학자의 H-Index는 3입니다.
//

import java.util.Arrays;
import java.util.stream.IntStream;

public class Hindex {
    public static void main(String[] args) {

        int[] arr = new int[]{3, 0, 6, 1, 5};
        int result = calcHindex(arr);
        System.out.println(result);

    } // end main

    // 0 부터 10000의 인용횟수 제한범위를 근거로 인트스트림을 통해 반복을 돈다.
    // 필터를 통해 h의 값을 증가시키며 h편의 논문이 h회 이상 인용이 되었는지 확인한다.
    // 필터를 통해 h값 이상 인용된 논문을 찾아내고 도출해낸 배열 길이는 h이상 인용된 논문의 수이다.
    // 해당조건의 부합하는 다수의  h 값들 중 최댓값이 H-Index이다. 따라서 max를 통해 최댓값을 도출해 낸다.

    // 증감된 h의 값이 더이상 조건에 유효하지 않으면 반복을 중단해야 한다.
    // rangeClosed 중단은 예외를 발생시켜 중단을 시키는 방법을 통해 문제를 해결했다.

    // 일반적인 반복문을 통해 해결하는 방법도 고려해 보면 좋을듯 하다.

    public static int calcHindex(int[] citations) {
        return IntStream.rangeClosed(1, 10000).filter( h -> {
            boolean result = Arrays.stream(citations).filter( read -> read >= h).toArray().length >= h;
            if(!result) new RuntimeException("stop");
           return result;
            // orElse를 사용한 이유는 하나의 h값도 유효하지 않는 값이 있을 수 있으므로.
            // null을 반환하는 대신 null일 경우 0을 반환하도록 하기 위함이다.
        }).max().orElse(0);

    } // end method

} // end class
