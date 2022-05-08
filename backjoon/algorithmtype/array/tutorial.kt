/*
    바킹독님 알고리즘 강의 중 배열 단원

    메모리 상에 원소를 연속되게 배치한 자료구조

    - O(1)에 k번째 원소를 확인/변경 가능
    - 추가적으로 소모되는 메모리의 양(=overhead)가 거의 없음
    - Cache hit rate가 높음 => ?? 이게 뭐지
    - 메모리 상에 연속한 구간을 잡아야 해서 할당에 제약이 걸림

    [idx]로 접근, 값 변경, 확인 => O(1)
    배열의 끝에 추가, 제거 => O(1)
    배얄의 시작, 중간에 추가, 제거 => O(n)
    - 추가된다면 추가되는 곳 뒤의 원소들을 뒤로 밀어내야하기때문
    => 저장공간을 늘리고, 원소 하나씩 밀어내기 때문에 O(n)
    - 삭제는 삭제 후 원소를 하나씩 땡겨야 하기 떄문에 O(n)

    강의에는 나오지 않았지만
    저장공간을 늘린다는것은 해당 크키의 배열을 새로 생성하고
    값을 옮기는 과정이라고 알고있음

    연습문제
    1. 백준 10808
*/