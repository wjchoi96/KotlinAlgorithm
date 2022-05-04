/*
    프로그래머스
    2021 카카오 채용 연계형 인턴십

    1. [solve] 숫자 문자열과 영단어(level 1) : 001.kt => 5분도 안걸림
    2. [solve] 거리두기 확인하기(level 2) : 002.kt => 한시간 10분 정도
    3. [solve] 표 편집(level 2) : 003.kt => 첫날 1시간반, 둘쨰날 15분
    - 4번의 걸친 수정(방식을 변경해보기도함) 끝에 성공
    - 검색의 도움을 받음
    - 바킹독님 블로그에도 풀이가 있다. 참고해봐도 좋을듯

    
*/

// 진행하며 알게된것들
/*
    https://yeon-kr.tistory.com/152
    ArrayList 연산의 시간복잡도

    ArrayList는 get은 예상대로 O(1)이지만
    add(addAt 포함)은 O(N)이였다
    add과정은 처음 기본size 10 이후부터는 새 배열을 만들고 복사 후 옮기게 되는 과정
    add(at)은 특정위치 다음에 존재하는 데이터를 복사 후 한칸 뒤에 붙이는 과정
    때문에 arrayList로는 효율성을 통과 할 수가 
*/

/*
    https://dundung.tistory.com/95
    https://barbera.tistory.com/45

    StringBilder, String

    String의 +, += 연산의 경우 O(N+K)의 시간복잡도
    기존문자열 길이 + 더하려는 문자열 크기
    
    StringBilder, StringBuffer은 동기화 지원 여부를 제외하고 동일한 기능을 가진다
    builder는 지원을 하지않아 멀티스레드에서 안전하지않다
    buffer은 지원을해서 멀티스레드에서 안전하지만, 동기화과정에서 오버헤드가 발생하여 느릴 수 있다

    StringBilder : 동적배열
    append : O(1) 
    delete : O(n)
    insert : O(n)

    동적배열에서의 추가작업은 배열의 크기를 늘리고, 전 배열을 System.arraycopy메소드를 통해 복붙하는 방식
    때문에 시간복잡도가 O(n)인데
    StringBuilder는 배열의 크기를 늘리때 전에 크기의 2배씩 확장하기때문에
    크기를 늘리는 O(n)작업이 자주 발생하지않는다
    때문에 상환복잡도를 계산하면 amoritized(분할상환 분석) O(1)음을 할 수 있다
    떄문에 append의 평균 복잡도는 O(1)

    https://loosie.tistory.com/339
    setCharAt : O(1)
    => insert가 아닌 해당 위치의 값을 변경하는 메소드
    구글링해서 알아낸건데 StringBuffer나 StringBuilder에 setCharAt(int idx, char c)이라는 메소드가 있다. 
    이는 시간복잡도 O(1)로 문자열 idx의 값을 c로 변경해주는 함수로 제외된 행의 번호 데이터만 있으면 쉽게 답을 출력할 수 있다


*/