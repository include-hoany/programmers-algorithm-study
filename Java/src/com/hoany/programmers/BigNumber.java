package com.hoany.programmers;




import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;

//        -- 문제

//        0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
//
//       예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.
//
//        0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.

//        제한 사항
//        numbers의 길이는 1 이상 100,000 이하입니다.
//        numbers의 원소는 0 이상 1,000 이하입니다.
//        정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.

//        입출력 예
//        numbers	return
//        [6, 10, 2]	"6210"
//        [3, 30, 34, 5, 9]	"9534330"

public class BigNumber {
    public static void main(String[] args) {

        int[] arr_One = new int[]{6, 10, 2};
        int[] arr_Two = new int[]{3, 30, 34, 5, 9};

        // 정렬의 기준은 아스키 코드를 생각하면 된다.
        // 아스키 코드로 문자열을 비교하면된다.

        // 그냥 정렬하면 될것같지만 30과 같은 뒤에 0이 붙어있는 숫자들이 있기때문이다.
        // 가장 큰 숫자를 만들기 위해서는 0을 맨 뒤로 보내야 한다.

        // 따라서 30과 같은 수를 아스키 코드로 분리해 보자면 3과 0으로 분리되어 표기된다.
        // 3은 아스키 코드로 51, 0은 48 이므로 한자한자 분리하여 정렬이 가능해 진다.

        // 30을 아래와 같이 아스키 코드로 뽑아내면 배열의 길이가 2가되고 앞자리 3은 0의 위치 0의 위치는 1로 배열에 나뉘어 저장된다.
        byte[] ascii = String.valueOf(arr_Two[1]).getBytes(StandardCharsets.US_ASCII);
        System.out.println(ascii[0]);
        System.out.println(ascii[1]);

        System.out.println(bigNumber(arr_One));
        System.out.println(bigNumber(arr_Two));

    }

    // 문자열을 오름차순으로 정렬한 이루
    // 다시 내림차순으로 정렬한다.

    // 마지막으로 정렬한 값을 하나의 문자열로 만든 다음 반환해주면 된다.

    public static String bigNumber(int[] arr) {
        String result =  Arrays.stream(arr).mapToObj(a -> String.valueOf(a)).sorted(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return (o2 + o1).compareTo(o1 + o2);
                }
            }).reduce("", (a, b) -> a + b);

        // 정렬한 이후 첫 시작이 0으로 시작되면 0이 제일 큰 수 이므로 0을 반환해준다.
        return result.startsWith("0") ? "0" : result;
    }

}
