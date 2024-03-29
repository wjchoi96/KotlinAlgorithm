/*
    바킹독님 알고리즘 강의 중 우선순위 큐 단원
    https://blog.encrypted.gg/1015
    
    #우선순위 큐
    pop을 할때 가장 먼저 들어온 원소가 나오는 대신 우선순위가 가장 높은 원소가 나오는 큐

    - 원소의 추가 O(lg N)
    - 우선순위가 가장 높은 원소의 확인 O(1)
    - 우선순위가 가장 높은 원소의 제거 O(lg N)

    각각 배열로 큐를 구현한다면 
    O(1), O(N), O(N) 이 되겠지만
    힙 자류구조를 이용하면 
    O(lg N), O(1), O(lg N) 에 처리 가능

    #힙
    힙은 이진 트리 모양을 가지고 있다
    이진 검색 트리와는 <이진 트리라는 공통점>만 있을 뿐이고, 다른 자료구조이다

    힙을 최댓값 혹은 최솟값을 찾는 목적으로 사용할 수 있고, 
    최댓값을 찾기 위해 사용하는 힙을 최대힙
    최솟값을 찾기 위해 사용하는 힙을 최소힙 이라고 한다

    최소힙에서는 부모가 자식보다 작아야 한다
    최대힙에서는 부모가 자식보다 커야 한다

    각 루트가 최솟값/최댓값이 된다

    힙에서는 원소를 삽입하는 순서가 bfs(너비우선탐색)의 탐색 순서와 같다
    - 높이가 낮은 곳 부터, 높이가 같은 정점의 경우 왼쪽부터 채워나간다
    때문에, 이진 검색 트리와는 달리 불균형이 발생하지 않고 늘 균형이 잘 맞는 이진 트리가 된다

    #insert
    첫번째 원소는 루트가 된다
    다음 원소가 현재 부모보다 큰 원소라면, 원래 삽입하는 순서 상 위치해야 하는곳에 정상적으로 위치시킨다
    다음 원소가 현재 부모보다 작은 원소라면, 원래 삽입하는 위치에 위치시키고, 부모와 자리를 바꾼다
        바꾼 후 자리에서의 부모와 값을 비교하여 동일한 과정 수행
    => 아무리 비교를 많이해도 최대 높이만큼만 올라가면서 자리를 바꿔주기 때문에 O(lg N)에 수행 가능

    #Fetch 최소값/최대값 확인
    루트의 값을 확인 O(1)
    !최소힙에서는 최솟값을 효율적으로 확인할 수 있지만, 열번째로 작은 값의 확인이라던가, 최댓값의 확인은 모든 원소를 다 보는게 아닌 한 불가능
    !최대힙도 마찬가지
    => 이진 검색 트리와 힙의 차이점

    #Erase
    지울 대상과, 트리 구조상 가장 마지막 위치인 가장 오른쪽 리프의 자리를 바꾸고, 지울 대상을 지운다
    이렇게 하면 가장 마지막 위치이인 오른쪽 리프를 지우기때문에, 트리의 구조는 잘 유지가 된다
    그 이후, 위치가 바뀐 대상이 알맞는 위치로 돌아가도록 부모-자식간 비교연산을 수행하며 자리를 바꾼다
    높이가 lg N 이니 시간복잡도는 O(lg N) 이다

    #구현 - implements.kt
    이진 트리 구조를 코드로 표현하는 방법 
    => 힙에 한해서 각 원소를 배열로 대응시켜 다소 간단하게 나타낼 수 있다
    
    8
    12      20
    16 14   21  27
    =>
    1-based-index, 1-index인 8이 루트
    [-, 8, 12, 20, 16, 14, 21, 27] 
    x번지의 왼쪽, 오른쪽자식: 2x, 2x+1
    x번지의 부모: x/2

    #TreeSet, TreeMap 과 PriorityQueue 의 차이점
    일단 Kotlin에서는 TreeSet, TreeMap은 중복을 허용하지 않아서 중복을 허용하는 작업을 하려면 PriorityQueue 사용이 필수
    
    Set은 새 정점을 동적할당하거나 정점을 제거하고 불균형 발생시 해결을 위한 처리가 필요하기 때문에
    같은 O(lg N)시간복잡도 이더라도 수행 속도가 더 느리다.

    PriorityQueue는 힙 구조이기 때문에 불균형이 없어 무조건 lg N 번 자리를 비교하면 끝이라 훨씬 빠르다
    (같은 연산시 2-4배 정도 속도 차이가 날 수 있다)
    또한, 공간을 차지하는 정도도 차이가 많이 난다

    PriorityQueue로 해결이 가능하다면, 최대한 PriorityQueue를 사용하는 것이 좋다

    #Kotlin PriorityQueue
    코틀린의 운선순위 큐는 Comparator를 전달하여 사용한다.
    var pq = PriorityQueue<T>(Comparator{a,b -> 조건식}) 구조
    var pq = PriorityQueue<T>{a,b -> 조건식} -> 람다
    C++과 다르게 comparator 전달 없이 생성하면 pop시에 작은 수부터 나오게 됩니다. 
    - 기본이 최소힙

    참고
    https://sangdo913.tistory.com/197
    https://leesh111112.tistory.com/238?category=1009913
    https://notepad96.tistory.com/104


    연습문제
    1. 백준 1927(solve)
    - implements 코드 유효성을 판단
    - 당연히 틀렸었고, 개선해서 성공
    2. 백준 11286(solve)
    3. 백준 1715(solve)
    
    4. 백준 7662(solve)
    - 이진검색트리 단원에서 소개된 문제인데, 우선순위큐로 구현 성공
    - 이전검색트리로 구현된 TreeMap 사용으로 구현 성공

    5. 백준 1202(solve)
    - 이진검색트리 단원에서 소개된 문제인데, 우선순위큐로 구현
    - TreeMap 으로도 구현 가능



*/