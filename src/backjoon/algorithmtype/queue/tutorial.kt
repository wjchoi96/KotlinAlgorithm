/*
    바킹독님 알고리즘 강의 중 큐 단원

    큐 : 한쪽끝에서 원소를 넣고, 반대쪽 끝에서 원소를 뺼 수 있는 자료구조

    큐를 FIFO(First in First out) 이라고 한다
    (스택은 FILO(First in Last out))

    큐
    - 원소의 추가 O(1)
    - 원소의 제거 O(1)
    - 제일 앞/뒤의 원소 확인이 O(1)
    - 제일 앞/뒤가 아닌 나머지 원소의 확인, 변경이 원칙적으로 불가능

    rear: 추가되는쪽
    front: 제거되는쪽

    원형큐: 큐의 앞 뒤가 연결되어 있는 큐

    실무에서는 배열로 큐를 구현할경우 원형큐를 권장하지만
    코딩테스트에서는 최대 push의 수가 정해져있기때문에 굳이 원형큐를 안만들어도 된다

    구현
    implement.kt


    연습문제
    1. 백준 10845(solve)

    따로 풀어보느 연습문제
    1. 백준 1158(solve)
    2. 백준 13335(solve)

*/