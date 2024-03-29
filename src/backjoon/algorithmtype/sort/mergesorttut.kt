/*
    바킹독님 알고리즘 강의 중 정렬 단원
    https://blog.encrypted.gg/955

    MergeSort
    - O(nlgn)
    : n이 10만개정도 된다면 O(n^2) 알고리즘은 10초~1분(컴퓨터 성능에 따라)의 시간이 소요
    : 그러나 O(nlgn)는 비교도 할수 없이 빠르며, n이 커질수록 이 격차는 더 커진다

    -N, M인 두 정렬된 리스트를 합쳐서 길이 N+M의 정렬된 리스트를 만들어야한다
    : 원시정렬방식으로 진행하면 O((n+m)^2) 의 시간복잡도가 나온다
    : 정렬된 n,m 두 리스트이니 0번째 idx부터 비교해서 더 작은 값을 넣어준다
    : 값을 넣은 배열은 idx++, 계속 비교하여 한쪽 배열이 끝날때까지 진행
    : 한쪽 배열이 끝난다면, 나머지 배열을 순차적으로 대입 
    => O(n+m)

    문제
    - 백준 11728(solve)
    - 머지 소트에는 Stable Sort라는 또 다른 성질이 있고 그 성질을 만족시키기 위해서는 가능하면 둘의 크기가 같을 때 앞쪽의 원소가 들어가야한다
    - 우연히 그렇게 구현했다

    ## Merge sort 알고리즘
    - 리스트를 반으로 쪼갰다가, 정렬 후 merge한다
    - 반으로 쪼개진 리스트를 정렬하는방법은, 해당 리스트들을 또 반으로 쪼개고, 정렬하여 merge 한다
    - 리스트의 size가 1이 될때까지 쪼갠뒤 merge를 한다면 순차적으로 정렬이 될것
    => 재귀방식
    !!!귀납적 사고로 본 merge sort
    길이가 8인 리스트를 정렬하려면, 길이가 4인 리스트를 정렬할수 있어야하고, 이것은 길이가 2인 리스트를 정렬할 수 있어야 한다
    => 길이가 1인 리스트를 정렬할수 있어야 한다는 의미 => 가능

    !!!시간복잡도
    1. 리스트가 분할하는 과정
    - O(n)
    - 반으로 분할하는 함수의 호출은 1,2,4,...,2^k 번
    - 1+2+4+...2^k = 2n-1 = O(n) 
    2. 리스트가 합쳐지는 과정
    - O(nlgn)
    - 두 정렬된 배열을 합치는데는 o(n+m)이 든다
    - 길이가 n/4인 4개의 리스트를 둘씩 합친다면 n/4 + n/4 + n/4 + n/4 = n 이된다
    - 분할이 완료되었을때 각 배열의 사이즈는 1이였다가, 원본배열이 될때까지 매번 2배씩 커진다
    - 원본배열의 사이즈가 2^k 였다면, 병합하는 줄은 k개가 된다
    => O(nk) = O(nlgn) 이다

    최종적으로 O(n) 보다 O(nlgn)이 더 크기때문에 O(nlgn)의 시간복잡도를 가진다

    구현
    - mergesort.kt 에 구현
    # java 자료구조,알고리즘 책도 참고해서 구현(p 246)
    - 백준 2751 문제를 통해 정확성 테스트 성공

    #Stable Sort 
    : 면접때 지식을 뽐낼 수 있는 기회라 하신다
    - 정렬을 진행할때 같은 값에 대한 우선순위가 존재하지않는다면 정렬 순서가 서로 다른 결과가 여러개가 나올 수 있다
    예) [파랑:21], [빨강:21], [초록:21], [검정: 38] 의 list가 존재할때 나이순으로 정렬
    - [파,빨,초,검]., [빨,파,초,검], [파,초,빨,검] 등 모두 올바른 정렬이나, 같은 값이라고 볼수는 없다
    정렬의 기준이 같은 원소들끼리는 원래의 순서를 따라가게 하는것이 Stable Sort 이다

    반대로 Stable Sort를 지원하지 않는 정렬방법은 Unstable Sort 라고 한다

    Merge Sort가 Stable Sort를 지원하기 위해서는 원소의 값을 비교할때, 값이 같다면 앞의 인덱스의 값을 먼저 정렬해줘야한다

    stable sort를 응용할 수 있는방법
    예) 
    파일 정렬
    파일리스트가 시간의 오름차순으로 정렬되어있을때, 파일을 1.크기순으로, 2.크기가 같다면 시간순으로 정렬하고 싶다
    => merge sort가 stable sort라는것을 알고있다면 고민없이 크기순으로 merge sort를 수행하면 된다

    예)
    2차원 좌표를 1.x가 작은순으로, 2.x가 같다면 y가 작은 순으로 정렬하고 싶다
    => y가 작은순으로 merge sort 수행 후, 해당 결과를 x가 작은순으로 merge sort를 수행하면 된다
    : 이건 예시일 뿐이고, merge 부분의 조건식을 변경하여 한번에 수행하는게 효율적이긴 하다
*/