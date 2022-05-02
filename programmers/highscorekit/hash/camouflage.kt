/*
    프로그래머스 고득점 kit 
    해시 유형

    level 2

    위장

    매일 다른 옷을 조합하여 입는다

    clothes : 의상의 이름, 의상의 종류
    1<= 의상의 수 <= 30
    같은 이름의 의상은 x
    1<= 문자열의 길이 <= 20, 알파벳소문자와 _ 로만 이루어져있다
    하루에 최소 한개의 의상은 입는다

    서로 다른 옷의 조합의 수를 return

    백준 9375 패션왕 신혜빈 문제와 같은 유형
    16-theory 패키지에 풀었던 코드 있다
    => 그냥 각 조합이 나올 수 있는 경우의 수를 계산하며 규칙을 찾았었다
*/
/*
    tc1
    headgear 2
    eyewear 1

    헤드기어1
    헤드기어2
    안경1
    헤드기어1 + 안경1
    헤드기어2 + 안경1

    item의 개수 => 의상을 하나씩만 입었을 경우의 수
    2*1 => 2
    
    tc2
    face 3

    tc3
    headgear 2
    eyewear 1
    face 2

    헤드기어1
    헤드기어2
    안경1
    얼굴1
    얼굴2

    아래는 곱하기 2(헤드기어1, 헤드기어2)[ => 5*2 =10
    헤드기어1
    안경1

    헤드기어1
    얼굴1

    헤드기어1
    얼굴2

    헤드기어1
    안경1
    얼굴1

    헤드기어1
    안경1
    얼굴2
    ]

    안경1
    얼굴1
    
    안경1
    얼굴2

    하나씩만 입는 경우의 수 5
    2*1 => 헤드기어 + 안경
    2*2 => 헤드기어 + 얼굴
    2*1*2 => 헤드기어 + 안경 + 얼굴
    2*1 => 안경 + 얼굴
    5 + 2 + 4 + 4 + 2 => 17

    각 조합별 개수를 센다
    
    헤드기어 2 + 안입는 선택지 => 3
    안경1 + 안입는 선택지 => 2
    얼굴2 + 안입는 선택지 => 3
    3*2*3 = 18
    18 - 1(하나도 안입는 선택지) = 답

    아 다시 이 유형 나오면 접근 못할거같은데
*/

fun main(args : Array<String>){
    val clothes = arrayOf(
        arrayOf("yellowhat", "headgear"),
        arrayOf("bluesunglasses", "eyewear"),
        arrayOf("green_turban", "headgear")
    )

    // val clothes = arrayOf(
    //     arrayOf("crowmask", "face"),
    //     arrayOf("bluesunglasses", "face"),
    //     arrayOf("smoky_makeup", "face")
    // )
}