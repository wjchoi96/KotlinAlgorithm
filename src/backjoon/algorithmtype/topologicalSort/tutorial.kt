/*
    바킹독님 알고리즘 강의 중 위상정렬 단원
    https://blog.encrypted.gg/1020

    #위상정렬
    방향 그래프에서 간선으로 주어진 정점 간 선후관계를 위배하지 않도록 나열하는 정렬
    (예) 선수 과목이 있는 수업들을 차례대로 듣는것)

    - 여러 개의 위상정렬 결과가 존재 가능
    - 그래프에 사이클이 존재할 경우, 올바른 위상정렬이 존재할 수 없음
    - 사이클이 존재하지 않는 방향 그래플르 DAG(DIrected Acyclic Graph)라고 줄여부르며
    DAG에서만 위상정렬이 잘 정의됨

    #위상정렬 수두코드
    1. 자신보다 앞에 위치하는 정점이 없는 정점들 중 하나를 맨 앞에 배치
    - indegree가 0이라고 표현 가능
    - indegree는 자신에게 들어오는 간선의 수를 의미
    - outdegree는 자신에게서 나가는 간선의 수를 의미
    2. 1번이 배치된 순간, 1번의 정점은 없는것으로 취급(해당 정점에서 나가는 간선도 함께 삭제)
    3. 1, 2번 반복
    - 매 순간바다 indegree가 0인 정점을 제거하는 방식

    #indegree가 0인 정점의 탐색
    - V는 정점의 수, E는 간선의 수
    - 간단히 구현을 한다면 O(V^2)가 될것
    - 몇가지 성질을 잘 잡아낸다면, 간편하고 효율적으로 O(V+E)로 가능

    1. 앞에서는 정점과 간선을 매번 제거하였지만 
    실제로 지울필요 없이 indegree값을 미리 저장했다가 그 값을 1 감소시키는것으로 수행 가능

    2. indegree가 0인 정점을 구하기 위해 매번 모든 정점들을 확인하는 대신
    목록을 따로 저장하고 있다가, 직전에 제거한 정점에서 연결된 정점들만 추가
    - 해당 목록은 Queue, Stack, 배열 등 상관없음

    #위상정렬 알고리즘
    1. 맨 처음 모든 간선을 읽으며 indegree테이블을 채움
    2. indegree가 0인 정점들을 모두 Queue에 넣음
    3. Queue에서 정점을 꺼내어, 위상정렬 결과에 추가
    4. 해당 정점으로부터 연결된 모든 정점들의 indegree값을 1 감소시킴
    이때 indegree가 0이되었다면 Queue에 추가
    5. Queue가 빌때까지 3, 4번 반복

    #위상정렬 알고리즘 주의점
    - 그래프에 사이클이 존재한다면, 데드락같이 indegree가 0이될수 없는 현상 발생
    - 즉, 사이클에 포함된 정점은 절대 Queue에 들어갈 수 없음
    - 위상정렬 알고리즘을 통해, 해당 그래프에 사이클의 유무도 알 수 있음

    #연습문제
    1. 백준 2252(solve)
    #네이버 카페 문제 50선 중 위상정렬 문제
    1. 백준 2252(바킹독 연습문제랑 겹침)
    2. 백준 1516(solve)
*/