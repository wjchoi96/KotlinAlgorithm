/*
    바킹독님 알고리즘 강의 중 덱 단원

    덱
    - Restricted Structure의 끝판왕 같은 느낌의 자료구조
    => Restricted Structure 이란?? : 제한된구조물, 검색해도 크게 나오는게 없다
    - 양쪽 끝에서 삽입, 삭제가 가능
    - 스택과 큐를 덱의 특수한 예시라고 볼수 있다

    시간복잡도
    - 원소의 추가/제거 O(1)
    - 제일 앞/뒤의 원소 확인이 O(1)
    - 제일 앞/뒤가 아닌 나머지 원소들의 확인/변경이 원칙적으로 불가능

    구현
    - 배열을 이용한 구현
    - head, tail 두개의 포인터(rear, front)
    - head, tail의 초기값은 0이 아닌, 배열의 중간 
    => 양쪽에서 삽입이 가능하기때문에 여의봉처럼 양 옆을 확장해야한다
    => 중간에 두면 양쪽으로 확장이 가능하기때문
    - head - 가장 앞의 원소를 가리킴/ tail - 가장 뒤의 원소+1을 가리킴
    - 원형구현이 가능하지만, 선형으로 구현

    덱 콜렉션
    LinkedList 로 구현하는게 좋을것(처음과 끝에 추가가O(1))

    데이터 추가
    offerFirst, offerLast (add 도 가능)
    offer과 add의 차이점
    => 용량초과시 add는 Exception을, offer은 false를 리턴
    push: offerFirst 과 같다

    데이터 삭제
    pollFrist, pollLast (remove 도 가능)
    poll과 remove의 차이점
    => 비어있을때 remove는 예외, poll은 null 리턴
    pop: removeFirst와 같다

    데이터 검색 후 삭제(indexFirst and remove 와 같다)
    removeFirstOccurrence(object): 앞에서 탐색 시작, 첫번째 값 제거
    removeLastOccurrence(object): 뒤에서 탐색 시작, 첫번째 값 제거
    remove(object): removeFirstOccurence와 같다

    => 대체로 Last, First가 안들어가면 First가 붙는 효과가 나는것 같다

    데이터 확인
    first, peekFirst, peek
    - 값이 없을때 peek: null, first: 예외
    last, peekLast
    - 값이 없을때 peek: null, last: 예외

    contain: 포함되어있는지 확인
    size: 개수


    연습문제
    - 백준 10866(solve)

*/