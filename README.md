# QueryDSL
### QueryDSL 기본문법

<검색>

- select(), from()
- select()+from() = selectFrom()
- where()
- and(), or() → where() 메서드 내부에서 체인으로 사용가능
- like()
- contains() : 포함되어 있는가? like %검색단어%
- startswith() : 검색단어로 시작되는가? like 검색단어%

<동일성 체크>

- eq(), ne(), not()
- isNotNull() → Null이 아니면 true

<범위체크>

- in(원소들), notIn(원소들), between(start, end)
- a.goe(b) → a ≥ b // a.gt(b)  → a > b
- a.loe(b) → a ≤ b // a.lt(b) → a < b

<정렬>

- orderBy() → 메서드 체이닝으로 asc(), desc() 조건부여 가능
- nullsLast(), nullsFirst()

<결과조회>

- fetct() : 리스트 조회, 조회내용이 없다면 빈 리스트 반환
- fetchOne() : 단일 객체 반환 없으면 null, 둘 이상이면 에러발생
- fetchFirst() : 가장 먼저 찾는걸 반환
- fetchResults() : 페이징 정보 포함, total 쿼리 추가 실행, 페이징이 아니라면 지양
- fetchCount() : count 쿼리로 갯수 조회 long

<페이징>

→ offset(), limit()을 적절히 사용해서 가능

- offset() : 시작지점, 0부터 시작
- limit() : 페이징 사이즈

<집계>

→ select()에 집계 메서드 사용가능

- count(), sum(), avg(), max(), min()

### QueryDsl 조인

- innerJoin, leftJoin, rightJoin, fullJoin을 사용할 수 있다.

→ join(조인 대상, 별칭으로 사용할 쿼리 타입)

ex) selectFrom.(member).join(member.team, team).where(team.id.eq(id)).fetch()

- 조인에 on을 사용할 수 있다.

→ on은 Join할 데이터를 필터링할 때 사용한다.

- 성능최적화를 위한 fetchJoin도 사용가능 하다.

ex) selectFrom(member).innerJoin(member.team, team).fetch()….

### 프로젝션

⇒ select 절에 조회 대상을 지정하는 것을 프로젝션이라 한다.

<프로젝션 대상이 하나>

select(member.name).from(member).fetch()

위와 같이 하나의 필드를 조회할 수 있다.

<프로젝션 대상이 여러개>

프로젝션 대상으로 여러 필드를 선택하면 QueryDSL은 기본적으로 Tuple이라는 Map와 비슷한 내부 타입으로 반환해 준다.

<DTO로 조회>

- 프로퍼티로 접근하는 법(setter)
    - Projections.bean(Dto.class, …, …)
    - 기본생성자와 setter를 통해서 객체를 만든다.
- 필트 직접 접근
    - Projections.fields(Dto.class, …, …)
- 생성자 사용
    - Projections.constructor(Dto.class, …, …)

### 동적쿼리 만들기

1. BooleanBuilder 사용하기 
2. Where 다중 파라미터 사용

→ where() 메서드내에 null이 들어가면 무시된다. 따라서 동적쿼리가 가능하다.
