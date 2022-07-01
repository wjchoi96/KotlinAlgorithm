/*
    바킹독님 알고리즘 강의 중 그래프 단원
    https://blog.encrypted.gg/1016
    
    
    #그래프
    정점과 간선으로 이루어진 자료구조
    - 네비게이션에서 최단 경로 찾기
    - 구글 같은 검색엔진에서 랭킹 정하기 
    등
    원소 사이의 연결 관계를 설정해야 하는 상황에서 유용하게 사용된다

    #차수(degree)
    각 정점에 대해서 간선으로 연결된 이웃한 정점의 개수

    #정점(Vertex/Node)
    그래프를 이루는 원소

    #간선(Edge)
    정점을 연결하는 선
    방향성이 존재할 수 있는데, 방향성이 없는 그래프를 무방향 그래프, 방향성이 있는 그래프를 방향 그래프 라고 칭한다
    간선에 방향성이 있다는 것은 일방통행 도로를 생각하면 된다

    #방향그래프에서의 차수는 outdegree, indegree로 나누어진다
    - outdegree = 자기에게서 나가는 간선(자신이 출발점 간선) / 진출 차수
    - indegree = 자기에게 들어오는 간선(자신이 도착점인 간선) / 진입 차수

    #사이클
    임의의 한 점에서 출발해 자기 자신으로 돌아올 수 있는 경로를 칭한다
    그래프 안에 사이클이 하나라도 존재하면 순환 그래프(Cyclic graph), 하나라도 없다면 비순환 그래프(Acyclic graph)라고 칭한다
    사이클을 고려할때, 간선의 방향성도 고려해야 한다

    모든 서로 다른 두 정점 쌍이 간선으로 연결된 그래프를 완전 그래프(Complete Graph)라고 칭하며
    임의의 두 정점 사이에 경로가 항상 존재하는 그래프를 연결 그래프(Connected Graph)라고 칭한다

    !!그래프는 꼭 연결되어 있을 필요도 없고, 
    - 두 정점 사이의 간선이 반드시 1개 이하일 필요도 없으며
    - 간선이 반드시 서로 다른 두 정점을 연결해야 할 필요도 없다 => 정점A에서 출발해 정점A로 되돌아 오는 간선이 있을 수 있다 => 이를 루프(loop)라고 칭한다

    두 정점 사이의 간선이 1개 이하이고, 루프가 존재하지 않는 그래프를 단순 그래프(Simple Graph)라고 칭한다

    !!문제에 따라 
    - 그래프는 연결되어있다/그래프는 연결그래프 이다,
    - 두 정점사이의 간선은 최대 1개 존재한다/같은 간선은 한개만 주어진다
    - 간선의 두 정점은 서로 다르다/간선은 서로 다른 두 정점을 연결한다
    와 같이 조건을 엄밀히 지정하는 경우가 많다
    그러나 이런 추가적인 조건이 없다면, 그래프가 분리되어있거나, 같은 간선이 여러개 있거나, 혹은 루프가 있는 형태도 고려를 해야 한다


    #표현법 -> 구현
    인접행렬, 인접리스트 방식 존재
    - 인접 행렬
    단순 그래프(두 정점 사이의 간선이 1개 이하인 그래프)일때 2차원 배열에서 연결된 두 정점에는 1을, 연결되지 않은 두 정점에는 0을 줘서 그래프 표현

    정점이 V개이고, 간선이 E개일때 
    - 어떤 두 점이 연결되어있는지 O(1)에 알 수 있다.
    - 공간은 O(V^2) 필요
    - 정점 a와 연결된 모든 정점의 목록을 알아내고 싶을때 O(V)

    - 인접 리스트
    인접 행렬과 비교했을때 정점이 많고, 간선은 상대적으로 작은 상황에서 공간을 절약할 수 있는 방식
    경우에 따라 인접 행렬로는 절대 저장이 불가능해 인접 리스트를 써야만 하는 상황이 종종 존재
    - 정점의 개수가 너무 많아 2차원 배열 선언시 메모리 부족 발생 경험
    - 두 점의 연결여부를 자주 확인할때, E가 V^2에 가까울때 효율적

    정점이 V개이고, 간선이 E개일때 
    - 정점 u, a가 연결되어있는지 O(min(deg(u), deg(a)))에 알 수 있다
    - O(V+E)의 공간이 필요
    - 정점 a와 연결된 모든 정점을 확인하는 시간 복잡도(O(deg(a)))
    - 특정 정점에 연결된 모든 정점을 자주 확인할 때, E가 V^2보다 훨씬 작을 때

    V가 100,000이고 E가 200,000일 경우에는 간선의 개수 E가 V2보다 훨씬 작기 때문에 인접 리스트를 사용하는 것이 효율적
        !애초에 256MB나 512MB 같은 일반적인 메모리 제한이 있는 상황에서는 V가 100,000이면 인접 행렬로 나타낼 수가 없다
    V가 100이고 E가 7,000일 경우에는 인접 행렬을 사용하는 것이 효율적

    일반적인 그래프 문제에서는 정점 u,v가 연결되어있는지를 반복적으로 확인하는 경우는 잘 없다
    BFS, DFS등 여러 경로 알고리즘들은 특정 정점에 연결된 모든 정점을 확인하는 작업이 반복적으로 등장
    => 인접 행렬보다는 인접 리스트로 그래프를 나타낼 상황이 훨씬 많다
    
    다만 
    - 입력 자체가 인접 행렬 느낌으로 주어지거나
    - V가 많이 작아 구현의 편의를 챙기고자 하거나
    - 최단경로 알고리즘 중 하나인 플로이드 알고리즘을 쓸때는 인접 행렬로 나타내는 그래프를 사용하는 경우가 있을 수 있다


    #BFS
    너비를 우선으로 방문하는 알고리즘
    1. 시작하는 정점을 큐에 넣고 방문했다는 표시를 남김
    2. 큐에서 정점을 꺼내어 그 정점과 연결된 모든 정점들에 대해 3번을 진행
    3. 해당 정점에 이전에 방문했었다면 아무것도 하지 않고, 처음으로 방문했다면 방문했다는 표시를 남기고 해당 칸을 큐에 삽입
    4. 큐가 빌때까지 2번을 반복

    모든 정점이 큐에 1번씩 들어가므로 
    - 인접 리스트에서 O(V+E) => 2번 과정에서 모든 정점들에 대해 그 정점과 연결된 모든 정점을 살펴보는 과정의 총 합이 방향 그래프에서 E, 무방향 그래프에서 2E
    - 인접 행렬에서 O(V^2)
    의 시간 복잡도

    #DFS
    깊이를 우선으로 방문하는 알고리즘
    1. 시작하는 정점을 스택에 넣고 방문했다는 표시를 남김
    2. 스택에서 정점을 꺼내어 그 정점과 연결된 모든 정점들에 대해 3번을 진행
    3. 해당 정점을 이전에 방문했다면 아무것도 하지 않고, 청므으로 방문했다면 방문했다는 표시를 남기고 해당 칸을 스택에 삽입
    4. 스택이 빌 때 까지 2번을 반복

    재귀로 작성한다면 코드가 짧고 간결해진다. 재귀에 익숙하다면 재귀적으로 짜는것이 좀 더 좋을 수도 있다
    다만 스택 메모리의 제한이 적은 경우에는 문제가 생길 수 있다

    모든 정점이 스택에 최소 1번 들어가므로
    - 인접리스트에서 O(V+E)
    - 인접행렬에서 O(V^2)
    의 시간 복잡도
    !다차원 배열을 순회하는 문제에서는 DFS가 필요하지 않다

    !!온라인 저지의 경우 스택 메모리의 제한이 적게 걸려있는 경우가 존재 할 수 있다
    처음 사용하는 플랫폼에서 코딩테스트를 진행할 경우 스택 메모리 제한을 알아두면 dfs구현시 망설이지 않을 수 있다
    => 2021년 11월 기준 백준, 프로그래머스, 구름에서는 스택 메모리와 관련한 문제가 없다
    => SW Expert Academy에서는 스택 메모리가 1MB로 제한


    DFS 재귀, 비재귀 구현에 따른 동작 차이
    방문 순서가 약간 다르다
    => 이전에 경험해봄

    1: {2, 3, 4}
    2: {1, 4}
    3: {1, 4}
    4: {1, 2, 3}
    재귀 DFS
    => 어떤 한 정점에 대해 방문 가능한 정점들을 visit처리하고 Stack에 미리 보관해 놓는 것이 아님을 명심
    dfs(1) -> dfs(2) -> dfs(4) -> dfs(3)

    비재귀 DFS
    dfs(1) -> push 2, 3, 4 -> pop 2 -> pop 3 -> pop 4

    우리가 관념적으로 생각하는 DFS는 재귀 DFS가 동작하는 방식과 일치
    => 때문에 단순히 Flood Fill, 순회를 하는 것이 아닌 DFS의 고유한 성질을 사용해 문제를 풀이해야 한다면 재귀 DFS를 구현해야 한다
    => 비재귀로 재귀DFS와 같은 순서를 보장하려면 
    스택에 정점을 push하면서 visit처리를 진행해 스택에 각 정점이 1번만 들어가도록 구현했던 것을
    스택에서 정점을 뽑아낼때 visit처리를 진행해 주면 된다 => visit 처리 이전에 visit 체크를 실시

    코딩테스트 레벨에서는 그래프에서 꼭 DFS를 해야 할 일이 별로 없다
    => cycle detection, SCC, BCC, 오일러 경로 등 난이도가 꽤 있는 문제들. 이런 문제는 잘 출제되지 않는다

    연습문제
    1. 백준 11724(solve)
    - 연결 요소란?
    => 이어지지 않은 두개의 그래프를 2개의 그래프라 표현할 수 있지만
    => 하나의 그래프에 두 개의 연결 요소를 가진다 할 수 있다

    2. 백준 1260(solve)


    추가 문제
    1. 백준 1012(solve)
    - 백준 그래프 이론, 그래프 탐색 유형 문제
    - solved.ac class3 문제

*/