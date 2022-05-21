/*
    바킹독님 알고리즘 강의 중 정렬 단원
    https://blog.encrypted.gg/955(원시, 머지, 퀵)
    https://blog.encrypted.gg/966?category=773649(카운팅, )

    - 원시(기초정렬)
    1. 선택정렬
    - 가장 큰값을 찾아서 n-1, n-2, n-3.. 순으로 대입해준다
    - O(n^2)

    2. 버블정렬
    - 인접한 두 값을 비교해 큰값을 오른쪽으로 보낸다
    - 배열을 한번 순회할때마다, n-1, n-2, n-3.. 큰값순으로 정렬된다ㅓ
    - O(n^2)

    3. 삽입정렬
    - O(n^2)


    - 좀더 향상된 정렬
    1. mergeSort
    - mergesorttut.kt
    : stable sort에 대한 내용도 포함

    2. quickSort
    - quicksorttut.kt

    3. counting sort
    - countingsorttut.kt

    4. Radix sort
    - radixsorttut.kt

    정렬 응용 문제
    1. 백준 1431(solve)
    2. 백준 11652(solve)
    3. 백준 7795(solve)
    - 이분탐색, 투포인터로 진행한 사람이 많지만 정렬로 한번 구현해보시길

*/